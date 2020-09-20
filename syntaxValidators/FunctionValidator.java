package oop.ex6.main.syntaxValidators;
import oop.ex6.main.SyntaxExceptions.BadMethodDeclaration.BadMethodDeclarationFormat;
import oop.ex6.main.SyntaxExceptions.BadMethodDeclaration.IllegalParameter;
import oop.ex6.main.SyntaxExceptions.SyntaxException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * this class checking function syntax validator
 */
public class FunctionValidator extends LineSyntaxValidator{
    private String funcName;
    private ArrayList<String> paramsArr;
    private final int FUNC_NAME_INDEX = 4;
    private final String FINAL_REGEX="(final( )+)?((";
    private final String WHITE_SPACE= ")( )(\\s)*(";

    /**
     *
     * @param line the line to check
     * @throws SyntaxException if the line illegal
     */
    public void valid(String line) throws SyntaxException {
        Pattern p = Pattern.compile(FUNC_FORMAT);
        Matcher m = p.matcher(line);
        if(!m.matches()){
            throw new BadMethodDeclarationFormat();
        }

        funcName = m.group(FUNC_NAME_INDEX); // group 4 captures the method's name.

        String params = line.substring(line.indexOf("(") + 1, line.indexOf(")"));
        paramsArr = new ArrayList<>();
        // check each param section format
        if(!params.matches("(\\s)*")){ // if it doesnt contain only white space
            String[] temp = params.split(",");
            for(String param: temp){
                paramsArr.add(param.trim());
            }
            for(String param: paramsArr){
                if(!param.matches(FINAL_REGEX + VALID_TYPE + WHITE_SPACE + VALID_VAR_NAME + "))")){
                    throw new IllegalParameter();
                }
            }
        }
    }

    /**
     * @return the function name
     */
    public String getFuncName(){ return funcName; }

    /**
     * @return array list of parameters
     */
    public ArrayList<String> getParamsArr(){ return paramsArr; }
}
