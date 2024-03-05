package rd.checker.mandatory_field;

import org.junit.jupiter.api.Test;

import model.pojo.RootXml;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

class CancellationNoteMandatoryFieldTest {

    @Test
    void TestCheckRequiredField() throws Exception {
        // Arrange
        String jsonMessage = "{\"transaction\": \"CLN\",\"crossIndustryInvoice\": {\"exchangedDocumentContext\": {\"guidelineSpecifiedDocumentContextParameter\": {\"id\": {\"value\":\"ER3-2560\"}}},\"exchangedDocument\": {\"id\": \"INV01\",\"name\": \"ใบแจ้งยกเลิก (Cancellation note)\",\"typeCode\": \"T07\",\"issueDateTime\": \"2017-12-19T00:00:00\",\"creationDateTime\": \"2017-12-19T00:00:00\"},\"supplyChainTradeTransaction\": {\"applicableHeaderTradeAgreement\": {\"sellerTradeParty\": {\"name\": \"บริษัท ขยันหมั่นเพียร จำกัด\",\"specifiedTaxRegistration\": {\"id\": {\"value\":\"1111111111111\"}},\"definedTradeContact\": {\"emailURIUniversalCommunication\": {\"uriid\": \"natueal@example.com\"},\"telephoneUniversalCommunication\": {\"completeNumber\": \"+66-81234567\"}},\"postalTradeAddress\": {\"postcodeCode\": \"71180\",\"lineOne\": \"สำนักงานอุทยานแห่งชาติ\",\"cityName\": \"1007\",\"citySubDivisionName\": \"100701\",\"countryID\": \"TH\",\"countrySubDivisionID\": \"37\",\"buildingNumber\": \"777/777\"}},\"additionalReferencedDocument\": {\"issuerAssignedID\": \"abc\",\"issueDateTime\": \"2017-12-19T00:00:00\",\"referenceTypeCode\": \"388\"}},\"applicableHeaderTradeSettlement\": {\"invoicerTradeParty\": {\"name\": \"poi\",\"specifiedTaxRegistration\": {\"id\": {\"value\":\"3333333333333\"}},\"postalTradeAddress\": {\"postcodeCode\": \"11344\",\"cityName\": \"1007\",\"citySubDivisionName\": \"100702\",\"countryID\": \"TH\",\"countrySubDivisionID\": \"37\"}}}}}}";
        // Act
        ByteBuffer buffer = StandardCharsets.UTF_8.encode(jsonMessage); 
		String jsonMessageUtf8 = StandardCharsets.UTF_8.decode(buffer).toString();

        rd.Transaction txn = new rd.Transaction();
        RootXml rootXml = txn.convertJsonToPojo(jsonMessageUtf8);

        CancellationNoteMandatoryField cln = new CancellationNoteMandatoryField();
        cln.checkRequiredField(rootXml);
        String errorMessage = cln.getError().getErrorMessage();
        // Assert
        assertEquals("", errorMessage);
    }

