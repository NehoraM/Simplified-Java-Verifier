package oop.ex6.main.FunctionCompiler;
import oop.ex6.main.SyntaxExceptions.MissingReturnStatement;
import oop.ex6.main.SyntaxExceptions.SyntaxException;
import oop.ex6.main.dataHolderClasses.*;
import oop.ex6.main.syntaxValidators.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * this class compile each function
 */
public class FunctionCompiler {

    private static HashMap<String, Function> funcMap;
    private static ArrayList<String> functionStream;
    private static String line;
    private static final String RETURN_REGEX = "( )*(return)( )*(;)";
    private static final String IF = "if";
    private static final String WHILE= "while";


    /**
     * @param function   the lines of this function
     * @param recfuncMap hold all the func in the class and their params
     * @param varMap     hold all the var of the class
     */
    public static void compileFunction(HashMap<String, Function> recfuncMap, HashMap<String, Variable> varMap,
                                       Function function) throws SyntaxException {

        HashMap<String, Variable> globalVarMap = new HashMap<>(varMap);
        HashMap<String, Variable> localVarMap = new HashMap<>();

        funcMap = recfuncMap;
        functionStream = function.getLines();

        for (Variable variable : function.getParamsArray()) {
            localVarMap.put(variable.getVarName(), variable);
        }


        compileFunctionInterior(globalVarMap, localVarMap, functionStream.iterator());
        if(!functionStream.get(functionStream.size()-1).matches(RETURN_REGEX)){
            throw new MissingReturnStatement();
        }

    }


    /**
     *
     * @param globalVarMap the global variable
     * @param localVarMap the local variable
     * @param iterator the iterator
     * @throws SyntaxException if the line illegal
     */
    private static void compileFunctionInterior(HashMap<String, Variable> globalVarMap, HashMap<String,
            Variable> localVarMap, Iterator<String> iterator) throws SyntaxException {

        while (iterator.hasNext()) {

            line = iterator.next();
            if (line.startsWith("//") || line.matches(RETURN_REGEX)) {
                // COMMENT OR EMPTY
                continue;
            }

            line = line.trim(); // remove whitespace from the suffix and the prefix
            if(line.isEmpty()){
                continue;
            }

            if (line.startsWith(IF) || line.startsWith(WHILE)) {
                HashMap<String, Variable> newMap = localVarMap; // creates a map combining the local vars and the
                                                                // global vars to be used as the global var for
                                                                // the next function call (recursively).
                newMap.putAll(globalVarMap);
                FunctionHandlers.ifWhileHandler(line, newMap);
                compileFunctionInterior(newMap, new HashMap<>(), iterator);
                if(!iterator.hasNext() || !line.equals("}")){
                    throw new SyntaxException();
                }
                continue;
            }

            if (line.contains("}")) { // recursion stopping mechanism
                break;
            }

            String linePeek;
            if (line.contains(" ")) {
                linePeek = line.substring(0, line.indexOf(" ")).trim();
            } else {
                linePeek = line;
            }
            lineCategorize(linePeek, globalVarMap, localVarMap);
        }

    }

    /**
     * sends the current line into the currect handler according to its beginning.
     * @param linePeek the beginning of the line
     * @param globalVarMap the global variable map
     * @param localVarMap the local variable map
     * @throws SyntaxException
     */
    private static void lineCategorize(String linePeek, HashMap<String, Variable> globalVarMap,
                                       HashMap<String, Variable> localVarMap) throws SyntaxException{
        if(linePeek.matches(LineSyntaxValidator.getValidType()) || linePeek.equals("final")){
            FunctionHandlers.initHandler(line, globalVarMap, localVarMap);
        } else {
            if(line.contains("=")){
                FunctionHandlers.appHandler(line, localVarMap, globalVarMap);
            } else {
                FunctionHandlers.funcImpHandler(line, localVarMap, funcMap);
            }
        }
    }

}
