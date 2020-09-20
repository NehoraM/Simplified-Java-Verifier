package oop.ex6.main.SyntaxExceptions.BadInit;
import oop.ex6.main.SyntaxExceptions.SyntaxException;

/**
 * This class return exception if the initialized has bad format
 */
public class BadInitializationFormat extends SyntaxException {
    /**
     * print the error
     */
    public void print(){
        System.err.println("Compilation Error : bad initialization format");
    }
}
