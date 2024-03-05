package rd.checker.rule;

import java.util.regex.Pattern;

import rd.checker.Checker;

public class Rule_5 extends Checker {
    public void checkDateTimeFormat(String datetime, String object) {
        // ???
        // ISO datetime

        String regex = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}";
        Pattern pattern = Pattern.compile(regex);

        if (!pattern.matcher(datetime).matches())
            errors.setErrorMassage("Check " + object + ": Invalid DateTime (YYYY-mm-DDTHH:MM:SS). Current DateTime = " + datetime);
    }
}
