package oop.ex6.main.SyntaxExceptions.BadApplication;

/**
 * return error if the variable not exist
 */
public class VariableDoesntExists extends BadApplicationFormat {
    /**
     * print the error
     */
    public void print(){
        System.err.println("Compilation Error : variable doesn't exists");
    }
}
