package rd.checker.rule;

import java.util.List;

import model.ErrorMessage;

public class Rule_14 {
    public void checkCurrencyCode(String currencyCode, List<String> currencyCodes, String object, ErrorMessage errors){
        if (!currencyCodes.contains(currencyCode))
            errors.setErrorMassage("Check " + object + ": CurrencyCode is not in the list. Current CurrencyCode = " + currencyCode);
    }
}
