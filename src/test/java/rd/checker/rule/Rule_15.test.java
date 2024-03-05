package rd.checker.rule;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import connector.DatabaseConnecter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class Rule_15Test {
    String object;
    String taxCode;

    DatabaseConnecter dbCon = new DatabaseConnecter("connection");
    List<String> taxCodes = dbCon.getTaxCodes();

    @BeforeEach
    private void setBeforEach() {
        object = "Test";
    }

    @AfterEach
    private void setAfterEach() {
        object = "";
    }
    
    @Test
    void testCheck() {
        // Arrange
        Rule_15 rule;

        // Act & Assert
        taxCode = "VAT";
        rule = new Rule_15();
        rule.checkTaxCode(taxCode, taxCodes, object);
        assertEquals("", rule.getError().getErrorMessage());

        taxCode = "XYZ";
        rule = new Rule_15();
        rule.checkTaxCode(taxCode, taxCodes, object);
        assertEquals("Check " + object + ": TypeCode is not in the list. Current TypeCode = " + taxCode + "\n", rule.getError().getErrorMessage());
    }
}