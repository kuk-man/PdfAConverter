package rd.checker.rule;

import java.util.regex.Pattern;

import rd.checker.Checker;

public class Rule_22 extends Checker {
    public void checkGlobalID(String globalID, String object){
        String regex;
        Pattern pattern;

        regex = "^.{1,70}$";
        pattern = Pattern.compile(regex);
        if (!pattern.matcher(globalID).matches()) {
            errors.setErrorMassage("Check " + object + ": Invalid GlobalID (<=70 characters). Current GlobalID = " + globalID);
        }
    }
}
