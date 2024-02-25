package rd.checker.rule;

import java.util.regex.Pattern;

import model.ErrorMessage;

public class Rule_22 {
    public void checkGlobalID(String globalID, String object, ErrorMessage errors){
        String regex;
        Pattern pattern;

        regex = "^.{1,70}$";
        pattern = Pattern.compile(regex);
        if (!pattern.matcher(globalID).matches()) {
            errors.setErrorMassage("Check " + object + ": Invalid GlobalID (<=70 characters). Current GlobalID = " + globalID);
        }
    }
}
