package rd.checker.mandatory_field;

import org.junit.jupiter.api.Test;

import model.pojo.RootXml;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

class AbbreviatedTaxInvoiceMandatoryFieldTest {

    @Test
    void TestCheckRequiredField() throws Exception {
        // Arrange
        String jsonMessage = "{\"transaction\": \"ABB\",\"crossIndustryInvoice\": {\"exchangedDocumentContext\": {\"guidelineSpecifiedDocumentContextParameter\": {\"id\": {\"value\":\"ER3-2560\"}}},\"exchangedDocument\": {\"id\": \"INV01\",\"name\": \"ใบกากับภาษี (Tax Invoice)\",\"typeCode\": 388,\"issueDateTime\": \"2017-12-19T00:00:00\",\"creationDateTime\": \"2017-12-19T00:00:00\"},\"supplyChainTradeTransaction\": {\"applicableHeaderTradeAgreement\": {\"sellerTradeParty\": {\"name\": \"บริษัท ขยันหมั่นเพียร จำกัด\",\"specifiedTaxRegistration\": {\"id\": {\"value\":\"1111111111111\"}},\"definedTradeContact\": {\"emailURIUniversalCommunication\": {\"uriid\": \"natueal@example.com\"},\"telephoneUniversalCommunication\": {\"completeNumber\": \"+66-81234567\"}},\"postalTradeAddress\": {\"postcodeCode\": \"71180\",\"lineOne\": \"สำนักงานอุทยานแห่งชาติ\",\"cityName\": \"1007\",\"citySubDivisionName\": \"100701\",\"countryID\": \"TH\",\"countrySubDivisionID\": \"37\",\"buildingNumber\": \"777/777\"}}},\"applicableHeaderTradeDelivery\": {\"shipToTradeParty\": {\"definedTradeContact\": {\"personName\": \"สมพร ใจงาม\"}},\"shipFromTradeParty\": {\"name\": \"iop\"}},\"applicableHeaderTradeSettlement\": {\"invoiceCurrencyCode\": \"THB\",\"applicableTradeTax\": {\"typeCode\": \"VAT\",\"calculatedRate\": \"7\",\"basisAmount\": {\"value\":\"9999\"},\"calculatedAmount\": {\"value\":\"699.93\"}},\"specifiedTradeSettlementHeaderMonetarySummation\": {\"lineTotalAmount\": {\"value\":\"9999\"},\"taxBasisTotalAmount\": {\"value\":\"699.93\"},\"taxTotalAmount\": {\"value\":\"699.93\"},\"grandTotalAmount\": {\"value\":\"10698.93\"}}},\"includedSupplyChainTradeLineItem\": {\"associatedDocumentLineDocument\": {\"lineID\": \"1\"},\"specifiedTradeProduct\": {\"name\": {\"value\":\"สินค้าทดสอบ\"}},\"specifiedLineTradeAgreement\": {\"grossPriceProductTradePrice\": {\"chargeAmount\": {\"value\":\"9999\"}}},\"specifiedLineTradeDelivery\": {\"billedQuantity\": {\"unitCode\": \"net_kilogram\", \"value\":\"1\"}},\"specifiedLineTradeSettlement\": {\"applicableTradeTax\": {\"typeCode\": \"123\",\"calculatedRate\": \"0\",\"basisAmount\": {\"value\":\"9999\"},\"calculatedAmount\": {\"value\":\"9999\"}},\"specifiedTradeSettlementLineMonetarySummation\": {\"netLineTotalAmount\": {\"currencyID\": \"THB\", \"value\":\"9999\"},\"netIncludingTaxesLineTotalAmount\": {\"currencyID\": \"THB\", \"value\":\"10698.93\"}}}}}}}";
        // Act
        ByteBuffer buffer = StandardCharsets.UTF_8.encode(jsonMessage); 
		String jsonMessageUtf8 = StandardCharsets.UTF_8.decode(buffer).toString();

        rd.Transaction txn = new rd.Transaction();
        RootXml rootXml = txn.convertJsonToPojo(jsonMessageUtf8);

        AbbreviatedTaxInvoiceMandatoryField abb = new AbbreviatedTaxInvoiceMandatoryField();
        abb.checkRequiredField(rootXml);
        String errorMessage = abb.getError().getErrorMessage();
        // Assert
        assertEquals("", errorMessage);
    }

