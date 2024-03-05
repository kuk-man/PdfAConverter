package rd.checker.mandatory_field;

import org.junit.jupiter.api.Test;

import model.pojo.RootXml;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

class TaxInvoiceMandatoryFieldTest {

    @Test
    void TestCheckRequiredField() throws Exception {
        // Arrange
        String jsonMessage = "{\"transaction\": \"TIV\",\"crossIndustryInvoice\": {\"exchangedDocumentContext\": {\"guidelineSpecifiedDocumentContextParameter\": {\"id\": {\"value\":\"ER3-2560\"}}},\"exchangedDocument\": {\"id\": \"INV01\",\"name\": \"ใบกากับภาษี (Tax Invoice)\",\"typeCode\": 388,\"issueDateTime\": \"2017-12-19T00:00:00\",\"creationDateTime\": \"2017-12-19T00:00:00\"},\"supplyChainTradeTransaction\": {\"applicableHeaderTradeAgreement\": {\"sellerTradeParty\": {\"name\": \"บริษัท ขยันหมั่นเพียร จำกัด\",\"specifiedTaxRegistration\": {\"id\": {\"value\":\"1111111111111\"}},\"definedTradeContact\": {\"emailURIUniversalCommunication\": {\"uriid\": \"natueal@example.com\"},\"telephoneUniversalCommunication\": {\"completeNumber\": \"+66-81234567\"}},\"postalTradeAddress\": {\"postcodeCode\": \"71180\",\"lineOne\": \"สำนักงานอุทยานแห่งชาติ\",\"cityName\": \"1007\",\"citySubDivisionName\": \"100701\",\"countryID\": \"TH\",\"countrySubDivisionID\": \"37\",\"buildingNumber\": \"777/777\"}},\"buyerTradeParty\": {\"name\": \"บริษัททำดีจำกัด\",\"specifiedTaxRegistration\": {\"id\": {\"value\":\"2222222222222\"}},\"definedTradeContact\": {\"emailURIUniversalCommunication\": {\"uriid\": \"patiw@example.com\"},\"telephoneUniversalCommunication\": {\"completeNumber\": \"+66-97778889\"}},\"postalTradeAddress\": {\"postcodeCode\": \"11344\",\"lineOne\": \"หาดทุ่งวัวแล่น\",\"cityName\": \"1007\",\"citySubDivisionName\": \"100702\",\"countryID\": \"TH\",\"countrySubDivisionID\": \"37\",\"buildingNumber\": \"77/79\"}},\"additionalReferencedDocument\": {\"issuerAssignedID\": \"abc\",\"issueDateTime\": \"2017-12-19T00:00:00\",\"referenceTypeCode\": \"388\"}},\"applicableHeaderTradeDelivery\": {\"shipToTradeParty\": {\"definedTradeContact\": {\"personName\": \"สมพร ใจงาม\"}},\"shipFromTradeParty\": {\"name\": \"iop\"}},\"applicableHeaderTradeSettlement\": {\"invoiceCurrencyCode\": \"THB\",\"applicableTradeTax\": {\"typeCode\": \"VAT\",\"calculatedRate\": \"7\",\"basisAmount\": {\"value\":\"9999\"},\"calculatedAmount\": {\"value\":\"699.93\"}},\"specifiedTradeSettlementHeaderMonetarySummation\": {\"lineTotalAmount\": {\"value\":\"9999\"},\"taxBasisTotalAmount\": {\"value\":\"699.93\"},\"taxTotalAmount\": {\"value\":\"699.93\"},\"grandTotalAmount\": {\"value\":\"10698.93\"}},\"invoicerTradeParty\": {\"name\": \"poi\",\"specifiedTaxRegistration\": {\"id\": {\"value\":\"3333333333333\"}},\"postalTradeAddress\": {\"postcodeCode\": \"11344\",\"cityName\": \"1007\",\"citySubDivisionName\": \"100702\",\"countryID\": \"TH\",\"countrySubDivisionID\": \"37\"}},\"invoiceeTradeParty\": {\"name\": \"uio\",\"specifiedTaxRegistration\": {\"id\": {\"value\":\"4444444444444\"}},\"postalTradeAddress\": {\"postcodeCode\": \"11111\",\"cityName\": \"1007\",\"citySubDivisionName\": \"100702\",\"countryID\": \"TH\",\"countrySubDivisionID\": \"37\"}},\"payerTradeParty\": {\"name\": \"cfd\",\"specifiedTaxRegistration\": {\"id\": {\"value\":\"5555555555555\"}}},\"payeeTradeParty\": {\"name\": \"yru\",\"specifiedTaxRegistration\": {\"id\": {\"value\":\"6666666666666\"}},\"postalTradeAddress\": {\"postcodeCode\": \"11344\",\"cityName\": \"1007\",\"citySubDivisionName\": \"100702\",\"countryID\": \"TH\",\"countrySubDivisionID\": \"37\"}}},\"includedSupplyChainTradeLineItem\": {\"associatedDocumentLineDocument\": {\"lineID\": \"1\"},\"specifiedTradeProduct\": {\"name\": {\"value\":\"สินค้าทดสอบ\"}},\"specifiedLineTradeAgreement\": {\"grossPriceProductTradePrice\": {\"chargeAmount\": {\"value\":\"9999\"}}},\"specifiedLineTradeDelivery\": {\"billedQuantity\": {\"unitCode\": \"net_kilogram\", \"value\":\"1\"}},\"specifiedLineTradeSettlement\": {\"applicableTradeTax\": {\"typeCode\": \"123\",\"calculatedRate\": \"0\",\"basisAmount\": {\"value\":\"9999\"},\"calculatedAmount\": {\"value\":\"9999\"}},\"specifiedTradeSettlementLineMonetarySummation\": {\"netLineTotalAmount\": {\"currencyID\": \"THB\", \"value\":\"9999\"},\"netIncludingTaxesLineTotalAmount\": {\"currencyID\": \"THB\", \"value\":\"10698.93\"}}}}}}}";
        // Act
        ByteBuffer buffer = StandardCharsets.UTF_8.encode(jsonMessage); 
		String jsonMessageUtf8 = StandardCharsets.UTF_8.decode(buffer).toString();

        rd.Transaction txn = new rd.Transaction();
        RootXml rootXml = txn.convertJsonToPojo(jsonMessageUtf8);

        TaxInvoiceMandatoryField tiv = new TaxInvoiceMandatoryField();
        tiv.checkRequiredField(rootXml);
        String errorMessage = tiv.getError().getErrorMessage();
        // Assert
        assertEquals("", errorMessage);
    }

