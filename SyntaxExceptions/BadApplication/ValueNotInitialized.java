package oop.ex6.main.SyntaxExceptions.BadApplication;

/**
 * This class return exception if the value not initialized
 */
public class ValueNotInitialized extends BadApplicationFormat {

    /**
     * print the error
     */
    public void print(){
        System.err.println("Compilation Error : given value isn't initialized");
    }
}
