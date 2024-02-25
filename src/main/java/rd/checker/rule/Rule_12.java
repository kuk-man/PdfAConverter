package rd.checker.rule;

import java.util.List;

import model.ErrorMessage;

public class Rule_12 {
    public void checkTradeTermCode(String incotermCode, List<String> incotermCodes, String object, ErrorMessage errors){
        if (!incotermCodes.contains(incotermCode))
            errors.setErrorMassage("Check " + object + "|DeliveryTypeCode: Invalid DeliveryTypeCode is not in the list. Current Trade Term Code = " + incotermCode);
    }
}
