package oop.ex6.main.SyntaxExceptions.BadMethodDeclaration;

public class IllegalParameter extends BadMethodDeclarationFormat {
    public void print(){
        System.err.println("Compilation Error : method's parameter is not according to format");
    }
}
