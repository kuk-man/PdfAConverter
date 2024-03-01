package rd.checker.rule;

import java.math.BigDecimal;
import java.util.List;

import model.ErrorMessage;
import model.pojo.common.Amount;

public class Rule_19 {
    public void checkAmountAndCurrencyCode(Amount amount, List<String> currencyCodes, String object, ErrorMessage errors) {
        String currencyCode = amount.getCurrencyID() == null ? "" : amount.getCurrencyID();
        String value = amount.getValue() == null ? "" : amount.getValue();

        (new Rule_14()).checkCurrencyCode(currencyCode, currencyCodes, object + "|CurrencyID", errors);
        
        try {
            new BigDecimal(value);
        } catch (NullPointerException ex) {
            errors.setErrorMassage("Check " + object + ": Invalid Amount (Decimal). Current Amount = " + value);
        }
    }
}
