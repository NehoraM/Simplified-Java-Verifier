package oop.ex6.main.syntaxValidators;

import oop.ex6.main.SyntaxExceptions.SyntaxException;

/**
 * this class checking the line of "if" and "while"
 */
public class IfWhileValidator extends LineSyntaxValidator {


    /**
     *
     * @param line the line to check
     * @throws SyntaxException
     */
    public void valid(String line) throws SyntaxException {
        line = line.trim();
        if(!(line.matches(VALID_IF_WHILE))) {
            throw new SyntaxException();
        }
    }
}



