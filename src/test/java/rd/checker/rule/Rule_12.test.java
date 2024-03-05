package rd.checker.rule;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import connector.DatabaseConnecter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class Rule_12Test {
    String object;
    String incotermCode;

    DatabaseConnecter dbCon = new DatabaseConnecter("connection");
    List<String> incotermCodes = dbCon.getIncotermCodes();
    
    @BeforeEach
    private void setBeforEach() {
        object = "Test";
    }

    @AfterEach
    private void setAfterEach() {
        object = "";
    }
    @Test
    void testCheckTradeTermCode() {
        // Arrange
        Rule_12 rule;
        // Act & Assert
        incotermCode = "EXW";
        rule = new Rule_12();
        rule.checkTradeTermCode(incotermCode, incotermCodes, object);
        assertEquals("", rule.getError().getErrorMessage());

        incotermCode = "XYZ";
        rule = new Rule_12();
        rule.checkTradeTermCode(incotermCode, incotermCodes, object);
        assertEquals("Check " + object + "|DeliveryTypeCode: Invalid DeliveryTypeCode is not in the list. Current Trade Term Code = " + incotermCode + "\n", rule.getError().getErrorMessage());
    }
}