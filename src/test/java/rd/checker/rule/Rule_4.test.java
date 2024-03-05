package rd.checker.rule;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import connector.DatabaseConnecter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

class PurposeCodeToDescriptionTest {
    String object;
    String purposeCode;
    String purposeDescription;

    @BeforeEach
    private void setBeforEach() {
        object = "Test";
    }

    @AfterEach
    private void setAfterEach() {
        object = "";
    }

    @Test
    void testCheckPurpose() {
        // Arrange
        Rule_4 rule;
        DatabaseConnecter dbCon = new DatabaseConnecter("connection");
        Map<String, String> purposeCodeToDescriptionForDebitNoteOnGoods = dbCon.getPurposeCodeToDescriptionForDebitNoteOnGoods();
        Map<String, String> purposeCodeToDescriptionForDebitNoteOnService = dbCon.getPurposeCodeToDescriptionForDebitNoteOnService();
        Map<String, String> purposeCodeToDescriptionForCreditNoteOnGoods = dbCon.getPurposeCodeToDescriptionForCreditNoteOnGoods();
        Map<String, String> purposeCodeToDescriptionForCreditNoteOnService = dbCon.getPurposeCodeToDescriptionForCreditNoteOnService();
        Map<String, String> purposeCodeToDescriptionForCancellationCauseOnNewTaxInvoice = dbCon.getPurposeCodeToDescriptionForCancellationCauseOnNewTaxInvoice();
        Map<String, String> purposeCodeToDescriptionForCancellationCauseOnNewReceipt = dbCon.getPurposeCodeToDescriptionForCancellationCauseOnNewReceipt();
        
        Map<String, String> purposeCodeToDescription = new HashMap<>();
        purposeCodeToDescription.putAll(purposeCodeToDescriptionForDebitNoteOnGoods);
        purposeCodeToDescription.putAll(purposeCodeToDescriptionForDebitNoteOnService);
        purposeCodeToDescription.putAll(purposeCodeToDescriptionForCreditNoteOnGoods);
        purposeCodeToDescription.putAll(purposeCodeToDescriptionForCreditNoteOnService);
        purposeCodeToDescription.putAll(purposeCodeToDescriptionForCancellationCauseOnNewTaxInvoice);
        purposeCodeToDescription.putAll(purposeCodeToDescriptionForCancellationCauseOnNewReceipt);
        // Act & Assert
        purposeCode = "DBNG01";
        purposeDescription = "มีการเพิ่มราคาค่าสินค้า (สินค้าเกินกว่าจานวนที่ตกลงกัน)";
        rule = new Rule_4();
        rule.checkPurpose(purposeCode, purposeDescription, purposeCodeToDescription, object);
        assertEquals("", rule.getError().getErrorMessage());

        purposeCode = "DBNG01";
        purposeDescription = "ABCDEFG";
        rule = new Rule_4();
        rule.checkPurpose(purposeCode, purposeDescription, purposeCodeToDescription, object);
        assertEquals("Check " + object + "|Purpose: Purpose is not in the list. Current Purpose = " + purposeDescription + "\n", rule.getError().getErrorMessage());

        purposeCode = "DBNG50";
        purposeDescription = "มีการเพิ่มราคาค่าสินค้า (สินค้าเกินกว่าจานวนที่ตกลงกัน)";
        rule = new Rule_4();
        rule.checkPurpose(purposeCode, purposeDescription, purposeCodeToDescription, object);
        assertEquals("Check " + object + "|PurposeCode: PurposeCode is not in the list. Current PurposeCode = " + purposeCode + "\n", rule.getError().getErrorMessage());
    }
}