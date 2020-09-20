package oop.ex6.main.SyntaxExceptions.BadInit;

/**
 * this class return exception if initialized final variable
 */
public class UninitializedFinal extends BadInitializationFormat {

    /**
     * print the error
     */
    public void print(){
        System.err.println("Compilation Error: uninitialized final variable");;
    }
}
