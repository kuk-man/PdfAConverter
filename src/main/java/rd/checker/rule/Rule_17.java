package rd.checker.rule;

import java.util.List;

import model.pojo.common.Description;
import rd.checker.Checker;

public class Rule_17 extends Checker {
    public void checkSpecifiedTradePaymentTerms(Description[] descriptions, List<String> tradePaymentCodes, String object){
        for (Description description : descriptions) {
            // Value
            if (!isNull(new N<>(() -> "" + description.getValue()))) {
                String value = description.getValue() == null ? "" : description.getValue();
                if(!tradePaymentCodes.contains(value)) {
                    errors.setErrorMassage("Check " + object + ": Description is not in the list. Current Description = " + value);
                }
            }
        }
    }
}
