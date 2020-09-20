package oop.ex6.main.SyntaxExceptions.BadMethodCall;

/**
 * this class return exception if the parameters illegal
 */
public class WrongParameters extends BadMethodCallFormat {
    /**
     * print the error
     */
    public void print(){
        System.err.println("Compilation Error : calling method with illegal parameters");
    }
}