    @Test 
    void TestCheckNotUsedField() throws Exception {
        // Arrange
        String jsonMessage = "{\"transaction\": \"ABB\",\"crossIndustryInvoice\": {\"exchangedDocumentContext\": {\"guidelineSpecifiedDocumentContextParameter\": {\"id\": {\"value\":\"ER3-2560\"}}},\"exchangedDocument\": {\"id\": \"INV01\",\"name\": \"ใบกากับภาษี (Tax Invoice)\",\"typeCode\": 388,\"issueDateTime\": \"2017-12-19T00:00:00\",\"creationDateTime\": \"2017-12-19T00:00:00\"},\"supplyChainTradeTransaction\": {\"applicableHeaderTradeAgreement\": {\"sellerTradeParty\": {\"name\": \"บริษัท ขยันหมั่นเพียร จำกัด\",\"specifiedTaxRegistration\": {\"id\": {\"value\":\"1111111111111\"}},\"definedTradeContact\": {\"emailURIUniversalCommunication\": {\"uriid\": \"natueal@example.com\"},\"telephoneUniversalCommunication\": {\"completeNumber\": \"+66-81234567\"}},\"postalTradeAddress\": {\"postcodeCode\": \"71180\",\"lineOne\": \"สำนักงานอุทยานแห่งชาติ\",\"cityName\": \"1007\",\"citySubDivisionName\": \"100701\",\"countryID\": \"TH\",\"countrySubDivisionID\": \"37\",\"buildingNumber\": \"777/777\"}}},\"applicableHeaderTradeDelivery\": {\"shipToTradeParty\": {\"definedTradeContact\": {\"personName\": \"สมพร ใจงาม\"}},\"shipFromTradeParty\": {\"name\": \"iop\"}},\"applicableHeaderTradeSettlement\": {\"invoiceCurrencyCode\": \"THB\",\"applicableTradeTax\": {\"typeCode\": \"VAT\",\"calculatedRate\": \"7\",\"basisAmount\": {\"value\":\"9999\"},\"calculatedAmount\": {\"value\":\"699.93\"}},\"specifiedTradeSettlementHeaderMonetarySummation\": {\"lineTotalAmount\": {\"value\":\"9999\"},\"taxBasisTotalAmount\": {\"value\":\"699.93\"},\"taxTotalAmount\": {\"value\":\"699.93\"},\"grandTotalAmount\": {\"value\":\"10698.93\"}}},\"includedSupplyChainTradeLineItem\": {\"associatedDocumentLineDocument\": {\"lineID\": \"1\"},\"specifiedTradeProduct\": {\"name\": {\"value\":\"สินค้าทดสอบ\"}},\"specifiedLineTradeAgreement\": {\"grossPriceProductTradePrice\": {\"chargeAmount\": {\"value\":\"9999\"}}},\"specifiedLineTradeDelivery\": {\"billedQuantity\": {\"unitCode\": \"net_kilogram\", \"value\":\"1\"}},\"specifiedLineTradeSettlement\": {\"applicableTradeTax\": {\"typeCode\": \"123\",\"calculatedRate\": \"0\",\"basisAmount\": {\"value\":\"9999\"},\"calculatedAmount\": {\"value\":\"9999\"}},\"specifiedTradeSettlementLineMonetarySummation\": {\"netLineTotalAmount\": {\"currencyID\": \"THB\", \"value\":\"9999\"},\"netIncludingTaxesLineTotalAmount\": {\"currencyID\": \"THB\", \"value\":\"10698.93\"}}}}}}}";
        String expted = "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|DefinedTradeContact|PersonName\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|DefinedTradeContact|DepartmentName\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|DefinedTradeContact|EmailURIUniversalCommunication\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|DefinedTradeContact|EmailURIUniversalCommunication|URIID\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|DefinedTradeContact|TelephoneUniversalCommunication\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|DefinedTradeContact|TelephoneUniversalCommunication|CompleteNumber\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|AdditionalReferencedDocument|IssuerAssignedID\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|AdditionalReferencedDocument|IssueDateTime\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|AdditionalReferencedDocument|ReferenceTypeCode\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|DefinedTradeContact|PersonName\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|DefinedTradeContact|DepartmentName\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|DefinedTradeContact|EmailURIUniversalCommunication\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|DefinedTradeContact|EmailURIUniversalCommunication|URIID\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|DefinedTradeContact|TelephoneUniversalCommunication\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|DefinedTradeContact|TelephoneUniversalCommunication|CompleteNumber\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|DefinedTradeContact|PersonName\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|DefinedTradeContact|DepartmentName\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|DefinedTradeContact|EmailURIUniversalCommunication\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|DefinedTradeContact|EmailURIUniversalCommunication|URIID\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|DefinedTradeContact|TelephoneUniversalCommunication\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|DefinedTradeContact|TelephoneUniversalCommunication|CompleteNumber\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|DefinedTradeContact|PersonName\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|DefinedTradeContact|DepartmentName\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|DefinedTradeContact|EmailURIUniversalCommunication\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|DefinedTradeContact|EmailURIUniversalCommunication|URIID\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|DefinedTradeContact|TelephoneUniversalCommunication\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|DefinedTradeContact|TelephoneUniversalCommunication|CompleteNumber\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|DefinedTradeContact|PersonName\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|DefinedTradeContact|DepartmentName\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|DefinedTradeContact|EmailURIUniversalCommunication\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|DefinedTradeContact|EmailURIUniversalCommunication|URIID\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|DefinedTradeContact|TelephoneUniversalCommunication\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|DefinedTradeContact|TelephoneUniversalCommunication|CompleteNumber" + //
                        "\n";
        // Act
        ByteBuffer buffer = StandardCharsets.UTF_8.encode(jsonMessage); 
		String jsonMessageUtf8 = StandardCharsets.UTF_8.decode(buffer).toString();

        rd.Transaction txn = new rd.Transaction();
        RootXml rootXml = txn.convertJsonToPojo(jsonMessageUtf8);

        AbbreviatedTaxInvoiceMandatoryField abb = new AbbreviatedTaxInvoiceMandatoryField();
        abb.checkNotUsedField(rootXml);
        String errorMessage = abb.getError().getErrorMessage();
        // Assert
        assertEquals(expted, errorMessage);
    }
}