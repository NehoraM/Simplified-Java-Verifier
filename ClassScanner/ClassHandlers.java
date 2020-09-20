package oop.ex6.main.ClassScanner;
import oop.ex6.main.SyntaxExceptions.BadApplication.*;
import oop.ex6.main.SyntaxExceptions.BadInit.*;
import oop.ex6.main.SyntaxExceptions.BadMethodDeclaration.*;
import oop.ex6.main.dataHolderClasses.*;
import oop.ex6.main.syntaxValidators.*;
import oop.ex6.main.syntaxValidators.VarType.*;
import oop.ex6.main.SyntaxExceptions.SyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * this class handler with line in the class
 */
public class ClassHandlers {

    private static final String EQUAL = "=";


    /**
     *
     * @param line the line if the init
     * @param varMap the variables map of this class
     * @throws SyntaxException if it illegal application
     */
    static void appHandler(String line, HashMap<String, Variable> varMap) throws SyntaxException {
        AppValidator validator = new AppValidator();
        validator.valid(line);
        String varName = validator.getVarName();
        String valName = validator.getValName();
        Variable var;
        Variable val;

        // CHECKING LEFT SIDE
        if (!varMap.containsKey(varName)) {
            throw new VariableDoesntExists();
        }

        // varName variable exists
        var = varMap.get(varName);
        if(var.getIsFinal()) {
            throw new VariableIsFinal();
        }

        // CHECKING RIGHT SIDE
        if (!varMap.containsKey(valName)) {
            var.getType().valid(valName);
        } else {
            val = varMap.get(valName);
            if(!val.isInitialized()){
                throw new ValueNotInitialized();
            }
            if(var.getType() != val.getType()){
                throw new IllegalValueType();
            }
        }

        var.setInitialized(true);
    }


    /**
     * this method handle with line of create method
     * @param line the line of the function create
     * @param funcMap the function of this class
     * @return Function object of this function
     * @throws SyntaxException if this line illegal
     */
    static Function funcHandler(String line, HashMap<String, Function> funcMap)
            throws SyntaxException{
        FunctionValidator validator = new FunctionValidator();
        validator.valid(line);

        String funcName = validator.getFuncName();
        if(funcMap.containsKey(funcName)){
            throw new MethodAlreadyExists();
        }
        Function function = new Function(validator.getParamsArr());
        funcMap.put(funcName, function);
        return function;
    }

    /**
     * this method handle with line of init variable
     * @param line the line of the init
     * @param varMap the var map of the function
     * @throws SyntaxException if the init illegal
     */

    static void initHandler(String line, HashMap<String, Variable> varMap) throws SyntaxException{
        InitValidator validator = new InitValidator();
        validator.valid(line);

        ArrayList<Variable> vars = validator.getVariables();
        for(Variable variable: vars) {
            // checks if variable exists in the varMap
            if (varMap.containsKey(variable.getVarName())) {
                throw new VariableAlreadyExists();
            } else {
                varMap.put(variable.getVarName(), variable);
            }
        }
        ArrayList<String> appliedVars = validator.getAppliedVars();
        initValidator(appliedVars , varMap , validator );
    }

    /**
     *
     * @param appliedVars the variables of this init
     * @param varMap the variables of this function
     * @param validator the InitValidator
     * @throws SyntaxException if the init illegal
     */
    private static void initValidator(ArrayList<String> appliedVars, HashMap<String, Variable> varMap ,
                                      InitValidator validator ) throws SyntaxException{

        for(String expression : appliedVars){
            String left = expression.substring(0, expression.indexOf(EQUAL)).trim();
            String right = expression.substring(expression.indexOf(EQUAL)+1).trim();
            if(!varMap.containsKey(right)){
                throw new ValueDoesntExists();
            }
            Variable value = varMap.get(right);
            if(!value.isInitialized()){
                throw new ValueNotInitialized();
            }

            VarType variableType = validator.getVarType();
            VarType valueType = value.getType();

            VarTypeValidator.typeValidator(variableType, valueType);

            Variable variable = new Variable(left, validator.isFinalVar(),variableType,true);
            varMap.put(left, variable);
        }

    }
}
