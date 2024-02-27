package rd.checker.format;

import java.util.regex.Pattern;

import model.ErrorMessage;
import rd.checker.Checker;

public class Format  extends Checker {
    public void checkPercentageRate(String percentageRage, String object, ErrorMessage errors) {
        // ???
        // fractionDigits: 10
        // totalDigits: 11
        // baseValue: 100.0
        
        try {
            Float.parseFloat(percentageRage);
        } catch (NumberFormatException ex) {
            errors.setErrorMassage("Check " + object + ": Invalid PercentageRage (Float). Current PercentageRage = " + percentageRage);
        }
    }

    public void checkAmount(String amount, String object, ErrorMessage errors) {
        // ???
        // fractionDigits: 5
        // minInclusive: 0
        // totalDigits: 18
        
        try {
            Float.parseFloat(amount);
        } catch (NumberFormatException ex) {
            errors.setErrorMassage("Check " + object + ": Invalid Amount (Float). Current Amount = " + amount);
        }
    }

    public void checkQuantity(String quantity, String object, ErrorMessage errors) {
        // ???
        // fractionDigits: 17
        // totalDigits: 18

        try {
            Float.parseFloat(quantity);
        } catch (NumberFormatException ex) {
            errors.setErrorMassage("Check " + object + ": Invalid Quantity (Float). Current Quantity = " + quantity);
        }
    }

    public void checkIsoDateTime(String datetime, String object, ErrorMessage errors) {
        String regex = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}";
        Pattern pattern = Pattern.compile(regex);

        if (!pattern.matcher(datetime).matches())
            errors.setErrorMassage("Check " + object + ": Invalid DateTime (YYYY-mm-DDTHH:MM:SS). Current DateTime = " + datetime + "");
    }

    public void checkTrueFalse(String trueFalse, String object, ErrorMessage errors) {
        if (!trueFalse.equals("True") && !trueFalse.equals("False"))
            errors.setErrorMassage("Check " + object + ": Invalid True/False. Current True/False = " + trueFalse + "");
    }

    public void checkPhoneNumber(String phoneNumber, String object, ErrorMessage errors) {
        String regex = "\\+[0-9]{1,3}-[0-9()+\\-]{1,30}";
        Pattern pattern = Pattern.compile(regex);

        if (!pattern.matcher(phoneNumber).matches())
            errors.setErrorMassage("Check " + object + ": Invalid phoneNumber (+1-(465)432-123). Current PhoneNumber = " + phoneNumber + "");
    }

    private void checkText(String text, int maxLength, String object, ErrorMessage errors) {
        if (!(text.length() > 1 && text.length() <= maxLength))
            errors.setErrorMassage("Check " + object + ": Invalid Text (> 1 & <= " + maxLength + " characters). Current Text = " + text + "");
    }

    public void check16Text(String text, String object, ErrorMessage errors) {
        checkText(text, 16, object, errors);
    }

    public void check35Text(String text, String object, ErrorMessage errors) {
        checkText(text, 35, object, errors);
    }

    public void check70Text(String text, String object, ErrorMessage errors) {
        checkText(text, 70, object, errors);
    }

    public void check140Text(String text, String object, ErrorMessage errors) {
        checkText(text, 140, object, errors);
    }
    
    public void check256Text(String text, String object, ErrorMessage errors) {
        checkText(text, 256, object, errors);
    }

    public void check500Text(String text, String object, ErrorMessage errors) {
        checkText(text, 500, object, errors);
    }

    public void check2048Text(String text, String object, ErrorMessage errors) {
        checkText(text, 2048, object, errors);
    }
}
