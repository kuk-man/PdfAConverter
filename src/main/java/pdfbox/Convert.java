package pdfbox;

import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import org.w3c.dom.Document;

import pdfa3.Config;

public class Convert {
	public String getSignedDocument(String jsonMessage, String sourceFile, String outputFileName) throws Exception {
		Config config = new Config();
		String colorProfilePath = config.getColorProfilePath();
		String xmpTemplatePath = config.getXmpTempatePath();
		String documentVersion = config.getDocumentVersion();
		String pkType = config.getSelesctPkcs();

		// JSON to XML
		System.setOut(new PrintStream(System.out, true, "UTF-8"));
		ByteBuffer buffer = StandardCharsets.UTF_8.encode(jsonMessage); 
		String jsonMessageUtf8 = StandardCharsets.UTF_8.decode(buffer).toString();

		rd.Transaction transaction = new rd.Transaction();
		String errorMessage = transaction.processJson(jsonMessageUtf8);
		if (!errorMessage.isBlank()){
			throw new Exception(errorMessage);
		}
		String xml = transaction.generateXml();

		// sign XML
		signer.Xades signer = new signer.Xades(pkType);
		Document envelopedXml = signer.signEnvelopedXml(xml);

		// convert PDF & embeded signed XML
		String documentType = transaction.getDocumentType();
		String destFile = ConvertPDFtoA3.Convert(sourceFile, envelopedXml, colorProfilePath, documentType, outputFileName, documentVersion, xmpTemplatePath);
		return destFile;

		// verify embeded XML
		// TransformerFactory transformerFactory = TransformerFactory.newInstance();
		// Transformer transformer = transformerFactory.newTransformer();
		// StringWriter stringWriter = new StringWriter();
		// transformer.transform(new DOMSource(envelopedXml), new StreamResult(stringWriter));
		// InputStream inStream = new ByteArrayInputStream(stringWriter.toString().getBytes());

		// verifier.Xades verifier = new verifier.Xades();
		// return verifier.verifyEnvelopedXml(inStream);
	}
}