package rd;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import model.pojo.CrossIndustryInvoice;
import model.pojo.RootXml;
import model.pojo.exchanged_document.ExchangedDocument;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

class TransactionTest {

    @Test
    void TestIsValidJson() throws IOException {
        // Arrange
        String passJson = "{\"transaction\": \"TIV\"}";
        String failJson = "{\"transaction\":: \"TIV\"}";
        // Act
        boolean passCase = (new Transaction()).isValidJson(passJson);
        boolean failCase = (new Transaction()).isValidJson(failJson);
        // Assert
        assertEquals(true, passCase);
        assertEquals(false, failCase);
    }

    @Test
    void TestConvertJsonToPojo() throws JsonProcessingException {
        // Arrange
        String json = "{\"transaction\": \"INV\",\"crossIndustryInvoice\": {\"exchangedDocument\": {\"id\": \"INV01\"}}}";
        
        ExchangedDocument ed = new ExchangedDocument();
        ed.setId("INV01");
        CrossIndustryInvoice cii = new CrossIndustryInvoice();
        cii.setExchangedDocument(ed);
        RootXml expected = new RootXml();
        expected.setTransaction("INV");
        expected.setCrossIndustryInvoice(cii);
        // Act
        RootXml rootXml = (new Transaction()).convertJsonToPojo(json);
        // Assert
        assertEquals(expected.getTransaction(), rootXml.getTransaction());
        assertEquals(expected.getCrossIndustryInvoice().getExchangedDocument().getId(), rootXml.getCrossIndustryInvoice().getExchangedDocument().getId());
    }

    @Test
    void TestProcessJson() throws IOException {
        // Arrange
        String wrongJson = "{\"transaction\":: \"TIV\"}";
        String missingFieldJson = "{\"transaction\": \"ABB\",\"crossIndustryInvoice\": {\"exchangedDocumentContext\": {\"guidelineSpecifiedDocumentContextParameter\": {\"id\": {\"value\":\"ER3-2560\"}}},\"exchangedDocument\": {\"name\": \"ใบกากับภาษี (Tax Invoice)\",\"typeCode\": 388,\"issueDateTime\": \"2017-12-19T00:00:00\",\"creationDateTime\": \"2017-12-19T00:00:00\"},\"supplyChainTradeTransaction\": {\"applicableHeaderTradeAgreement\": {\"sellerTradeParty\": {\"name\": \"บริษัท ขยันหมั่นเพียร จำกัด\",\"specifiedTaxRegistration\": {\"id\": {\"value\":\"1111111111111\"}},\"definedTradeContact\": {\"emailURIUniversalCommunication\": {\"uriid\": \"natueal@example.com\"},\"telephoneUniversalCommunication\": {\"completeNumber\": \"+66-81234567\"}},\"postalTradeAddress\": {\"postcodeCode\": \"71180\",\"lineOne\": \"สำนักงานอุทยานแห่งชาติ\",\"cityName\": \"1007\",\"citySubDivisionName\": \"100701\",\"countryID\": \"TH\",\"countrySubDivisionID\": \"37\",\"buildingNumber\": \"777/777\"}}},\"applicableHeaderTradeDelivery\": {\"shipToTradeParty\": {\"definedTradeContact\": {\"personName\": \"สมพร ใจงาม\"}},\"shipFromTradeParty\": {\"name\": \"iop\"}},\"applicableHeaderTradeSettlement\": {\"invoiceCurrencyCode\": \"THB\",\"applicableTradeTax\": {\"typeCode\": \"VAT\",\"calculatedRate\": \"7\",\"basisAmount\": {\"value\":\"9999\"},\"calculatedAmount\": {\"value\":\"699.93\"}},\"specifiedTradeSettlementHeaderMonetarySummation\": {\"lineTotalAmount\": {\"value\":\"9999\"},\"taxBasisTotalAmount\": {\"value\":\"699.93\"},\"taxTotalAmount\": {\"value\":\"699.93\"},\"grandTotalAmount\": {\"value\":\"10698.93\"}}},\"includedSupplyChainTradeLineItem\": {\"associatedDocumentLineDocument\": {\"lineID\": \"1\"},\"specifiedTradeProduct\": {\"name\": {\"value\":\"สินค้าทดสอบ\"}},\"specifiedLineTradeAgreement\": {\"grossPriceProductTradePrice\": {\"chargeAmount\": {\"value\":\"9999\"}}},\"specifiedLineTradeDelivery\": {\"billedQuantity\": {\"unitCode\": \"net_kilogram\", \"value\":\"1\"}},\"specifiedLineTradeSettlement\": {\"applicableTradeTax\": {\"typeCode\": \"123\",\"calculatedRate\": \"0\",\"basisAmount\": {\"value\":\"9999\"},\"calculatedAmount\": {\"value\":\"9999\"}},\"specifiedTradeSettlementLineMonetarySummation\": {\"netLineTotalAmount\": {\"currencyID\": \"THB\", \"value\":\"9999\"},\"netIncludingTaxesLineTotalAmount\": {\"currencyID\": \"THB\", \"value\":\"10698.93\"}}}}}}}";
        // Act
        Transaction txn = new Transaction();
        String expectedWrongJson = txn.processJson(wrongJson);
        String expectedMissingField = txn.processJson(missingFieldJson);
        // Assert
        assertEquals("Wrong JSON Format.", expectedWrongJson);
        assertEquals("Check Mandatory Fields:\n" + //
                    "Missing CrossIndustryInvoice|ExchangedDocument|ID\n", expectedMissingField);
    }

    // @Test
    // void TestGenerateXml() throws IOException {
    //     // Arrange
    //     String json = "{\"transaction\": \"INV\",\"crossIndustryInvoice\": {\"exchangedDocument\": {\"id\": \"INV01\"}}}";
    //     // Act
    //     Transaction txn = new Transaction();
    //     txn.processJson(json);
    //     String expected = "  <rsm:Invoice_CrossIndustryInvoice\n" + //
    //                     "  xmlns:rsm=\"urn:etda:uncefact:data:standard:Invoice_CrossIndustryInvoice:2\"\n" + //
    //                     "  xmlns:ram=\"urn:etda:uncefact:data:standard:Invoice_ReusableAggregateBusinessInformationEntity:2\"\n" + //
    //                     "  xmlns:ns3=\"http://www.w3.org/2000/09/xmldsig#\">\n" + //
    //                     "    <rsm:ExchangedDocument>\n" + //
    //                     "      <ram:ID>INV01</ram:ID>\n" + //
    //                     "    </rsm:ExchangedDocument>\n" + //
    //                     "  </rsm:Invoice_CrossIndustryInvoice>";
    //     // Assert
    //     assertEquals(expected, txn.generateXml());
    // }
}