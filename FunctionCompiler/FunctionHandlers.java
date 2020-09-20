package oop.ex6.main.FunctionCompiler;
import oop.ex6.main.SyntaxExceptions.BadApplication.*;
import oop.ex6.main.SyntaxExceptions.BadInit.*;
import oop.ex6.main.SyntaxExceptions.BadMethodCall.*;
import oop.ex6.main.dataHolderClasses.*;
import oop.ex6.main.syntaxValidators.VarType.*;
import oop.ex6.main.SyntaxExceptions.SyntaxException;
import oop.ex6.main.syntaxValidators.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * this class compile the function
 */
public class FunctionHandlers {

    private static final String OR_ALSO= "(\\|\\|)|(&&)";

    /**
     *
     * @param line the line to check
     * @param globalVarMap the global var map
     * @param localVarMap the local var map
     * @throws SyntaxException is the line illegal
     */
    public static void initHandler(String line, HashMap<String, Variable> globalVarMap,
                                   HashMap<String, Variable> localVarMap) throws SyntaxException {

        InitValidator validator = new InitValidator();
        validator.valid(line);

        ArrayList<Variable> vars = validator.getVariables();

        for (Variable variable : vars) {
            // checks if variable exists in the varMap
            if (localVarMap.containsKey(variable.getVarName())) {
                throw new VariableAlreadyExists();
            }

            localVarMap.put(variable.getVarName(), variable);
        }

        ArrayList<String> appliedVars = validator.getAppliedVars();

        appliedVarsValidator(  globalVarMap,  localVarMap , validator, appliedVars);
    }

    /**
     *
     * @param globalVarMap the global variable map
     * @param localVarMap the local variable map
     * @param validator the validator
     * @param appliedVars the variable
     * @throws SyntaxException is the line illegal
     */
    private static void appliedVarsValidator(HashMap<String, Variable> globalVarMap,
                                             HashMap<String,Variable> localVarMap , InitValidator validator,
                                             ArrayList<String> appliedVars) throws SyntaxException{
        for (String expression : appliedVars) {
            String left = expression.substring(0, expression.indexOf("=")).trim();
            String right = expression.substring(expression.indexOf("=")+1).trim();
            Variable value;

            if(localVarMap.containsKey(right)){
                value = localVarMap.get(right);
            } else if(globalVarMap.containsKey(right)){
                value = globalVarMap.get(right);
            } else {
                throw new ValueDoesntExists();
            }
            if(!value.isInitialized()){
                throw new ValueNotInitialized();
            }

            VarTypeValidator.typeValidator(validator.getVarType(), value.getType());

            Variable variable = new Variable(left, validator.isFinalVar(), validator.getVarType(), true);
            localVarMap.put(left, variable);
        }
    }

    /**
     * check if the application if legal
     * @param line the line to check
     * @param localVarMap the local var map
     * @param globalVarMap the global var map
     * @throws SyntaxException if the syntax illegal
     */
    public static void appHandler(String line, HashMap<String, Variable> localVarMap,
                                  HashMap<String, Variable> globalVarMap)  throws SyntaxException {

        AppValidator validator = new AppValidator();
        validator.valid(line);
        String varName = validator.getVarName();
        String valName = validator.getValName();
        Variable var;

        // CHECKING LEFT SIDE
        if (!(localVarMap.containsKey(varName) || globalVarMap.containsKey(varName))) {
            throw new VariableDoesntExists();
        }
        // varName variable exists

        if (localVarMap.containsKey(varName)) {
            var = localVarMap.get(varName);
        } else {
            var = globalVarMap.get(varName);
        }

        if (var.getIsFinal()) {
            throw new VariableIsFinal();
        }

        // CHECKING RIGHT SIDE
        Variable val;

        if (!(localVarMap.containsKey(valName) || globalVarMap.containsKey(valName))) {
            var.getType().valid(valName);
        } else {
            if (localVarMap.containsKey(valName)) {
                val = localVarMap.get(valName);
            } else {
                val = localVarMap.get(valName);
            }
            if (!val.isInitialized()) {
                throw new ValueNotInitialized();
            }
            if (val.getType() != var.getType()) {
                throw new IllegalValueType();
            }
        }

        var.setInitialized(true);

    }

    /**
     * check the method call syntax
     * @param line the line to check
     * @param localVarMap the local var map
     * @throws SyntaxException if the syntax illegal
     */
    public static void funcImpHandler(String line, HashMap<String, Variable> localVarMap ,
                                      HashMap<String, Function> funcMap) throws SyntaxException {

        FunctionCallValidator validator = new FunctionCallValidator();
        validator.valid(line);

        String funcName = validator.getFuncName().trim();
        ArrayList<String> params = validator.getParams();

        if (!funcMap.containsKey(funcName)) {
            throw new MethodDoesntExists();
        }

        Function function = funcMap.get(funcName);

        if (params.size() != function.getParamsArray().size()) {
            throw new WrongParameters();
        }

        checkParams (localVarMap , params, function);
    }

    /**
     *
     * @param localVarMap the local variable map
     * @param params the parameters of this function
     * @param function the function
     * @throws SyntaxException if the line illegal
     */
    private static void checkParams (HashMap<String, Variable> localVarMap , ArrayList<String> params,
                                     Function function) throws SyntaxException{
        for (int i = 0; i < params.size(); i++) {

            Variable variable = function.getParamsArray().get(i);
            VarType expVarType = variable.getType();

            if (localVarMap.containsKey(params.get(i))) {
                if (!(expVarType == localVarMap.get(params.get(i)).getType()) ||
                        !localVarMap.get(params.get(i)).isInitialized())
                    throw new WrongParameters();
            } else {
                expVarType.valid(params.get(i).trim());
            }
        }

    }
    /**
     *
     * @param line the line to check
     * @param globalVarMap the global var map
     * @throws SyntaxException if the line is illegal
     */
    public static void ifWhileHandler(String line , HashMap<String, Variable> globalVarMap) throws SyntaxException {

        IfWhileValidator validator = new IfWhileValidator();
        validator.valid(line);

        String input = line.substring(line.indexOf("(")+1, line.indexOf(")")).trim();
        String[] conditions = input.split(OR_ALSO);

        for(String condition : conditions){
            condition = condition.trim();
            if (globalVarMap.containsKey(condition)) {
                Variable temp = globalVarMap.get(condition);
                if (!temp.isInitialized()) {
                    throw new ValueNotInitialized();
                }
                if(temp.getType() != VarType.BOOLEAN && temp.getType() != VarType.INT &&
                        temp.getType() != VarType.DOUBLE){
                    throw new IllegalValueType();
                }
            } else {
                VarType.BOOLEAN.valid(condition);
            }
        }
    }
}
