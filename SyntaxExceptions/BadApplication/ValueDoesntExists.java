package oop.ex6.main.SyntaxExceptions.BadApplication;


/**
 * This class return exception if the variable doesnt init
 */
public class ValueDoesntExists extends BadApplicationFormat {
    /**
     * print the error
     */
    public void print(){
        System.err.println("Compilation Error : given value doesn't exists");
    }
}
