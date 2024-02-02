package pdfbox;

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
		String inputFilePath = args[0];
		String embbedFilePath = args[1];
		String colorProfilePath = args[2];
		String outputFilePath = args[3];
		String documentType = args[4];
		String docFileName = args[5];
		String docVersion = args[6];
		String xmpTemplatePath = args[7];
		String pkType = args[8];

		signer.Xades signer = new signer.Xades(pkType);
		String envelopedXML = "src/main/resources/enveloped_xml.xml";
		signer.signEnvelopedXml(embbedFilePath, envelopedXML);

		PDFA3Components pdfa3Components = new PDFA3Components(inputFilePath, envelopedXML, colorProfilePath,
				outputFilePath, documentType, docFileName, docVersion, xmpTemplatePath);

		ConvertPDFtoA3.Convert(pdfa3Components);

		verifier.Xades verifier = new verifier.Xades();
		verifier.verifyEnvelopedXml(envelopedXML);
	}
}
