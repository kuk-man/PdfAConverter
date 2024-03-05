package rd.checker.rule;

import java.util.List;

import rd.checker.Checker;

public class Rule_12 extends Checker {
    public void checkTradeTermCode(String incotermCode, List<String> incotermCodes, String object){
        if (!incotermCodes.contains(incotermCode))
            errors.setErrorMassage("Check " + object + "|DeliveryTypeCode: Invalid DeliveryTypeCode is not in the list. Current Trade Term Code = " + incotermCode);
    }
}
