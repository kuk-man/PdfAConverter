package signer;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.X509Certificate;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.apache.xml.security.algorithms.MessageDigestAlgorithm;
import org.apache.xml.security.signature.XMLSignature;
import org.apache.xml.security.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import pdfa3.Config;
import xades4j.XAdES4jException;
import xades4j.algorithms.EnvelopedSignatureTransform;
import xades4j.production.DataObjectReference;
import xades4j.production.SignedDataObjects;
import xades4j.production.XadesBesSigningProfile;
import xades4j.production.XadesSignatureResult;
import xades4j.production.XadesSigner;
import xades4j.production.XadesSigningProfile;
import xades4j.production.SignatureAlgorithms;
import xades4j.properties.DataObjectDesc;
import xades4j.providers.KeyingDataProvider;
import xades4j.providers.SigningCertChainException;
import xades4j.providers.impl.FileSystemKeyStoreKeyingDataProvider;
import xades4j.providers.impl.KeyStoreKeyingDataProvider;
import xades4j.providers.impl.PKCS11KeyStoreKeyingDataProvider;
import xades4j.utils.XadesProfileResolutionException;
import xades4j.providers.impl.KeyStoreKeyingDataProvider.SigningCertificateSelector;
import xades4j.verification.UnexpectedJCAException;

public class Xades {
    private XadesSigner signer;
    private SignatureAlgorithms signatureAlgorithm;

	public Xades(String pkType){
        Config config = new Config();

        signatureAlgorithm = new SignatureAlgorithms()
            .withDigestAlgorithmForDataObjectReferences(MessageDigestAlgorithm.ALGO_ID_DIGEST_SHA512)
            .withDigestAlgorithmForReferenceProperties(MessageDigestAlgorithm.ALGO_ID_DIGEST_SHA512)
            .withSignatureAlgorithm("RSA", XMLSignature.ALGO_ID_SIGNATURE_RSA_SHA512);

        try {
            if (pkType.equals("PKCS11")) {
                signer = getSignerPkcs11(config);
            } else if (pkType.equals("PKCS12")) {
                signer = getSignerPkcs12(config, pkType);
            } else {
                throw new Exception("PK_TYPE_not_supported");
            }
        } catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Document signEnvelopedXml(String inputFile)
			throws TransformerFactoryConfigurationError, XAdES4jException, IOException, TransformerException, SAXException, ParserConfigurationException {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        Document sourceDoc = null;
        
        InputStream inputStream = new ByteArrayInputStream(inputFile.getBytes());
        sourceDoc = dbf.newDocumentBuilder().parse(inputStream);

        Element elementToSign = sourceDoc.getDocumentElement();
        String refUri;
        
        if (elementToSign.hasAttribute("Id")) {
            refUri = '#' + elementToSign.getAttribute("Id");
        } else {
            if (elementToSign.getParentNode().getNodeType() != Node.DOCUMENT_NODE) {
                throw new IllegalArgumentException("Element without Id must be the document root.");
            }
            refUri = "";
        }
            
        DataObjectDesc dataObjRef = new DataObjectReference(refUri).withTransform(new EnvelopedSignatureTransform());
        XadesSignatureResult result = signer.sign(new SignedDataObjects(dataObjRef), sourceDoc.getDocumentElement());
        XMLSignature signature = result.getSignature();
        Document doc = signature.getDocument();

        return doc;
	}

	public void signEnvelopedXml(String inputPath, String outputPath)
			throws TransformerFactoryConfigurationError, XAdES4jException, IOException {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        Document sourceDoc = null;
        
        try {
            sourceDoc = dbf.newDocumentBuilder().parse(inputPath);

            try (FileOutputStream bos = new FileOutputStream(outputPath)){
                Element elementToSign = sourceDoc.getDocumentElement();
                String refUri;
                
                if (elementToSign.hasAttribute("Id")) {
                    refUri = '#' + elementToSign.getAttribute("Id");
                } else {
                    if (elementToSign.getParentNode().getNodeType() != Node.DOCUMENT_NODE) {
                        throw new IllegalArgumentException("Element without Id must be the document root.");
                    }
                    refUri = "";
                }
                
                DataObjectDesc dataObjRef = new DataObjectReference(refUri).withTransform(new EnvelopedSignatureTransform());
                XadesSignatureResult result = signer.sign(new SignedDataObjects(dataObjRef), sourceDoc.getDocumentElement());
                XMLSignature signature = result.getSignature();
                Document docs = signature.getDocument();
                XMLUtils.outputDOM(docs, bos);
            }
        } catch (SAXException | ParserConfigurationException | NullPointerException ex) {
            ex.printStackTrace();
        }
	}

	public XadesSigner getSignerPkcs11(Config config) throws XadesProfileResolutionException {
        String libPath = config.getPkcs11LibPath();
        String providerName = config.getPkcs11ProviderName();
        int slotId = config.getPkcs11SlotId();
        String password = config.getPkcs11Password();

		KeyingDataProvider keyingProvider = getKeyingDataProvider(libPath, providerName, slotId, password);
		XadesSigningProfile signingProfile = new XadesBesSigningProfile(keyingProvider);
        signingProfile.withSignatureAlgorithms(signatureAlgorithm);

		return signingProfile.newSigner();
	}

	public XadesSigner getSignerPkcs12(Config config, String pkType) 
            throws SigningCertChainException, UnexpectedJCAException, XadesProfileResolutionException {

        String keyPath = config.getPkcs12Path();
        String password = config.getPkcs12Password();

        KeyingDataProvider keyingProvider = getKeyingDataProvider(keyPath, password, pkType);
        XadesSigningProfile signingProfile = new XadesBesSigningProfile(keyingProvider);
        signingProfile.withSignatureAlgorithms(signatureAlgorithm);

        return signingProfile.newSigner();
	}

	private KeyingDataProvider getKeyingDataProvider(String libPath, String providerName, int slotId, String password) {
        return PKCS11KeyStoreKeyingDataProvider
                    .builder(libPath, SigningCertificateSelector.single())
                    .providerName(providerName)
                    .slot(slotId)
                    .storePassword(new DirectPasswordProvider(password))
                    .entryPassword(null)
                    .fullChain(false)
                    .build();
	}

	private KeyingDataProvider getKeyingDataProvider(String keyPath, String password, String pkType)
			throws SigningCertChainException, UnexpectedJCAException {
        KeyingDataProvider keyingProvider = FileSystemKeyStoreKeyingDataProvider
                                                .builder(pkType, keyPath, SigningCertificateSelector.single())
                                                .storePassword(new DirectPasswordProvider(password))
                                                .entryPassword(new DirectPasswordProvider(password))
                                                .fullChain(false)
                                                .build();

		if (keyingProvider.getSigningCertificateChain().isEmpty()) {
			throw new IllegalArgumentException("Cannot initialize keystore with path " + keyPath);
		}
		return keyingProvider;
	}

    public class DirectPasswordProvider implements KeyStoreKeyingDataProvider.KeyStorePasswordProvider, KeyStoreKeyingDataProvider.KeyEntryPasswordProvider {
        private char[] password;

        public DirectPasswordProvider(String password)
        {
            this.password = password.toCharArray();
        }

        @Override
        public char[] getPassword()
        {
            return password;
        }

        @Override
        public char[] getPassword(String alias, X509Certificate certificate)
        {
            return password;
        }
    }
}
