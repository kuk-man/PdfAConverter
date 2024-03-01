package rd.checker.rule;

import java.math.BigDecimal;
import java.util.List;

import model.ErrorMessage;
import model.pojo.common.Quantity;

public class Rule_18 {
    public void checkUnitCodeAndQuantity(Quantity quantity, List<String> unitCodes, String object, ErrorMessage errors){
        String unitCode = quantity.getUnitCode() == null ? "" : quantity.getUnitCode();
        String value = quantity.getValue() == null ? "" : quantity.getValue();

        if (!unitCodes.contains(unitCode)) {
            errors.setErrorMassage("Check " + object + ": UnitCode is not in the list. Current UnitCode = " + unitCode);
        }

        try {
            new BigDecimal(value);
        } catch (NullPointerException ex) {
            errors.setErrorMassage("Check " + object + ": Invalid Quantity (Decimal). Current Quantity = " + value);
        }
    }
}
