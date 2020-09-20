package oop.ex6.main.SyntaxExceptions.BadInit;

import oop.ex6.main.SyntaxExceptions.BadApplication.BadApplicationFormat;

/**
 * this class return exception if the value has illegal type
 */
public class IllegalValueType extends BadApplicationFormat {
    /**
     * print the error
     */
    public void print(){
        System.err.println("Compilation Error : given value has bad type");
    }
}
