package oop.ex6.main.syntaxValidators;

import oop.ex6.main.syntaxValidators.VarType.*;
import oop.ex6.main.dataHolderClasses.Variable;
import oop.ex6.main.SyntaxExceptions.SyntaxException;
import oop.ex6.main.SyntaxExceptions.BadInit.IllegalVariableName;
import oop.ex6.main.SyntaxExceptions.BadInit.UninitializedFinal;

import java.util.ArrayList;

/**
 * this class checking the validator initialized
 */
public class InitValidator extends LineSyntaxValidator {


    private ArrayList<Variable> variables = new ArrayList<>();
    private ArrayList<String> appliedVars = new ArrayList<>();
    private VarType varType;
    private boolean finalVar = false;

    /**
     *
     * @param line the line to check
     * @throws SyntaxException
     */
    public void valid(String line) throws SyntaxException {

        String[] varNames;
        String type;

        if (line.startsWith("final")) {
            finalVar = true;
            if(!line.contains(" ")) {
                throw new SyntaxException();
            }
            line = line.substring(line.indexOf(" ")).trim();
        }

        if (line.endsWith(";")) {
            line = line.substring(0, line.lastIndexOf(";")).trim();
        } else {
            throw new SyntaxException();
        }

        if(line.endsWith(",")){
            throw new SyntaxException();
        }

        if(!line.contains(" ")) {
            throw new SyntaxException();
        }
        type = line.substring(0, line.indexOf(" ")).trim();
        varType = VarTypeFactory.getVarType(type);
        line = line.substring(line.indexOf(" ")).trim();

        varNames = line.split(",");

        initValidator(varNames);
    }

    /**
     *
     * @param varNames string array of the variable
     * @throws SyntaxException if the line illegal
     */
    private void initValidator(String varNames[]) throws SyntaxException {
        for (String var : varNames) {
            var = var.trim();
            String left;
            String right;
            if (var.contains("=")) {
                left = var.substring(0, var.indexOf("=")).trim();
                right = var.substring(var.indexOf("=")+1).trim();
                if (!left.matches(VALID_VAR_NAME)) {
                    throw new IllegalVariableName();
                }

                try {
                    varType.valid(right);
                    Variable variable = new Variable(left, finalVar, varType, true);
                    variables.add(variable);
                } catch (SyntaxException exception) {
                    appliedVars.add(var);
                }
            } else {
                if (!var.matches(VALID_VAR_NAME)) {
                    throw new IllegalVariableName();
                }
                if(finalVar){
                    throw new UninitializedFinal();
                }
                Variable variable = new Variable(var, finalVar, varType, false);
                variables.add(variable);
            }
        }
    }


    /**
     *
     * @return the array list of the variables
     */
    public ArrayList<Variable> getVariables(){ return variables;}

    /**
     *
     * @return the applied Variables
     */
    public ArrayList<String> getAppliedVars() {
        return appliedVars;
    }

    /**
     * @return if the variable is final
     */
    public boolean isFinalVar() {
        return finalVar;
    }

    /**
     * @return the variable type
     */
    public VarType getVarType() {
        return varType;
    }
}