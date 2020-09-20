package oop.ex6.main.syntaxValidators;

import oop.ex6.main.SyntaxExceptions.SyntaxException;

/**
 * this class is an interface of syntax validator
 */
public interface SyntaxValidator{

    /**
     * @param line the line to check
     * @throws SyntaxException if the line illegal
     */
    void valid(String line) throws SyntaxException;

}