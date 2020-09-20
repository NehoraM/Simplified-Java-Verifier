package oop.ex6.main.syntaxValidators;
import oop.ex6.main.SyntaxExceptions.BadMethodCall.BadMethodCallFormat;
import oop.ex6.main.SyntaxExceptions.SyntaxException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * this class check line of function call
 */
public class FunctionCallValidator extends LineSyntaxValidator {

    private final int FUNC_NAME_INDEX = 1;
    private final int FUNC_PARAMS_INDEX = 2;
    private String funcName;
    private ArrayList<String> params;

    /**
     * @param line the line to check
     * @throws SyntaxException if the line illegal
     */
    public void valid(String line) throws SyntaxException {

        Pattern p = Pattern.compile(VALID_FUNCTION_CALL);
        Matcher m = p.matcher(line);
        if(!m.matches()){
            throw new BadMethodCallFormat();
        }
        funcName= m.group(FUNC_NAME_INDEX);
        String paramsString=m.group(FUNC_PARAMS_INDEX);

        params = new ArrayList<>();
        if(!paramsString.matches("( )*")){
            String[] temp = paramsString.split(",");
            for(String param : temp){
                params.add(param);
            }
        }

    }

    /**
     *
     * @return the function name
     */
    public String getFuncName(){
        return funcName;
    }

    /**
     *
     * @return the parameters array of the function
     */
    public ArrayList<String> getParams(){
        return params;
    }
}
