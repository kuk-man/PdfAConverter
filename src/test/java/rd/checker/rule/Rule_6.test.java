package rd.checker.rule;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.pojo.common.GlobalID;
import model.pojo.common.ID;

import static org.junit.jupiter.api.Assertions.*;

class Rule_6Test {
    String object;
    String value;
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
    void testCheckID() {
        // Arrange
        Rule_6 rule;
        ID id = new ID();
        
        // Act & Assert
        // ""
        id.setSchemeID("");
        id.setValue("0123456789ABC");
        rule = new Rule_6();
        rule.checkID(id, object);
        assertEquals("", rule.getError().getErrorMessage());

        value = "0123456789ABCD";
        id.setSchemeID("");
        id.setValue(value);
        rule = new Rule_6();
        rule.checkID(id, object);
        assertEquals("Check " + object + ": Invalid ID (13 characters of A-Z/0-9). Current ID = " + value + "\n", rule.getError().getErrorMessage());

        value = "0123456789";
        id.setSchemeID("");
        id.setValue(value);
        rule = new Rule_6();
        rule.checkID(id, object);
        assertEquals("Check " + object + ": Invalid ID (13 characters of A-Z/0-9). Current ID = " + value + "\n", rule.getError().getErrorMessage());

        value = "0123456789AB?";
        id.setSchemeID("");
        id.setValue(value);
        rule = new Rule_6();
        rule.checkID(id, object);
        assertEquals("Check " + object + ": Invalid ID (13 characters of A-Z/0-9). Current ID = " + value + "\n", rule.getError().getErrorMessage());

        // TXID
        value = "012345678901234567";
        id.setSchemeID("TXID");
        id.setValue(value);
        rule = new Rule_6();
        rule.checkID(id, object);
        assertEquals("", rule.getError().getErrorMessage());

        value = "0123456789012345678";
        id.setSchemeID("TXID");
        id.setValue(value);
        rule = new Rule_6();
        rule.checkID(id, object);
        assertEquals("Check " + object + ": Invalid ID (18 numerics of 0-9). Current ID = " + value + "\n", rule.getError().getErrorMessage());

        value = "01234567890123456";
        id.setSchemeID("TXID");
        id.setValue(value);
        rule = new Rule_6();
        rule.checkID(id, object);
        assertEquals("Check " + object + ": Invalid ID (18 numerics of 0-9). Current ID = " + value + "\n", rule.getError().getErrorMessage());

        value = "01234567890123456A";
        id.setSchemeID("TXID");
        id.setValue(value);
        rule = new Rule_6();
        rule.checkID(id, object);
        assertEquals("Check " + object + ": Invalid ID (18 numerics of 0-9). Current ID = " + value + "\n", rule.getError().getErrorMessage());

        // NIDN
        value = "0123456789012";
        id.setSchemeID("NIDN");
        id.setValue(value);
        rule = new Rule_6();
        rule.checkID(id, object);
        assertEquals("", rule.getError().getErrorMessage());

        value = "012345678901";
        id.setSchemeID("NIDN");
        id.setValue(value);
        rule = new Rule_6();
        rule.checkID(id, object);
        assertEquals("Check " + object + ": Invalid ID (13 numberics of 0-9). Current ID = " + value + "\n", rule.getError().getErrorMessage());

        value = "01234567890123";
        id.setSchemeID("NIDN");
        id.setValue(value);
        rule = new Rule_6();
        rule.checkID(id, object);
        assertEquals("Check " + object + ": Invalid ID (13 numberics of 0-9). Current ID = " + value + "\n", rule.getError().getErrorMessage());

        value = "0123456789012A";
        id.setSchemeID("NIDN");
        id.setValue(value);
        rule = new Rule_6();
        rule.checkID(id, object);
        assertEquals("Check " + object + ": Invalid ID (13 numberics of 0-9). Current ID = " + value + "\n", rule.getError().getErrorMessage());

        // CCPT
        value = "";
        id.setSchemeID("CCPT");
        id.setValue(value);
        rule = new Rule_6();
        rule.checkID(id, object);
        assertEquals("", rule.getError().getErrorMessage());

        // OTHR
        value = "N/A";
        id.setSchemeID("OTHR");
        id.setValue(value);
        rule = new Rule_6();
        rule.checkID(id, object);
        assertEquals("", rule.getError().getErrorMessage());

        value = "123ABC";
        id.setSchemeID("OTHR");
        id.setValue(value);
        rule = new Rule_6();
        rule.checkID(id, object);
        assertEquals("Check " + object + ": Invalid ID (\"N/A\"). Current ID = " + value + "\n", rule.getError().getErrorMessage());
        
        // XYZ
        schemeId = "XYZ";
        id.setSchemeID(schemeId);
        id.setValue("");
        rule = new Rule_6();
        rule.checkID(id, object);
        assertEquals("Check " + object + ": Invalid schemeID (Blank/TXID/NIDN/CCPT/OTHR). Current schemeID = " + schemeId + "\n", rule.getError().getErrorMessage());
    }

