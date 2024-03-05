package rd.checker.rule;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import connector.DatabaseConnecter;
import model.pojo.trade_transaction.application_header.document.AdditionalReferencedDocument;
import model.pojo.trade_transaction.application_header.document.BuyerOrderReferencedDocument;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class Rule_13Test {
    String object;
    String issueDateTime;
    String referenceTypeCode;
    String transactionType;

    DatabaseConnecter dbCon = new DatabaseConnecter("connection");
    List<String> referencedTypeCodes = dbCon.getReferencedTypeCodes();
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
    void testCheckBuyerOrderReferencedDocument() {
        // Arrange
        Rule_13 rule;
        BuyerOrderReferencedDocument document = new BuyerOrderReferencedDocument();

        // Act & Assert
        issueDateTime = "2023-03-08T10:15:30";
        referenceTypeCode = "IV";
        document.setIssueDateTime(issueDateTime);
        document.setReferenceTypeCode(referenceTypeCode);
        rule = new Rule_13();
        rule.checkBuyerOrderReferencedDocument(document, referencedTypeCodes, object);
        assertEquals("", rule.getError().getErrorMessage());

        issueDateTime = "2023-03-08T10:15:30";
        referenceTypeCode = "XYZ";
        document.setIssueDateTime(issueDateTime);
        document.setReferenceTypeCode(referenceTypeCode);
        rule = new Rule_13();
        rule.checkBuyerOrderReferencedDocument(document, referencedTypeCodes, object);
        assertEquals("Check " + object + "|ReferencedTypeCodes: ReferencedTypeCodes is not in the list. Current ReferencedTypeCodes = " + referenceTypeCode + "\n", rule.getError().getErrorMessage());
    }

    @Test
    void testCheckAdditionalReferencedDocument() {
        // Arrange
        Rule_13 rule;
        List<AdditionalReferencedDocument> documents = new ArrayList<AdditionalReferencedDocument>();
        AdditionalReferencedDocument document = new AdditionalReferencedDocument();

        // Act & Assert
        transactionType = "CLN";
        issueDateTime = "2023-03-08T10:15:30";
        referenceTypeCode = "T07";
        document.setIssueDateTime(issueDateTime);
        document.setReferenceTypeCode(referenceTypeCode);
        documents.add(document);
        rule = new Rule_13();
        rule.checkAdditionalReferencedDocument(documents.toArray(new AdditionalReferencedDocument[documents.size()]), transactionType, invoiceTypeCodeToName, taxInvoiceTypeCodeToName, object);
        assertEquals("", rule.getError().getErrorMessage());

        transactionType = "CLN";
        issueDateTime = "2023-03-08T10:15:30";
        referenceTypeCode = "T07";
        document.setIssueDateTime(issueDateTime);
        document.setReferenceTypeCode(referenceTypeCode);
        documents.add(document);
        rule = new Rule_13();
        rule.checkAdditionalReferencedDocument(documents.toArray(new AdditionalReferencedDocument[documents.size()]), transactionType, invoiceTypeCodeToName, taxInvoiceTypeCodeToName, object);
        assertEquals("Check " + object + "|Document: Transaction = CancellationNote then Total Document Number can be 1 only. Current Total Document Number = " + documents.size() + "\n", rule.getError().getErrorMessage());

        
        transactionType = "TIV";
        issueDateTime = "2023-03-08T10:15:30";
        referenceTypeCode = "XYZ";
        documents = new ArrayList<AdditionalReferencedDocument>();
        document = new AdditionalReferencedDocument();
        document.setIssueDateTime(issueDateTime);
        document.setReferenceTypeCode(referenceTypeCode);
        documents.add(document);
        rule = new Rule_13();
        rule.checkAdditionalReferencedDocument(documents.toArray(new AdditionalReferencedDocument[documents.size()]), transactionType, invoiceTypeCodeToName, taxInvoiceTypeCodeToName, object);
        assertEquals("Check " + object + "|ReferenceTypeCode: ReferenceTypeCode is not in the list. Current ReferenceTypeCode = " + referenceTypeCode + "\n", rule.getError().getErrorMessage());
    }
}