package rd.checker.rule;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.pojo.common.GlobalID;
import model.pojo.trade_transaction.trade_line_item.SpecifiedTradeProduct;

import static org.junit.jupiter.api.Assertions.*;

class Rule_20Test {
    String object;
    String id;
    String schemeId;
    String schemeAgencyId;

    @BeforeEach
    private void setBeforEach() {
        object = "Test";
    }

    @AfterEach
    private void setAfterEach() {
        object = "";
    }
    
    @Test
    void testCheckSpecifiedTradeProduct() {
        // Arrange
        Rule_20 rule;
        SpecifiedTradeProduct stp = new SpecifiedTradeProduct();
        GlobalID globalID = new GlobalID();

        // Act && Assert
        // ID
        id = "0123456789ABC";
        stp.setId(id);
        rule = new Rule_20();
        rule.checkSpecifiedTradeProduct(stp, object);
        assertEquals("", rule.getError().getErrorMessage());

        id = "";
        stp.setId(id);
        rule = new Rule_20();
        rule.checkSpecifiedTradeProduct(stp, object);
        assertEquals("Check " + object + "|ID: Invalid ID (<=35 characters of A-Z/0-9). Current ID = " + id + "\n", rule.getError().getErrorMessage());

        id = "0123456789ABC?";
        stp.setId(id);
        rule = new Rule_20();
        rule.checkSpecifiedTradeProduct(stp, object);
        assertEquals("Check " + object + "|ID: Invalid ID (<=35 characters of A-Z/0-9). Current ID = " + id + "\n", rule.getError().getErrorMessage());

        // GlobalID
        id = "01234567890123";
        schemeId = "GLN";
        schemeAgencyId = "GS1";
        globalID.setValue(id);
        globalID.setSchemeID(schemeId);
        globalID.setSchemeAgencyID(schemeAgencyId);
        stp = new SpecifiedTradeProduct();
        stp.setGlobalID(globalID);
        rule = new Rule_20();
        rule.checkSpecifiedTradeProduct(stp, object);
        assertEquals("", rule.getError().getErrorMessage());

        id = "0123456789012";
        schemeId = "GLN";
        schemeAgencyId = "GS1";
        globalID.setValue(id);
        globalID.setSchemeID(schemeId);
        globalID.setSchemeAgencyID(schemeAgencyId);
        stp = new SpecifiedTradeProduct();
        stp.setGlobalID(globalID);
        rule = new Rule_20();
        rule.checkSpecifiedTradeProduct(stp, object);
        assertEquals("Check " + object + "|GlobalID: Invalid GlobalID (14 characters of 0-9). Current GlobalID = " + id + "\n", rule.getError().getErrorMessage());

        id = "0123456789012A";
        schemeId = "GLN";
        schemeAgencyId = "GS1";
        globalID.setValue(id);
        globalID.setSchemeID(schemeId);
        globalID.setSchemeAgencyID(schemeAgencyId);
        stp = new SpecifiedTradeProduct();
        stp.setGlobalID(globalID);
        rule = new Rule_20();
        rule.checkSpecifiedTradeProduct(stp, object);
        assertEquals("Check " + object + "|GlobalID: Invalid GlobalID (14 characters of 0-9). Current GlobalID = " + id + "\n", rule.getError().getErrorMessage());

        id = "01234567890123";
        schemeId = "XYZ";
        schemeAgencyId = "GS1";
        globalID.setValue(id);
        globalID.setSchemeID(schemeId);
        globalID.setSchemeAgencyID(schemeAgencyId);
        stp = new SpecifiedTradeProduct();
        stp.setGlobalID(globalID);
        rule = new Rule_20();
        rule.checkSpecifiedTradeProduct(stp, object);
        assertEquals("Check " + object + "|SchemeId/SchemeAgencyId: Invalid SchemeId/SchemeAgencyId (SchememId=GLN, SchemeAgencyId=GS1). Current SchemeId = " + schemeId + ", SchemeAgencyId = " + schemeAgencyId + "\n", rule.getError().getErrorMessage());

        id = "01234567890123";
        schemeId = "GLN";
        schemeAgencyId = "XYZ";
        globalID.setValue(id);
        globalID.setSchemeID(schemeId);
        globalID.setSchemeAgencyID(schemeAgencyId);
        stp = new SpecifiedTradeProduct();
        stp.setGlobalID(globalID);
        rule = new Rule_20();
        rule.checkSpecifiedTradeProduct(stp, object);
        assertEquals("Check " + object + "|SchemeId/SchemeAgencyId: Invalid SchemeId/SchemeAgencyId (SchememId=GLN, SchemeAgencyId=GS1). Current SchemeId = " + schemeId + ", SchemeAgencyId = " + schemeAgencyId + "\n", rule.getError().getErrorMessage());
    }
}