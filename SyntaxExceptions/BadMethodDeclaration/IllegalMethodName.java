package oop.ex6.main.SyntaxExceptions.BadMethodDeclaration;

public class IllegalMethodName extends BadMethodDeclarationFormat {
    public void print(){
        System.err.println("Compilation Error : method's name is not according to format");
    }
}
