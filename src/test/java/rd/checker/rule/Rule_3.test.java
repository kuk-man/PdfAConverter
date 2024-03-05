package rd.checker.rule;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import connector.DatabaseConnecter;
import model.pojo.exchanged_document.ExchangedDocument;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Map;

class Rule_3Test {
    String object;
    String typeCode;
    String transactionType;

    DatabaseConnecter dbCon = new DatabaseConnecter("connection");
    Map<String, String> invoiceTypeCodeToName = dbCon.getInvoiceTypeCodeToName();
    Map<String, String> taxInvoiceTypeCodeToName = dbCon.getTaxInvoiceTypeCodeToName();

    @BeforeEach
    private void setBeforEach() {
        object = "Test";
    }

    @AfterEach
    private void setAfterEach() {
        object = "";
    }

    @Test
    void testCheckExchangedDocumentTypeCodeAndTypeName() {
        // Arrange
        Rule_3 rule;
        ExchangedDocument ed = new ExchangedDocument();
        
        // Act & Assert
        transactionType = "INV";
        ed.setName(new String[] {"Proforma invoice"});
        ed.setTypeCode("325");
        rule = new Rule_3();
        rule.checkExchangedDocumentTypeCodeAndTypeName(transactionType, ed, invoiceTypeCodeToName, taxInvoiceTypeCodeToName, object);
        assertEquals("", rule.getError().getErrorMessage());

        transactionType = "TIV";
        ed.setName(new String[] {"ใบกากับภาษี (Tax Invoice)"});
        ed.setTypeCode("388");
        rule = new Rule_3();
        rule.checkExchangedDocumentTypeCodeAndTypeName(transactionType, ed, invoiceTypeCodeToName, taxInvoiceTypeCodeToName, object);
        assertEquals("", rule.getError().getErrorMessage());

        transactionType = "ABC";
        ed.setName(new String[] {"ใบกากับภาษี (Tax Invoice)"});
        ed.setTypeCode("388");
        rule = new Rule_3();
        rule.checkExchangedDocumentTypeCodeAndTypeName(transactionType, ed, invoiceTypeCodeToName, taxInvoiceTypeCodeToName, object);
        assertEquals("Check " + object + ": Invalid TransactionType (INV/RCT/TIV/DCN/ABB/CLN). Current TransactionType = " + transactionType + "\n", rule.getError().getErrorMessage());

        transactionType = "TIV";
        typeCode = "388";
        String[] typeNames = {"abcdefg"};
        String name = taxInvoiceTypeCodeToName.get(typeCode);
        ed.setName(typeNames);
        ed.setTypeCode("388");
        rule = new Rule_3();
        rule.checkExchangedDocumentTypeCodeAndTypeName(transactionType, ed, invoiceTypeCodeToName, taxInvoiceTypeCodeToName, object);
        assertEquals("Check " + object + "|Name: Name is not in the list. Current TypeCode = " + typeCode + ", Name = " + Arrays.toString(typeNames) + ". System Name = " + name + "\n", rule.getError().getErrorMessage());

        transactionType = "TIV";
        typeCode = "999";
        ed.setName(new String[] {"ใบกากับภาษี (Tax Invoice)"});
        ed.setTypeCode("999");
        rule = new Rule_3();
        rule.checkExchangedDocumentTypeCodeAndTypeName(transactionType, ed, invoiceTypeCodeToName, taxInvoiceTypeCodeToName, object);
        assertEquals("Check " + object + "|TypeCode: TypeCode is not in the list. Current TypeCode = " + typeCode + "\n", rule.getError().getErrorMessage());
    }
}