package oop.ex6.main.SyntaxExceptions.BadMethodDeclaration;

import oop.ex6.main.SyntaxExceptions.SyntaxException;

public class BadMethodDeclarationFormat extends SyntaxException {
    public void print(){
        System.err.println("Compilation Error : bad method declaration format");
    }
}
