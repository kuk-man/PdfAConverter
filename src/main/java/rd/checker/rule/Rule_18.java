package rd.checker.rule;

import java.util.List;

import model.ErrorMessage;
import model.pojo.common.Quantity;

public class Rule_18 {
    public void checkUnitCodeAndQuantity(Quantity quantity, List<String> unitCodes, String object, ErrorMessage errors){
        String unitCode = quantity.getUnitCode() == null ? "" : quantity.getUnitCode();
        String value = quantity.getUnitCode() == null ? "" : quantity.getValue();

        if (!unitCodes.contains(unitCode)) {
            errors.setErrorMassage("Check " + object + ": UnitCode is not in the list. Current UnitCode = " + unitCode);
        }

        try {
            Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            errors.setErrorMassage("Check " + object + ": Invalid Quantity (Numeric). Current Quantity = " + value);
        }
    }
}
