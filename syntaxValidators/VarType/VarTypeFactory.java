package oop.ex6.main.syntaxValidators.VarType;

import oop.ex6.main.SyntaxExceptions.BadInit.IllegalType;
import oop.ex6.main.SyntaxExceptions.SyntaxException;

/**
 * this class is a variable type factory
 */
public class VarTypeFactory {
    private static VarType varType;

    /**
     * receives a string and returns the corresponding VarType, throw exception if the string doesn't represents
     * any of the VarTypes flags.
     * @param type the type of the variable
     * @return object of VarType
     * @throws SyntaxException if the variable type illegal
     */
    public static VarType getVarType(String type) throws SyntaxException{
        varType = null;
        switch(type){
            case "int": varType = VarType.INT; break;
            case "String": varType = VarType.STRING; break;
            case "double": varType = VarType.DOUBLE; break;
            case "boolean": varType = VarType.BOOLEAN; break;
            case "char": varType = VarType.CHAR; break;
            default: throw new IllegalType();
        }
        return varType;
    }
}
