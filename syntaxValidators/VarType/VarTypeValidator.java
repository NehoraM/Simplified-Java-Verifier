package oop.ex6.main.syntaxValidators.VarType;

import oop.ex6.main.SyntaxExceptions.BadInit.IllegalValueType;
import oop.ex6.main.SyntaxExceptions.SyntaxException;

/**
 * this class check for the relations between types
 */
public class VarTypeValidator {

    /**
     * this function receives 2 variable's types and checks if valueType can be applied to variableType.
     * in other words, if valueType can be putted into variableType.
     * @param varType the var type object
     * @param valueType the value type object
     * @throws SyntaxException if the type illegal
     */
    public static void typeValidator(VarType varType, VarType valueType) throws SyntaxException {
        switch(varType){
            case BOOLEAN:
                if(valueType != VarType.BOOLEAN && valueType != VarType.INT && valueType != VarType.DOUBLE){
                    throw new IllegalValueType();
                }
                break;
            case DOUBLE:
                if(valueType != VarType.DOUBLE && valueType != VarType.INT){
                    throw new IllegalValueType();
                }
                break;
            default:
                if(varType != valueType){
                    throw new IllegalValueType();
                }
        }
    }
}
