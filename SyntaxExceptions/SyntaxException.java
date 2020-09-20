package oop.ex6.main.SyntaxExceptions;

/**
 * this class return exception if there syntax exception
 */
public class SyntaxException extends Exception {

    /**
     * print the error
     */
    public void  print(){
        System.err.println("Compilation Error: Syntax Error");
    }

}