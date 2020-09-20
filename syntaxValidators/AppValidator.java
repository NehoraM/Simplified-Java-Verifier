package oop.ex6.main.syntaxValidators;
import oop.ex6.main.SyntaxExceptions.BadApplication.BadApplicationFormat;
import oop.ex6.main.SyntaxExceptions.SyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * this class checking application line
 */
public class AppValidator extends LineSyntaxValidator {

    private String varName;
    private String valName;


    /**
     * @param line the line to check
     * @throws SyntaxException if the line illegal
     */
    public void valid(String line) throws SyntaxException {
        Pattern p = Pattern.compile(VALID_APP);
        Matcher m = p.matcher(line);
        if(!m.matches()){
            throw new BadApplicationFormat();
        }
        varName = m.group(1);
        valName = m.group(2);
    }


    /**
     * @return the variable name
     */
    public String getVarName(){
        return varName;
    }

    /**
     * @return the value name
     */
    public String getValName(){
        return valName;
    }

}
