package oop.ex6.main.SyntaxExceptions.BadMethodCall;

/**
 * this class return exception if the method doesn't exists
 */
public class MethodDoesntExists extends BadMethodCallFormat {

    /**
     * print the error
     */
    public void print(){
        System.err.println("Compilation Error : method doesn't exists");
    }
}
