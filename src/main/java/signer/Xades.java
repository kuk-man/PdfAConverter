package signer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.security.cert.X509Certificate;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import org.apache.xml.security.algorithms.MessageDigestAlgorithm;
import org.apache.xml.security.signature.XMLSignature;
import org.apache.xml.security.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
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
    private String configFilePath = "src/main/resources/config/etax-xades.properties";

	public Xades(String pkType){
        XadesConfig config = loadConfig(this.configFilePath);

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

	public XadesSigner getSignerPkcs11(XadesConfig config) throws XadesProfileResolutionException {
        String libPath = config.getPkcs11LibPath();
        String providerName = config.getPkcs11ProviderName();
        int slotId = config.getPkcs11SlotId();
        String password = config.getPkcs11Password();

		KeyingDataProvider keyingProvider = getKeyingDataProvider(libPath, providerName, slotId, password);
		XadesSigningProfile signingProfile = new XadesBesSigningProfile(keyingProvider);
        signingProfile.withSignatureAlgorithms(signatureAlgorithm);

		return signingProfile.newSigner();
	}

	public XadesSigner getSignerPkcs12(XadesConfig config, String pkType) 
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

	private XadesConfig loadConfig(String configPath) {
        XadesConfig conf = new XadesConfig();

        try {
            Properties properties = new Properties();
            try (InputStream inputStream = new FileInputStream(configPath)){
                properties.load(inputStream);        
                conf.setXmlInput(properties.getProperty("SIGN_INPUT_PATH"));
                conf.setXmlOutput(properties.getProperty("SIGN_OUTPUT_PATH"));
                conf.setPkcs11LibPath(properties.getProperty("PKCS11_LIB_PATH"));
                    conf.setPkcs11ProviderName(properties.getProperty("PKCS11_PROVIDER_NAME"));
                    conf.setPkcs11SlotId(Integer.parseInt(properties.getProperty("PKCS11_SLOT_ID")));
                    conf.setPkcs11Password(properties.getProperty("PKCS11_PASSWORD"));
                    conf.setPkcs12Path(properties.getProperty("PKCS12_PATH"));
                    conf.setPkcs12Password(properties.getProperty("PKCS12_PASSWORD"));
                }

            } catch (IOException e) {
                e.printStackTrace();
        }
            return conf;
	}

    public class XadesConfig {
        String xmlInput;
        String xmlOutput;    
        String pkcs11LibPath;
        String pkcs11ProviderName;
        int pkcs11SlotId;
        String pkcs11Password;
        String pkcs12Path;
        String pkcs12Password;

        public String getXmlInput() {
            return xmlInput;
        }

        public void setXmlInput(String xmlInput) {
            this.xmlInput = xmlInput;
        }

        public String getXmlOutput() {
            return xmlOutput;
        }

        public void setXmlOutput(String xmlOutput) {
            this.xmlOutput = xmlOutput;
        }

        public String getPkcs11LibPath() {
            return pkcs11LibPath;
        }

        public void setPkcs11LibPath(String pkcs11LibPath) {
            this.pkcs11LibPath = pkcs11LibPath;
        }

        public String getPkcs11ProviderName() {
            return pkcs11ProviderName;
        }

        public void setPkcs11ProviderName(String pkcs11ProviderName) {
            this.pkcs11ProviderName = pkcs11ProviderName;
        }

        public int getPkcs11SlotId() {
            return pkcs11SlotId;
        }

        public void setPkcs11SlotId(int pkcs11SlotId) {
            this.pkcs11SlotId = pkcs11SlotId;
        }

        public String getPkcs11Password() {
            return pkcs11Password;
        }

        public void setPkcs11Password(String pkcs11Password) {
            this.pkcs11Password = pkcs11Password;
        }

        public String getPkcs12Path() {
            return pkcs12Path;
        }

        public void setPkcs12Path(String pkcs12Path) {
            this.pkcs12Path = pkcs12Path;
        }
        
        public String getPkcs12Password() {
            return pkcs12Password;
        }

        public void setPkcs12Password(String pkcs12Password) {
            this.pkcs12Password = pkcs12Password;
        }
    }
}
