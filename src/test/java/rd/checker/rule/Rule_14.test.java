package rd.checker.rule;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import connector.DatabaseConnecter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class Rule_14Test {
    String object;
    String currencyCode;

    DatabaseConnecter dbCon = new DatabaseConnecter("connection");
    List<String> currencyCodes = dbCon.getCurrencyCodes();

    @BeforeEach
    private void setBeforEach() {
        object = "Test";
    }

    @AfterEach
    private void setAfterEach() {
        object = "";
    }
    
    @Test
    void testCheckCurrencyCode() {
        // Arrange
        Rule_14 rule;
        // Act & Assert
        currencyCode = "THB";
        rule = new Rule_14();
        rule.checkCurrencyCode(currencyCode, currencyCodes, object);
        assertEquals("", rule.getError().getErrorMessage());

        currencyCode = "XYZ";
        rule = new Rule_14();
        rule.checkCurrencyCode(currencyCode, currencyCodes, object);
        assertEquals("Check " + object + ": CurrencyCode is not in the list. Current CurrencyCode = " + currencyCode + "\n", rule.getError().getErrorMessage());
    }
}