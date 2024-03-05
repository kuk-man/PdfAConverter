package rd.checker.rule;

import java.math.BigDecimal;
import java.util.List;

import model.pojo.common.Amount;
import rd.checker.Checker;

public class Rule_19 extends Checker {
    public void checkAmountAndCurrencyCode(Amount amount, List<String> currencyCodes, String object) {
        String currencyCode = amount.getCurrencyID() == null ? "" : amount.getCurrencyID();
        String value = amount.getValue() == null ? "" : amount.getValue();

        Rule_14 rule_14 = new Rule_14();
        rule_14.checkCurrencyCode(currencyCode, currencyCodes, object + "|CurrencyID");
        if (!rule_14.getError().getErrorMessage().equals(""))
            errors.setErrorMassage(rule_14.getError().getErrorMessage());
        try {
            new BigDecimal(value);
        } catch (NumberFormatException ex) {
            errors.setErrorMassage("Check " + object + ": Invalid Amount (Decimal). Current Amount = " + value);
        }
    }
}
