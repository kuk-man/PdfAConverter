package pdfbox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Reader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Convert file input from PDF to PDF/A-3
 * 
 * @author ETDA
 *
 */

public class Convert {

	/**
	 * Input argument with full path of file.
	 * 
	 * 
	 * @param args[input file name,
	 *                   embed file name,
	 *                   color profile name,
	 *                   output filename,
	 *                   document type,
	 *                   document file name,
	 *                   document version]
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// // parameter
		// String inputFilePath = args[0];
		// String embbedFilePath = args[1];
		// String colorProfilePath = args[2];
		// String outputFilePath = args[3];
		// String documentType = args[4];
		// String docFileName = args[5];
		// String docVersion = args[6];
		// String xmpTemplatePath = args[7];
		// String pkType = args[8];

		// // sign XML
		// signer.Xades signer = new signer.Xades(pkType);
		// String envelopedXML = "src/main/resources/enveloped_xml.xml";
		// signer.signEnvelopedXml(embbedFilePath, envelopedXML);

		// // convert PDF & embeded signed XML
		// PDFA3Components pdfa3Components = new PDFA3Components(inputFilePath, envelopedXML, colorProfilePath,
		// 		outputFilePath, documentType, docFileName, docVersion, xmpTemplatePath);
		// ConvertPDFtoA3.Convert(pdfa3Components);

		// // verify embeded XML
		// verifier.Xades verifier = new verifier.Xades();
		// verifier.verifyEnvelopedXml(envelopedXML);

		// JSON to XML
		System.setOut(new PrintStream(System.out, true, "UTF-8"));
		String jsonMessage = "{\"transaction\": \"ABB\",\"crossIndustryInvoice\": {\"exchangedDocumentContext\": {\"guidelineSpecifiedDocumentContextParameter\": {\"id\": {\"value\":\"ER3-2560\"}}},\"exchangedDocument\": {\"name\": \"ใบกากับภาษี (Tax Invoice)\",\"typeCode\": 388,\"issueDateTime\": \"2017-12-19T00:00:00\",\"creationDateTime\": \"2017-12-19T00:00:00\"},\"supplyChainTradeTransaction\": {\"applicableHeaderTradeAgreement\": {\"sellerTradeParty\": {\"name\": \"บริษัท ขยันหมั่นเพียร จำกัด\",\"specifiedTaxRegistration\": {\"id\": {\"value\":\"1111111111111\"}},\"definedTradeContact\": {\"emailURIUniversalCommunication\": {\"uriid\": \"natueal@example.com\"},\"telephoneUniversalCommunication\": {\"completeNumber\": \"+66-81234567\"}},\"postalTradeAddress\": {\"postcodeCode\": \"71180\",\"lineOne\": \"สำนักงานอุทยานแห่งชาติ\",\"cityName\": \"1007\",\"citySubDivisionName\": \"100701\",\"countryID\": \"TH\",\"countrySubDivisionID\": \"37\",\"buildingNumber\": \"777/777\"}}},\"applicableHeaderTradeDelivery\": {\"shipToTradeParty\": {\"definedTradeContact\": {\"personName\": \"สมพร ใจงาม\"}},\"shipFromTradeParty\": {\"name\": \"iop\"}},\"applicableHeaderTradeSettlement\": {\"invoiceCurrencyCode\": \"THB\",\"applicableTradeTax\": {\"typeCode\": \"VAT\",\"calculatedRate\": \"7\",\"basisAmount\": {\"value\":\"9999\"},\"calculatedAmount\": {\"value\":\"699.93\"}},\"specifiedTradeSettlementHeaderMonetarySummation\": {\"lineTotalAmount\": {\"value\":\"9999\"},\"taxBasisTotalAmount\": {\"value\":\"699.93\"},\"taxTotalAmount\": {\"value\":\"699.93\"},\"grandTotalAmount\": {\"value\":\"10698.93\"}}},\"includedSupplyChainTradeLineItem\": {\"associatedDocumentLineDocument\": {\"lineID\": \"1\"},\"specifiedTradeProduct\": {\"name\": {\"value\":\"สินค้าทดสอบ\"}},\"specifiedLineTradeAgreement\": {\"grossPriceProductTradePrice\": {\"chargeAmount\": {\"value\":\"9999\"}}},\"specifiedLineTradeDelivery\": {\"billedQuantity\": {\"unitCode\": \"net_kilogram\", \"value\":\"1\"}},\"specifiedLineTradeSettlement\": {\"applicableTradeTax\": {\"typeCode\": \"123\",\"calculatedRate\": \"0\",\"basisAmount\": {\"value\":\"9999\"},\"calculatedAmount\": {\"value\":\"9999\"}},\"specifiedTradeSettlementLineMonetarySummation\": {\"netLineTotalAmount\": {\"currencyID\": \"THB\", \"value\":\"9999\"},\"netIncludingTaxesLineTotalAmount\": {\"currencyID\": \"THB\", \"value\":\"10698.93\"}}}}}}}";
		ByteBuffer buffer = StandardCharsets.UTF_8.encode(jsonMessage); 
		String jsonMessageUtf8 = StandardCharsets.UTF_8.decode(buffer).toString();
		
		rd.Transaction transaction = new rd.Transaction();
		String errorMessage = transaction.processJson(jsonMessageUtf8);
		if (errorMessage.isBlank()){
			String xml = transaction.generateXml();
			System.out.println(xml);
		} else {
			System.out.println(errorMessage);
		}

		
	}
}