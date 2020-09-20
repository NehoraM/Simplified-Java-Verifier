package oop.ex6.main.syntaxValidators;

/**
 * this class hold all the Syntax validator
 */
public abstract class LineSyntaxValidator implements SyntaxValidator {
    protected static final String FUNC_FORMAT = "(void)( )(\\s)*([a-zA-Z]\\w*)( |\\s)*\\(([^)]*)\\)( |\\s)*(\\{)";
    protected static final String VALID_TYPE = "(int|char|double|String|boolean)";
    protected static final String VALID_VAR_NAME = "((_(\\w)+)|([a-zA-Z](\\w)*))+";
    protected static final String VALID_IF_WHILE = "((if|while)( |\\s)*\\((.)*\\)( |\\s)*\\{)";
    protected static final String VALID_APP = "(_\\w+|[a-zA-Z]\\w*) *= *(.)* *;";
    protected static final String VALID_FUNCTION_CALL = "(.+)\\((.*)\\);";

    /**
     * @return the valid type regex
     */
    public static String getValidType(){
        return VALID_TYPE;
    }
}
