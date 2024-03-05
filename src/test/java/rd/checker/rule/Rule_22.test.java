package rd.checker.rule;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Rule_22Test {
    String object;
    String globalID;

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
        Rule_22 rule;

        // Act & Assert
        globalID = "0123456789ABC";
        rule = new Rule_22();
        rule.checkGlobalID(globalID, object);
        assertEquals("", rule.getError().getErrorMessage());

        globalID = "";
        rule = new Rule_22();
        rule.checkGlobalID(globalID, object);
        assertEquals("Check " + object + ": Invalid GlobalID (<=70 characters). Current GlobalID = " + globalID + "\n", rule.getError().getErrorMessage());

        globalID = "0123456789012345678901234567890123456789012345678901234567890123456789A";
        rule = new Rule_22();
        rule.checkGlobalID(globalID, object);
        assertEquals("Check " + object + ": Invalid GlobalID (<=70 characters). Current GlobalID = " + globalID + "\n", rule.getError().getErrorMessage());
    }
}