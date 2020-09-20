package oop.ex6.main.syntaxValidators.VarType;

import oop.ex6.main.SyntaxExceptions.BadInit.IllegalValueType;
import oop.ex6.main.SyntaxExceptions.SyntaxException;
import oop.ex6.main.syntaxValidators.SyntaxValidator;

/**
 * this enum class holds all the possible variable types and their regex's.
 */
public enum VarType implements SyntaxValidator {

    INT("(-)?(\\d)+"),
    DOUBLE("(-)?(\\d)+(\\.(\\d)*)?"),
    STRING("(\")(.*)(\")"),
    BOOLEAN("(true)|(false)|(-)?(\\d)+(\\.(\\d)*)?"),
    CHAR("\'.\'");

    private String validatorRegex;

    VarType(String regex){
        this.validatorRegex = regex;
    }

    /**
     *
     * @param line the line to check
     * @throws SyntaxException if the line illegal
     */
    public void valid(String line)  throws SyntaxException{
        // fill to work with given regex
        if(!line.matches(validatorRegex)){
            throw new IllegalValueType();
        }
    }


}
