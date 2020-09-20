package oop.ex6.main;

import oop.ex6.main.ClassScanner.ClassScanner;
import oop.ex6.main.FunctionCompiler.FunctionCompiler;
import oop.ex6.main.dataHolderClasses.*;
import oop.ex6.main.SyntaxExceptions.SyntaxException;

import java.io.*;
import java.util.HashMap;

/**
 * the main class of the project, it receives a file and attempt to compile it, throws informative exception if
 * unsuccessful.
 */
public class Sjavac {
    private static final int COMPILATION_SUCCEED = 0;
    private static final int COMPILATION_FAILED_SYNTAX = 1;
    private static final int COMPILATION_FAILED_IO = 2;

    public static void main(String[] args){

        String path = args[0];

        try{
            File file = new File(path);
            ClassScanner classScanner = new ClassScanner(file);
            classScanner.firstRun();

            HashMap<String, Variable> varMap = classScanner.getVarMap();
            HashMap<String, Function> funcMap =  classScanner.getFuncMap();

            for (Function function : funcMap.values()){
                FunctionCompiler.compileFunction(funcMap , varMap , function);
            }

            System.out.println(COMPILATION_SUCCEED); // compiled successfully

        } catch(IOException exception){
            System.err.println(exception);
            System.out.println(COMPILATION_FAILED_IO); // IO error
        } catch (SyntaxException exception){
            System.err.println(exception);
            System.out.println(COMPILATION_FAILED_SYNTAX); // Code writted not according to syntax
        }

    }
}