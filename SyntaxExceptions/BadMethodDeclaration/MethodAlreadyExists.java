package oop.ex6.main.SyntaxExceptions.BadMethodDeclaration;

public class MethodAlreadyExists extends BadMethodDeclarationFormat {
    public void print(){
        System.err.println("Compilation Error : Method already exists");
    }
}
