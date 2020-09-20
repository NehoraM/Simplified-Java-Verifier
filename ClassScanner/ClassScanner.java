package oop.ex6.main.ClassScanner;
import oop.ex6.main.dataHolderClasses.*;
import oop.ex6.main.SyntaxExceptions.SyntaxException;
import oop.ex6.main.syntaxValidators.*;
import java.io.*;
import java.util.HashMap;


/**
 * this class scan the class file, check the init and application variable and create function that hold all the
 * function in the class
 */
public class ClassScanner {

    /**
     * the variable map of the class
     */
    private HashMap<String, Variable> varMap; //hold oll the var of the class

    /**
     * the function map of the class-hold all the function in the class and their params
     */
    private HashMap<String, Function> funcMap;
    /**
     * 
     */
    private Function currentFunction;
    private BufferedReader reader;

    /**
     *
     * @param file the file to check
     * @throws IOException if the path illegal
     */
    public ClassScanner(File file) throws IOException {
        Reader temp= new FileReader(file);
        BufferedReader reader=new BufferedReader(temp);
        this.reader=reader;
        varMap = new HashMap<>();
        funcMap = new HashMap<>();
    }


    /**
     * this method check each line of the file
     * @throws IOException if the file unreadable
     * @throws SyntaxException if the syntax illegal
     */
    public void firstRun() throws IOException, SyntaxException {

        int bracketCounter=0;
        String line;
        while((line = reader.readLine()) != null){
            if(line.startsWith("//")){
                // COMMENT OR EMPTY
                continue;
            }

            line = line.trim(); // remove whitespace from the suffix and the prefix
            if(line.isEmpty()){
                continue;
            }
            if(line.equals("}") && bracketCounter == 1){
                bracketCounter--;
                continue;
            }
            if(bracketCounter != 0){
                addLineToFunction(line);
                if(line.contains("{")){
                    bracketCounter++;
                }
                if(line.equals("}")){
                    bracketCounter--;
                }
                continue;
            }
            String linePeek = line;
            // out of a function
            if(line.contains(" ")){
                linePeek = line.substring(0, line.indexOf(" ")).trim();
            }
            if(linePeek.matches(LineSyntaxValidator.getValidType()) || linePeek.equals("final")){
                ClassHandlers.initHandler(line, varMap);
            }
            else if (linePeek.equals("void")){
                currentFunction=ClassHandlers.funcHandler(line.trim() , funcMap);
                bracketCounter++;
            } else {
                if(line.contains("=")){
                    ClassHandlers.appHandler(line, varMap); // application of existing variable.
                } else {
                    throw new SyntaxException();
                }
            }
        }
        if(bracketCounter != 0){ // if there is an unclosed bracket.
            throw new SyntaxException();
        }

    }


    /**
     * add the line to the relevant function list
     * @param line
     */
    private void addLineToFunction(String line){
        if(currentFunction != null){
            currentFunction.getLines().add(line);
        }
    }


    /**
     *
     * @return the variable map
     */
    public HashMap<String, Variable> getVarMap(){ return varMap; }

    /**
     *
     * @return the function map
     */
    public HashMap<String, Function> getFuncMap(){ return funcMap; }


}