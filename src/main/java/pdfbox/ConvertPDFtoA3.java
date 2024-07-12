package pdfbox;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Collections;
import java.util.GregorianCalendar;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDDocumentNameDictionary;
import org.apache.pdfbox.pdmodel.PDEmbeddedFilesNameTreeNode;
import org.apache.pdfbox.pdmodel.common.PDMetadata;
import org.apache.pdfbox.pdmodel.common.filespecification.PDComplexFileSpecification;
import org.apache.pdfbox.pdmodel.common.filespecification.PDEmbeddedFile;
import org.apache.pdfbox.pdmodel.graphics.color.PDOutputIntent;
import org.verapdf.pdfa.Foundries;
import org.verapdf.pdfa.PDFAParser;
import org.verapdf.gf.foundry.VeraGreenfieldFoundryProvider;
import org.verapdf.pdfa.VeraPDFFoundry;
import org.verapdf.pdfa.flavours.PDFAFlavour;
import org.w3c.dom.Document;

public class ConvertPDFtoA3 {
	private static float pdfVer = 1.7f;

	public static String Convert(String sourceFile, Document envelopedXml, String colorProfilePath, String documentType, 
			String outputFileName, String documentVersion, String xmpTemplatePath) throws Exception {
		InputStream ins = new ByteArrayInputStream(javax.xml.bind.DatatypeConverter.parseBase64Binary(sourceFile));
		// PDDocument doc = PDDocument.load(ins);
		PDDocument doc = Loader.loadPDF(ins.readAllBytes());
		PDDocumentCatalog cat = makeA3compliant(doc, sourceFile, xmpTemplatePath, outputFileName, documentType, documentVersion);

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		if (cat == null) {
			throw new Exception("Please check input file.");
		} else {
			attachFile(doc, envelopedXml);

			File colorPFile = new File(colorProfilePath);
			InputStream colorProfile = new FileInputStream(colorPFile);
			addOutputIntent(doc, cat, colorProfile);
			
			doc.setVersion(pdfVer);
			doc.save(out);
			doc.close();

			// VeraGreenfieldFoundryProvider.initialise();
			// VeraPDFFoundry instance = Foundries.defaultInstance();

			// ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
			// try (PDFAParser parser = instance.createParser(in)) {
			// 	try (PDFAValidator validator = instance.createValidator(parser.getFlavour(), false)) {
			// 		ValidationResult result = validator.validate(parser);
			// 		if (!result.isCompliant()) {
			// 			throw new Exception("Please check input file.");
			// 		}
			// 	}
			// }
		}

		String destFile = Base64.getEncoder().encodeToString(out.toByteArray());
		return destFile;
	}

	private static void addOutputIntent(PDDocument doc, PDDocumentCatalog cat, InputStream colorProfile)
			throws IOException {
		if (cat.getOutputIntents().isEmpty()) {
			String value = "sRGB IEC61966-2.1";
			String registerName = "http://www.color.org";

			PDOutputIntent oi = new PDOutputIntent(doc, colorProfile);
			oi.setInfo(value);
			oi.setOutputCondition(value);
			oi.setOutputConditionIdentifier(value);
			oi.setRegistryName(registerName);
			cat.addOutputIntent(oi);
		}

	}

	private static void attachFile(PDDocument doc, Document envelopedXml) throws Exception {
		String embedFileName = "enveloped_xml.xml";
		String subType = "application/xml";

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		StringWriter stringWriter = new StringWriter();
		transformer.transform(new DOMSource(envelopedXml), new StreamResult(stringWriter));
		String xml = stringWriter.toString();

		PDComplexFileSpecification fs = new PDComplexFileSpecification();
		fs.setFile(embedFileName);
		COSDictionary dict = fs.getCOSObject();
		dict.setName("AFRelationship", "Source");
		dict.setString("UF", embedFileName);

		try (InputStream is = new ByteArrayInputStream(xml.getBytes()) ) {
			PDEmbeddedFile ef = new PDEmbeddedFile(doc, is);
			ef.setModDate(GregorianCalendar.getInstance());
			ef.setSize((int) xml.length());
			ef.setCreationDate(new GregorianCalendar());
			fs.setEmbeddedFile(ef);
			ef.setSubtype(subType);
		}

		setCosDictionary(embedFileName, fs, doc);
	}

	private static void setCosDictionary (String embedFileName, PDComplexFileSpecification fs, PDDocument doc) {
		PDEmbeddedFilesNameTreeNode efTree = new PDEmbeddedFilesNameTreeNode();
		efTree.setNames(Collections.singletonMap(embedFileName, fs));
		PDDocumentNameDictionary names = new PDDocumentNameDictionary(doc.getDocumentCatalog());
		names.setEmbeddedFiles(efTree);
		PDDocumentCatalog catalog = doc.getDocumentCatalog();
		catalog.setNames(names);

		COSDictionary dict2 = catalog.getCOSObject();
		COSArray array = new COSArray();
		array.add(fs.getCOSObject());
		dict2.setItem("AF", array);
	}

	private static PDDocumentCatalog makeA3compliant(PDDocument doc, String sourceFile, String xmpTemplatePath, String documentFileName, 
			String documentType, String documentVersion) throws Exception {
		PDDocumentCatalog cat = doc.getDocumentCatalog();
		PDDocumentInformation pdd = doc.getDocumentInformation();
		PDMetadata metadata = new PDMetadata(doc);
		cat.setMetadata(metadata);

		PDDocumentInformation pdi = new PDDocumentInformation();
		pdi.setProducer(pdd.getProducer());
		pdi.setAuthor(pdd.getAuthor());
		pdi.setTitle(pdd.getTitle());
		pdi.setSubject(pdd.getSubject());
		pdi.setKeywords(pdd.getKeywords());
		doc.setDocumentInformation(pdi);

		Charset charset = StandardCharsets.UTF_8;
		VeraGreenfieldFoundryProvider.initialise();
		VeraPDFFoundry instance = Foundries.defaultInstance();

		InputStream ins = new ByteArrayInputStream(javax.xml.bind.DatatypeConverter.parseBase64Binary(sourceFile));
		PDFAParser parser = instance.createParser(ins);
		// try (PDFAValidator validator = instance.createValidator(parser.getFlavour(), false)) {
			// ValidationResult result = validator.validate(parser);
			PDFAFlavour flavour = parser.getFlavour();
			String comformance = flavour.toString().substring(1);
			// if (!result.isCompliant() && !flavour.toString().equalsIgnoreCase("1b")) {
			// 	System.out.println("no");
			// 	System.out.println(flavour);
			// 	return null;
			// }
			
			byte[] fileBytes = Files.readAllBytes(new File(xmpTemplatePath).toPath());
			String content = new String(fileBytes, charset);
			content = content.replace("@DocumentFileName", documentFileName);
			content = content.replace("@DocumentType", documentType);
			content = content.replace("@DocumentVersion", documentVersion);
			content = content.replace("<pdfaid:conformance>", "<pdfaid:conformance>" + comformance.toUpperCase());

			byte[] editedBytes = content.getBytes(charset);
			metadata.importXMPMetadata(editedBytes);
		// }

		return cat;
	}
}
