package rd.checker.rule;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import connector.DatabaseConnecter;
import model.pojo.common.Quantity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class Rule_18Test {
    String object;
    String unitCode;
    String value;

    DatabaseConnecter dbCon = new DatabaseConnecter("connection");
    List<String> unitCodes = dbCon.getUnitCodes();

    @BeforeEach
    private void setBeforEach() {
        object = "Test";
    }

    @AfterEach
    private void setAfterEach() {
        object = "";
    }
    
    @Test
    void testCheckUnitCodeAndQuantity() {
        // Arrange
        Rule_18 rule;
        Quantity quantity = new Quantity();

        // Act & Assert
        unitCode = "number_of_packs";
        value = "10";
        quantity.setUnitCode(unitCode);
        quantity.setValue(value);
        rule = new Rule_18();
        rule.checkUnitCodeAndQuantity(quantity, unitCodes, object);
        assertEquals("", rule.getError().getErrorMessage());

        unitCode = "xyz";
        value = "10";
        quantity.setUnitCode(unitCode);
        quantity.setValue(value);
        rule = new Rule_18();
        rule.checkUnitCodeAndQuantity(quantity, unitCodes, object);
        assertEquals("Check " + object + ": UnitCode is not in the list. Current UnitCode = " + unitCode + "\n", rule.getError().getErrorMessage());

        unitCode = "number_of_packs";
        value = "xyz";
        quantity.setUnitCode(unitCode);
        quantity.setValue(value);
        rule = new Rule_18();
        rule.checkUnitCodeAndQuantity(quantity, unitCodes, object);
        assertEquals("Check " + object + ": Invalid Quantity (Decimal). Current Quantity = " + value + "\n", rule.getError().getErrorMessage());
    }
}