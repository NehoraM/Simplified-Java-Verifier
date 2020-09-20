package oop.ex6.main.SyntaxExceptions.BadInit;

/**
 * this class return exception if the variable name already exist
 */
public class VariableAlreadyExists extends BadInitializationFormat {
    /**
     * print the error
     */
    public void print() {
        System.err.println("Compilation Error : Variable already exists");
    }
}
