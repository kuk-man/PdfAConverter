package rd.checker.rule;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import connector.DatabaseConnecter;
import model.pojo.common.Amount;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class Rule_19Test {
    String object;
    String currencyID;
    String value;

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
    void testCheckAmountAndCurrencyCode() {
        // Arrange
        Rule_19 rule;
        Amount amount = new Amount();

        // Act & Assert
        currencyID = "THB";
        value = "100.50";
        amount.setCurrencyID(currencyID);
        amount.setValue(value);
        rule = new Rule_19();
        rule.checkAmountAndCurrencyCode(amount, currencyCodes, object);
        assertEquals("", rule.getError().getErrorMessage());

        currencyID = "THB";
        value = "xyz";
        amount.setCurrencyID(currencyID);
        amount.setValue(value);
        rule = new Rule_19();
        rule.checkAmountAndCurrencyCode(amount, currencyCodes, object);
        assertEquals("Check " + object + ": Invalid Amount (Decimal). Current Amount = " + value + "\n", rule.getError().getErrorMessage());
    }
}