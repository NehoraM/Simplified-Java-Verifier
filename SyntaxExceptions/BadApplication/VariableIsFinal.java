package oop.ex6.main.SyntaxExceptions.BadApplication;

/**
 * return exception if the variable if final
 */
public class VariableIsFinal extends BadApplicationFormat {
    /**
     * print the error
     */
    public void print(){
        System.err.println("Compilation Error : final variable cannot be manipulated");
    }
}
