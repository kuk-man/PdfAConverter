package rd.checker.rule;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Rule_5Test {
    String object;

    @BeforeEach
    private void setBeforEach() {
        object = "Test";
    }

    @AfterEach
    private void setAfterEach() {
        object = "";
    }
    @Test
    void testCheckDateTimeFormat() {
        // Arrange
        Rule_5 rule;
        String validIsoDateTime = "2023-03-08T10:15:30";
        String invalidIsoDateTime = "2023-03-08T10:15:30:55";
        // Act & Assert
        rule = new Rule_5();
        rule.checkDateTimeFormat(validIsoDateTime, object);
        assertEquals("", rule.getError().getErrorMessage());

        rule = new Rule_5();
        rule.checkDateTimeFormat(invalidIsoDateTime, object);
        assertEquals("Check " + object + ": Invalid DateTime (YYYY-mm-DDTHH:MM:SS). Current DateTime = " + invalidIsoDateTime + "\n", rule.getError().getErrorMessage());
    }
}