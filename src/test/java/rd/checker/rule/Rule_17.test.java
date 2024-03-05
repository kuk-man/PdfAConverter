package rd.checker.rule;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import connector.DatabaseConnecter;
import model.pojo.common.Description;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class Rule_17Test {
    String object;

    DatabaseConnecter dbCon = new DatabaseConnecter("connection");
    List<String> tradePaymentCodes = dbCon.getTradePaymentCodes();

    @BeforeEach
    private void setBeforEach() {
        object = "Test";
    }

    @AfterEach
    private void setAfterEach() {
        object = "";
    }
    
    @Test
    void testCheckSpecifiedTradePaymentTerms() {
        // Arrange
        Rule_17 rule;
        List<Description> descriptions = new ArrayList<Description>();
        Description description = new Description();

        // Act & Assert
        description.setValue("CREDOC");
        descriptions.add(description);
        rule = new Rule_17();
        rule.checkSpecifiedTradePaymentTerms(descriptions.toArray(new Description[descriptions.size()]), tradePaymentCodes, object);
        assertEquals("", rule.getError().getErrorMessage());

        String value = "XYZ";
        descriptions = new ArrayList<Description>();
        description = new Description();
        description.setValue(value);
        descriptions.add(description);
        rule = new Rule_17();
        rule.checkSpecifiedTradePaymentTerms(descriptions.toArray(new Description[descriptions.size()]), tradePaymentCodes, object);
        assertEquals("Check " + object + ": Description is not in the list. Current Description = " + value + "\n", rule.getError().getErrorMessage());
    }
}