    @Test
    void testCheckGlobalID() {
        // Arrange
        Rule_6 rule;
        GlobalID globalId = new GlobalID();

        // Act & Assert
        value = "0123456789ABC";
        schemeId = "GLN";
        schemeAgencyId = "GS1";
        globalId.setValue(value);
        globalId.setSchemeID(schemeId);
        globalId.setSchemeAgencyID(schemeAgencyId);
        rule = new Rule_6();
        rule.checkGlobalID(globalId, object);
        assertEquals("", rule.getError().getErrorMessage());

        value = "";
        schemeId = "GLN";
        schemeAgencyId = "GS1";
        globalId.setValue(value);
        globalId.setSchemeID(schemeId);
        globalId.setSchemeAgencyID(schemeAgencyId);
        rule = new Rule_6();
        rule.checkGlobalID(globalId, object);
        assertEquals("Check " + object + ": Invalid GlobalID (<=70 characters of A-Z or 0-9). Current GlobalID = " + value + "\n", rule.getError().getErrorMessage());

        value = "0123456789012345678901234567890123456789012345678901234567890123456789A";
        schemeId = "GLN";
        schemeAgencyId = "GS1";
        globalId.setValue(value);
        globalId.setSchemeID(schemeId);
        globalId.setSchemeAgencyID(schemeAgencyId);
        rule = new Rule_6();
        rule.checkGlobalID(globalId, object);
        assertEquals("Check " + object + ": Invalid GlobalID (<=70 characters of A-Z or 0-9). Current GlobalID = " + value + "\n", rule.getError().getErrorMessage());

        value = "0123456789ABC";
        schemeId = "G";
        schemeAgencyId = "GS1";
        globalId.setValue(value);
        globalId.setSchemeID(schemeId);
        globalId.setSchemeAgencyID(schemeAgencyId);
        rule = new Rule_6();
        rule.checkGlobalID(globalId, object);
        assertEquals("Check " + object + ": Invalid GlobalID (SchememId=GLN, SchemeAgencyId=GS1). Current GlobalID = " + value + 
            ", SchemeId = " + schemeId + ", SchemeAgencyId = " + schemeAgencyId + "\n", rule.getError().getErrorMessage());

        value = "0123456789ABC";
        schemeId = "GLN";
        schemeAgencyId = "GS99";
        globalId.setValue(value);
        globalId.setSchemeID(schemeId);
        globalId.setSchemeAgencyID(schemeAgencyId);
        rule = new Rule_6();
        rule.checkGlobalID(globalId, object);
        assertEquals("Check " + object + ": Invalid GlobalID (SchememId=GLN, SchemeAgencyId=GS1). Current GlobalID = " + value + 
            ", SchemeId = " + schemeId + ", SchemeAgencyId = " + schemeAgencyId + "\n", rule.getError().getErrorMessage());
    }
}