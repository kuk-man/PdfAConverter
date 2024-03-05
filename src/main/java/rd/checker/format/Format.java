package rd.checker.format;

import java.math.BigDecimal;
import java.util.regex.Pattern;

import rd.checker.Checker;

public class Format extends Checker {
    public void checkPercentageRate(String percentageRage, String object) {
        // ???
        // fractionDigits: 10
        // totalDigits: 11
        // baseValue: 100.0
        
        try {
            new BigDecimal(percentageRage);
        } catch (NumberFormatException ex) {
            errors.setErrorMassage("Check " + object + ": Invalid PercentageRage (Decimal). Current PercentageRage = " + percentageRage);
        }
    }

    public void checkAmount(String amount, String object) {
        // ???
        // fractionDigits: 5
        // minInclusive: 0
        // totalDigits: 18
        
        try {
            new BigDecimal(amount);
        } catch (NumberFormatException ex) {
            errors.setErrorMassage("Check " + object + ": Invalid Amount (Decimal). Current Amount = " + amount);
        }
    }

    public void checkQuantity(String quantity, String object) {
        // ???
        // fractionDigits: 17
        // totalDigits: 18

        try {
            new BigDecimal(quantity);
        } catch (NumberFormatException ex) {
            errors.setErrorMassage("Check " + object + ": Invalid Quantity (Decimal). Current Quantity = " + quantity);
        }
    }

    public void checkIsoDateTime(String datetime, String object) {
        // ???
        // all ISO datetime

        String regex = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}";
        Pattern pattern = Pattern.compile(regex);

        if (!pattern.matcher(datetime).matches())
            errors.setErrorMassage("Check " + object + ": Invalid DateTime (YYYY-mm-DDTHH:MM:SS). Current DateTime = " + datetime);
    }

    public void checkTrueFalse(String trueFalse, String object) {
        if (!trueFalse.equals("True") && !trueFalse.equals("False"))
            errors.setErrorMassage("Check " + object + ": Invalid True/False. Current True/False = " + trueFalse);
    }

    public void checkPhoneNumber(String phoneNumber, String object) {
        String regex = "\\+[0-9]{1,3}-[0-9()+\\-]{1,30}";
        Pattern pattern = Pattern.compile(regex);

        if (!pattern.matcher(phoneNumber).matches())
            errors.setErrorMassage("Check " + object + ": Invalid phoneNumber (+1-(465)432-123). Current PhoneNumber = " + phoneNumber);
    }

    public void checkText(String text, int maxLength, String object) {
        if (!(text.length() > 1 && text.length() <= maxLength))
            errors.setErrorMassage("Check " + object + ": Invalid Text (> 1 & <= " + maxLength + " characters). Current Text = " + text);
    }

    public void check16Text(String text, String object) {
        checkText(text, 16, object);
    }

    public void check35Text(String text, String object) {
        checkText(text, 35, object);
    }

    public void check70Text(String text, String object) {
        checkText(text, 70, object);
    }

    public void check140Text(String text, String object) {
        checkText(text, 140, object);
    }
    
    public void check256Text(String text, String object) {
        checkText(text, 256, object);
    }

    public void check500Text(String text, String object) {
        checkText(text, 500, object);
    }

    public void check2048Text(String text, String object) {
        checkText(text, 2048, object);
    }
}
