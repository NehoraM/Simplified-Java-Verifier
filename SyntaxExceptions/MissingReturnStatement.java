package oop.ex6.main.SyntaxExceptions;

/**
 * this class return exception if there is no return statement
 */
public class MissingReturnStatement extends SyntaxException {
    /**
     * print the error
     */
    public void print(){
        System.err.println("Compilation Error: missing return statement");
    }
}
