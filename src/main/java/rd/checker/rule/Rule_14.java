package rd.checker.rule;

import java.util.List;

import rd.checker.Checker;

public class Rule_14 extends Checker {
    public void checkCurrencyCode(String currencyCode, List<String> currencyCodes, String object){
        if (!currencyCodes.contains(currencyCode))
            errors.setErrorMassage("Check " + object + ": CurrencyCode is not in the list. Current CurrencyCode = " + currencyCode);
    }
}
