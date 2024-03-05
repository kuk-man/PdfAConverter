package rd.checker.rule;

import java.util.List;

import rd.checker.Checker;

public class Rule_15 extends Checker {
    public void checkTaxCode(String taxCode, List<String> taxCodes, String object){
        if (!taxCodes.contains(taxCode))
            errors.setErrorMassage("Check " + object + ": TypeCode is not in the list. Current TypeCode = " + taxCode);
    }
}
