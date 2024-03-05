package rd.checker.rule;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import connector.DatabaseConnecter;
import model.pojo.common.ClassCode;
import model.pojo.common.Name;
import model.pojo.trade_transaction.trade_line_item.DesignatedProductClassification;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class Rule_21Test {
    String object;
    String value;
    String listName;
    String listVersionID;
    String languageID;

    DatabaseConnecter dbCon = new DatabaseConnecter("connection");
    List<String> unspscCodes = dbCon.getUnspscCodes();
    List<String> languageCodes = dbCon.getLanguageCodes();

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
        Rule_21 rule;
        DesignatedProductClassification dpc = new DesignatedProductClassification();
        ClassCode classCode = new ClassCode();
        Name className = new Name();

        // Act & Assert
        value = "10100000";
        listName = "UNSPSC";
        listVersionID = "UNv26.0801";
        languageID = "tha";
        classCode.setListName(listName);
        classCode.setListVersionID(listVersionID);
        classCode.setValue(value);
        className.setLanguageID(languageID);
        dpc.setClassCode(classCode);
        dpc.setClassName(new Name[] {className});
        rule = new Rule_21();
        rule.checkDesignatedProductClassification(dpc, unspscCodes, languageCodes, object);
        assertEquals("", rule.getError().getErrorMessage());

        value = "101000";
        listName = "UNSPSC";
        listVersionID = "UNv26.0801";
        languageID = "tha";
        classCode.setListName(listName);
        classCode.setListVersionID(listVersionID);
        classCode.setValue(value);
        className.setLanguageID(languageID);
        dpc = new DesignatedProductClassification();
        dpc.setClassCode(classCode);
        dpc.setClassName(new Name[] {className});
        rule = new Rule_21();
        rule.checkDesignatedProductClassification(dpc, unspscCodes, languageCodes, object);
        assertEquals("Check " + object + "|ClassCode: Invalid ClassCode (8 numerics). Current ClassCode = " + value + "\n", rule.getError().getErrorMessage());

        value = "99100000";
        listName = "UNSPSC";
        listVersionID = "UNv26.0801";
        languageID = "tha";
        classCode.setListName(listName);
        classCode.setListVersionID(listVersionID);
        classCode.setValue(value);
        className.setLanguageID(languageID);
        dpc = new DesignatedProductClassification();
        dpc.setClassCode(classCode);
        dpc.setClassName(new Name[] {className});
        rule = new Rule_21();
        rule.checkDesignatedProductClassification(dpc, unspscCodes, languageCodes, object);
        assertEquals("Check " + object + "|ClassCode: ClassCode is not in the list. Current ClassCode = " + value + "\n", rule.getError().getErrorMessage());

        value = "10100000";
        listName = "UNSPSC";
        listVersionID = "";
        languageID = "tha";
        classCode.setListName(listName);
        classCode.setListVersionID(listVersionID);
        classCode.setValue(value);
        className.setLanguageID(languageID);
        dpc = new DesignatedProductClassification();
        dpc.setClassCode(classCode);
        dpc.setClassName(new Name[] {className});
        rule = new Rule_21();
        rule.checkDesignatedProductClassification(dpc, unspscCodes, languageCodes, object);
        assertEquals("Check " + object + "|ListVersionID: ListVersionID cannot be blank. Current ListVersionID = " + listVersionID + "\n", rule.getError().getErrorMessage());

        value = "10100000";
        listName = "XYZ";
        listVersionID = "UNv26.0801";
        languageID = "tha";
        classCode.setListName(listName);
        classCode.setListVersionID(listVersionID);
        classCode.setValue(value);
        className.setLanguageID(languageID);
        dpc = new DesignatedProductClassification();
        dpc.setClassCode(classCode);
        dpc.setClassName(new Name[] {className});
        rule = new Rule_21();
        rule.checkDesignatedProductClassification(dpc, unspscCodes, languageCodes, object);
        assertEquals("Check " + object + "|ListName: Invalid ListName (UNSPSC). Current ListName = " + listName + "\n", rule.getError().getErrorMessage());

        value = "10100000";
        listName = "UNSPSC";
        listVersionID = "UNv26.0801";
        languageID = "xyz";
        classCode.setListName(listName);
        classCode.setListVersionID(listVersionID);
        classCode.setValue(value);
        className.setLanguageID(languageID);
        dpc = new DesignatedProductClassification();
        dpc.setClassCode(classCode);
        dpc.setClassName(new Name[] {className});
        rule = new Rule_21();
        rule.checkDesignatedProductClassification(dpc, unspscCodes, languageCodes, object);
        assertEquals("Check " + object + "|LanguageID: LanguageID is not in the list. Current LanguageID = " + languageID + "\n", rule.getError().getErrorMessage());
    }
}