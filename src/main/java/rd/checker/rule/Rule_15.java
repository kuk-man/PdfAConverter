package rd.checker.rule;

import java.util.List;

import model.ErrorMessage;

public class Rule_15 {
    public void checkTaxCode(String taxCode, List<String> taxCodes, String object, ErrorMessage errors){
        if (!taxCodes.contains(taxCode))
            errors.setErrorMassage("Check " + object + ": TypeCode is not in the list. Current TypeCode = " + taxCode);
    }
}
