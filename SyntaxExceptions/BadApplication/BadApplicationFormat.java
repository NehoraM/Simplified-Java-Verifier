package oop.ex6.main.SyntaxExceptions.BadApplication;
import oop.ex6.main.SyntaxExceptions.SyntaxException;

/**
 * This class return exception if there bad application format
 */
public class BadApplicationFormat extends SyntaxException {

    /**
     * print the error
     */
    public void print(){
        System.err.println("Compilation Error : bad application format");
    }
}