    @Test 
    void TestCheckNotUsedField() throws Exception {
        // Arrange
        String jsonMessage = "{\"transaction\": \"CLN\",\"crossIndustryInvoice\": {\"exchangedDocumentContext\": {\"guidelineSpecifiedDocumentContextParameter\": {\"id\": {\"value\":\"ER3-2560\"}}},\"exchangedDocument\": {\"id\": \"INV01\",\"name\": \"ใบกากับภาษี (Tax Invoice)\",\"typeCode\": 388,\"issueDateTime\": \"2017-12-19T00:00:00\",\"creationDateTime\": \"2017-12-19T00:00:00\"},\"supplyChainTradeTransaction\": {\"applicableHeaderTradeAgreement\": {\"sellerTradeParty\": {\"name\": \"บริษัท ขยันหมั่นเพียร จำกัด\",\"specifiedTaxRegistration\": {\"id\": {\"value\":\"1111111111111\"}},\"definedTradeContact\": {\"emailURIUniversalCommunication\": {\"uriid\": \"natueal@example.com\"},\"telephoneUniversalCommunication\": {\"completeNumber\": \"+66-81234567\"}},\"postalTradeAddress\": {\"postcodeCode\": \"71180\",\"lineOne\": \"สำนักงานอุทยานแห่งชาติ\",\"cityName\": \"1007\",\"citySubDivisionName\": \"100701\",\"countryID\": \"TH\",\"countrySubDivisionID\": \"37\",\"buildingNumber\": \"777/777\"}},\"additionalReferencedDocument\": {\"issuerAssignedID\": \"abc\",\"issueDateTime\": \"2017-12-19T00:00:00\",\"referenceTypeCode\": \"388\"}},\"applicableHeaderTradeSettlement\": {\"invoicerTradeParty\": {\"name\": \"poi\",\"specifiedTaxRegistration\": {\"id\": {\"value\":\"3333333333333\"}},\"postalTradeAddress\": {\"postcodeCode\": \"11344\",\"cityName\": \"1007\",\"citySubDivisionName\": \"100702\",\"countryID\": \"TH\",\"countrySubDivisionID\": \"37\"}}}}}}";
        String expted = "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|DefinedTradeContact|PersonName\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|DefinedTradeContact|DepartmentName\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|DefinedTradeContact|EmailURIUniversalCommunication\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|DefinedTradeContact|EmailURIUniversalCommunication|URIID\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|DefinedTradeContact|TelephoneUniversalCommunication\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|DefinedTradeContact|TelephoneUniversalCommunication|CompleteNumber\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|DefinedTradeContact|PersonName\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|DefinedTradeContact|DepartmentName\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|DefinedTradeContact|TelephoneUniversalCommunication\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|DefinedTradeContact|TelephoneUniversalCommunication|CompleteNumber\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|DefinedTradeContact|PersonName\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|DefinedTradeContact|DepartmentName\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|DefinedTradeContact|TelephoneUniversalCommunication\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|DefinedTradeContact|TelephoneUniversalCommunication|CompleteNumber\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|ApplicableTradeTax|TypeCode\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|ApplicableTradeTax|CalculatedRate\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|ApplicableTradeTax|BasisAmount\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|ApplicableTradeTax|CalculatedAmount\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeAllowanceCharge|ChargeIndicator\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeAllowanceCharge|ActualAmount\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeAllowanceCharge|ReasonCode\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeAllowanceCharge|Reason\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeAllowanceCharge|TypeCode\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradePaymentTerms|Description\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradePaymentTerms|DueDateDateTime\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradePaymentTerms|TypeCode\n" + //
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
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|DefinedTradeContact|TelephoneUniversalCommunication|CompleteNumber\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|AssociatedDocumentLineDocument\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|AssociatedDocumentLineDocument|LineID\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|ID\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|GlobalID\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|Name\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|Description\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|IndividualTradeProductInstance\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|IndividualTradeProductInstance|BatchID\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|IndividualTradeProductInstance|ExpiryDateTime\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|DesignatedProductClassification\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|DesignatedProductClassification|ClassCode\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|DesignatedProductClassification|ClassName\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|OriginTradeCountry\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|OriginTradeCountry|ID\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|InformationNote\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|InformationNote|Subject\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|InformationNote|Content\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeAgreement\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeAgreement|GrossPriceProductTradePrice\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeAgreement|GrossPriceProductTradePrice|ChargeAmount\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeAgreement|GrossPriceProductTradePrice|AppliedTradeAllowanceCharge\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeAgreement|GrossPriceProductTradePrice|AppliedTradeAllowanceCharge|ChargeIndicator\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeAgreement|GrossPriceProductTradePrice|AppliedTradeAllowanceCharge|ActualAmount\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeAgreement|GrossPriceProductTradePrice|AppliedTradeAllowanceCharge|ReasonCode\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeAgreement|GrossPriceProductTradePrice|AppliedTradeAllowanceCharge|Reason\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeAgreement|GrossPriceProductTradePrice|AppliedTradeAllowanceCharge|TypeCode\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeDelivery\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeDelivery|BilledQuantity\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeDelivery|PerPackageUnitQuantity\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|ApplicableTradeTax\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|ApplicableTradeTax|TypeCode\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|ApplicableTradeTax|CalculatedRate\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|ApplicableTradeTax|BasisAmount\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|ApplicableTradeTax|CalculatedAmount\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|SpecifiedTradeAllowanceCharge\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|SpecifiedTradeAllowanceCharge|ChargeIndicator\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|SpecifiedTradeAllowanceCharge|ActualAmount\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|SpecifiedTradeAllowanceCharge|ReasonCode\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|SpecifiedTradeAllowanceCharge|Reason\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|SpecifiedTradeAllowanceCharge|TypeCode\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|SpecifiedTradeSettlementLineMonetarySummation\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|SpecifiedTradeSettlementLineMonetarySummation|TaxTotalAmount\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|SpecifiedTradeSettlementLineMonetarySummation|NetLineTotalAmount\n" + //
                        "Delete CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|SpecifiedTradeSettlementLineMonetarySummation|NetIncludingTaxesLineTotalAmount" + //
                        "\n";
        // Act
        ByteBuffer buffer = StandardCharsets.UTF_8.encode(jsonMessage); 
		String jsonMessageUtf8 = StandardCharsets.UTF_8.decode(buffer).toString();

        rd.Transaction txn = new rd.Transaction();
        RootXml rootXml = txn.convertJsonToPojo(jsonMessageUtf8);

        CancellationNoteMandatoryField cln = new CancellationNoteMandatoryField();
        cln.checkNotUsedField(rootXml);
        String errorMessage = cln.getError().getErrorMessage();
        // Assert
        assertEquals(expted, errorMessage);
    }
}
