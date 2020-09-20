package oop.ex6.main.SyntaxExceptions.BadInit;

public class IllegalVariableName extends BadInitializationFormat {
    public void print(){
        System.err.println("Compilation Error : variable name not according to format");
    }
}
