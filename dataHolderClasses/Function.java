package oop.ex6.main.dataHolderClasses;
import oop.ex6.main.syntaxValidators.VarType.*;
import oop.ex6.main.SyntaxExceptions.SyntaxException;
import oop.ex6.main.SyntaxExceptions.BadMethodDeclaration.*;
import oop.ex6.main.SyntaxExceptions.BadMethodCall.*;

import java.util.ArrayList;

/**
 * this class hold the function object
 */
public class Function {

    /**
     * array list of the parameters
     */
    private ArrayList<Variable> paramsArray;

    /**
     * array list of the function line
     */
    private ArrayList<String> funcLines= new ArrayList<>();

    /**
     *
     * @param params array list of the function line
     * @throws SyntaxException if there syntax error
     */
    public Function(ArrayList<String> params) throws SyntaxException {
       paramsArray = new ArrayList<>();
           for (String param : params) {
               param = param.trim();

               boolean isFinal = false;
               if (param.startsWith("final")) {
                   param = param.substring(param.indexOf(" ")).trim();
                   isFinal = true;
               }

               String[] temp = param.split("( )(\\s)*");

               if (temp.length != 2) {
                   // illegal parameter format in function
                   throw new WrongParameters(); }

               String paramName = temp[1];
               VarType paramType = VarTypeFactory.getVarType(temp[0]);

               for(Variable exisitingParam: paramsArray){
                   if(paramName.equals(exisitingParam.getVarName())){
                       throw new IllegalParameter();
                   }
               }

               Variable variable = new Variable(paramName, isFinal, paramType, true);
               paramsArray.add(variable);

           }
    }

    /**
     *
     * @return the parameters array
     */
    public ArrayList<Variable> getParamsArray(){
        return paramsArray;
    }

    /**
     *
     * @return the function line array
     */
    public ArrayList<String> getLines(){
        return funcLines;
    }
}
