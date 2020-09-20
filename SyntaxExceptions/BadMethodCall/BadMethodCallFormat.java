package oop.ex6.main.SyntaxExceptions.BadMethodCall;
import oop.ex6.main.SyntaxExceptions.SyntaxException;

/**
 * this class return exception if there illegal method call
 */
public class BadMethodCallFormat extends SyntaxException {

    /**
     * print the error
     */
    public void print(){
        System.err.println("Compilation Error : bad method call format");
    }
}
