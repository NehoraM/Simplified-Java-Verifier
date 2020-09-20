package oop.ex6.main.SyntaxExceptions.BadInit;

/**
 * return exception if variable has type illegal
 */
public class IllegalType extends BadInitializationFormat {

    /**
     * print the error
     */
    public void print(){
        System.err.println("Compilation Error : illegal type");
    }
}