    @Test 
    void TestCheckNotUsedField() throws Exception {
        // Arrange
        String jsonMessage = "{\"transaction\": \"TIV\",\"crossIndustryInvoice\": {\"exchangedDocumentContext\": {\"guidelineSpecifiedDocumentContextParameter\": {\"id\": {\"value\":\"ER3-2560\"}}},\"exchangedDocument\": {\"id\": \"INV01\",\"name\": \"ใบกากับภาษี (Tax Invoice)\",\"typeCode\": 388,\"issueDateTime\": \"2017-12-19T00:00:00\",\"creationDateTime\": \"2017-12-19T00:00:00\"},\"supplyChainTradeTransaction\": {\"applicableHeaderTradeAgreement\": {\"sellerTradeParty\": {\"name\": \"บริษัท ขยันหมั่นเพียร จำกัด\",\"specifiedTaxRegistration\": {\"id\": {\"value\":\"1111111111111\"}},\"definedTradeContact\": {\"emailURIUniversalCommunication\": {\"uriid\": \"natueal@example.com\"},\"telephoneUniversalCommunication\": {\"completeNumber\": \"+66-81234567\"}},\"postalTradeAddress\": {\"postcodeCode\": \"71180\",\"lineOne\": \"สำนักงานอุทยานแห่งชาติ\",\"cityName\": \"1007\",\"citySubDivisionName\": \"100701\",\"countryID\": \"TH\",\"countrySubDivisionID\": \"37\",\"buildingNumber\": \"777/777\"}},\"buyerTradeParty\": {\"name\": \"บริษัททำดีจำกัด\",\"specifiedTaxRegistration\": {\"id\": {\"value\":\"2222222222222\"}},\"definedTradeContact\": {\"emailURIUniversalCommunication\": {\"uriid\": \"patiw@example.com\"},\"telephoneUniversalCommunication\": {\"completeNumber\": \"+66-97778889\"}},\"postalTradeAddress\": {\"postcodeCode\": \"11344\",\"lineOne\": \"หาดทุ่งวัวแล่น\",\"cityName\": \"1007\",\"citySubDivisionName\": \"100702\",\"countryID\": \"TH\",\"countrySubDivisionID\": \"37\",\"buildingNumber\": \"77/79\"}},\"additionalReferencedDocument\": {\"issuerAssignedID\": \"abc\",\"issueDateTime\": \"2017-12-19T00:00:00\",\"referenceTypeCode\": \"388\"}},\"applicableHeaderTradeDelivery\": {\"shipToTradeParty\": {\"definedTradeContact\": {\"personName\": \"สมพร ใจงาม\"}},\"shipFromTradeParty\": {\"name\": \"iop\"}},\"applicableHeaderTradeSettlement\": {\"invoiceCurrencyCode\": \"THB\",\"applicableTradeTax\": {\"typeCode\": \"VAT\",\"calculatedRate\": \"7\",\"basisAmount\": {\"value\":\"9999\"},\"calculatedAmount\": {\"value\":\"699.93\"}},\"specifiedTradeSettlementHeaderMonetarySummation\": {\"originalInformationAmount\": {\"value\":\"9999\"},\"lineTotalAmount\": {\"value\":\"9999\"},\"differenceInformationAmount\": {\"value\":\"9999\"},\"taxBasisTotalAmount\": {\"value\":\"699.93\"},\"taxTotalAmount\": {\"value\":\"699.93\"},\"grandTotalAmount\": {\"value\":\"10698.93\"}},\"invoicerTradeParty\": {\"name\": \"poi\",\"specifiedTaxRegistration\": {\"id\": {\"value\":\"3333333333333\"}},\"postalTradeAddress\": {\"postcodeCode\": \"11344\",\"cityName\": \"1007\",\"citySubDivisionName\": \"100702\",\"countryID\": \"TH\",\"countrySubDivisionID\": \"37\"}},\"invoiceeTradeParty\": {\"name\": \"uio\",\"specifiedTaxRegistration\": {\"id\": {\"value\":\"4444444444444\"}},\"postalTradeAddress\": {\"postcodeCode\": \"11111\",\"cityName\": \"1007\",\"citySubDivisionName\": \"100702\",\"countryID\": \"TH\",\"countrySubDivisionID\": \"37\"}},\"payerTradeParty\": {\"name\": \"cfd\",\"specifiedTaxRegistration\": {\"id\": {\"value\":\"5555555555555\"}}},\"payeeTradeParty\": {\"name\": \"yru\",\"specifiedTaxRegistration\": {\"id\": {\"value\":\"6666666666666\"}},\"postalTradeAddress\": {\"postcodeCode\": \"11344\",\"cityName\": \"1007\",\"citySubDivisionName\": \"100702\",\"countryID\": \"TH\",\"countrySubDivisionID\": \"37\"}}},\"includedSupplyChainTradeLineItem\": {\"associatedDocumentLineDocument\": {\"lineID\": \"1\"},\"specifiedTradeProduct\": {\"name\": {\"value\":\"สินค้าทดสอบ\"}},\"specifiedLineTradeAgreement\": {\"grossPriceProductTradePrice\": {\"chargeAmount\": {\"value\":\"9999\"}}},\"specifiedLineTradeDelivery\": {\"billedQuantity\": {\"unitCode\": \"net_kilogram\", \"value\":\"1\"}},\"specifiedLineTradeSettlement\": {\"applicableTradeTax\": {\"typeCode\": \"123\",\"calculatedRate\": \"0\",\"basisAmount\": {\"value\":\"9999\"},\"calculatedAmount\": {\"value\":\"9999\"}},\"specifiedTradeSettlementLineMonetarySummation\": {\"netLineTotalAmount\": {\"currencyID\": \"THB\", \"value\":\"9999\"},\"netIncludingTaxesLineTotalAmount\": {\"currencyID\": \"THB\", \"value\":\"10698.93\"}}}}}}}";
        String expected = "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|OriginalInformationAmount\n" + //
                          "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|DifferenceInformationAmount" + //
                          "\n";
        // Act
        ByteBuffer buffer = StandardCharsets.UTF_8.encode(jsonMessage); 
		String jsonMessageUtf8 = StandardCharsets.UTF_8.decode(buffer).toString();

        rd.Transaction txn = new rd.Transaction();
        RootXml rootXml = txn.convertJsonToPojo(jsonMessageUtf8);

        TaxInvoiceMandatoryField tiv = new TaxInvoiceMandatoryField();
        tiv.checkNotUsedField(rootXml);
        String errorMessage = tiv.getError().getErrorMessage();
        // Assert
        assertEquals(expected, errorMessage);
    }
}