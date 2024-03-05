package rd.checker.rule;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Rule_23Test {
    String object;
    String transactionType;

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
        Rule_23 rule;

        // Act & Assert
        transactionType = "INV";
        rule = new Rule_23();
        rule.checkInvoicerTradeParty(transactionType, object);
        assertEquals("", rule.getError().getErrorMessage());

        transactionType = "TIV";
        rule = new Rule_23();
        rule.checkInvoicerTradeParty(transactionType, object);
        assertEquals("Check " + object + ": Missing InvoicerTradeParty when TransactionType = TIV/DCN/RCT/CLN. Current TransactionType = " + transactionType + "\n", rule.getError().getErrorMessage());

        transactionType = "DCN";
        rule = new Rule_23();
        rule.checkInvoicerTradeParty(transactionType, object);
        assertEquals("Check " + object + ": Missing InvoicerTradeParty when TransactionType = TIV/DCN/RCT/CLN. Current TransactionType = " + transactionType + "\n", rule.getError().getErrorMessage());

        transactionType = "RCT";
        rule = new Rule_23();
        rule.checkInvoicerTradeParty(transactionType, object);
        assertEquals("Check " + object + ": Missing InvoicerTradeParty when TransactionType = TIV/DCN/RCT/CLN. Current TransactionType = " + transactionType + "\n", rule.getError().getErrorMessage());

        transactionType = "CLN";
        rule = new Rule_23();
        rule.checkInvoicerTradeParty(transactionType, object);
        assertEquals("Check " + object + ": Missing InvoicerTradeParty when TransactionType = TIV/DCN/RCT/CLN. Current TransactionType = " + transactionType + "\n", rule.getError().getErrorMessage());
    }
}