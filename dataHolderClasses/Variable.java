package oop.ex6.main.dataHolderClasses;

import oop.ex6.main.syntaxValidators.VarType.VarType;

/**
 * this class hold variable object
 */
public class Variable {

    private VarType type;
    private boolean isFinal;
    private boolean initialized;
    private String varName;


    /**
     *
     * @param varName the variable name
     * @param isFinal if the variable if final
     * @param type the type of the variable
     * @param initialized if the variable initialized
     */
    public Variable(String varName , boolean isFinal, VarType type, boolean initialized){
        this.type = type;
        this.isFinal = isFinal;
        this.initialized= initialized;
        this.varName=varName;
    }

    /**
     * @param initialized  if the variable initialized
     */
    public void setInitialized(boolean initialized){
        this.initialized=initialized;
    }


    /**
     *
     * @return the variable name
     */
    public String getVarName() {
        return varName;
    }

    /**
     *
     * @return if the variable final
     */
    public boolean getIsFinal(){ return isFinal; }

    /**
     *
     * @return the type of the variable
     */
    public VarType getType() { return type; }

    /**
     *
     * @return if the variable initialized
     */
    public boolean isInitialized(){
        return initialized;
    }
}