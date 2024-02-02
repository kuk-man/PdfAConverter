package verifier;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CRLException;
import java.security.cert.CertStore;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.CollectionCertStoreParameters;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

import javax.security.auth.x500.X500Principal;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.x509.AccessDescription;
import org.bouncycastle.asn1.x509.AuthorityInformationAccess;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.GeneralName;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import xades4j.XAdES4jException;
import xades4j.properties.QualifyingProperty;
import xades4j.properties.SignatureTimeStampProperty;
import xades4j.properties.SigningCertificateProperty;
import xades4j.properties.SigningTimeProperty;
import xades4j.providers.CertificateValidationProvider;
import xades4j.providers.impl.PKIXCertificateValidationProvider;
import xades4j.utils.FileSystemDirectoryCertStore;
import xades4j.verification.XAdESVerificationResult;
import xades4j.verification.XadesVerificationProfile;
import xades4j.verification.XadesVerifier;


public class Xades {
	private CertificateFactory cf;
	private XadesConfig config;
    private String configFilePath = "src/main/resources/config/etax-xades.properties";
    
	public Xades()
	{
        config = loadConfig(this.configFilePath);
		try {
			cf = CertificateFactory.getInstance("X509");
		} catch (CertificateException e) {
			e.printStackTrace();
		}
	}

	public void verifyEnvelopedXml(String filePath)
			throws ParserConfigurationException, SAXException, IOException, CertificateException,
			InvalidAlgorithmParameterException, NoSuchAlgorithmException, CRLException, KeyStoreException,
			NoSuchProviderException, XAdES4jException {

		Collection<X509Certificate> certChainList;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new FileInputStream(filePath));

		NodeList nl = doc.getElementsByTagNameNS(javax.xml.crypto.dsig.XMLSignature.XMLNS, "Signature");
		Element sigElem = (Element) nl.item(0);
		Element certElem = (Element) sigElem.getElementsByTagName("ds:X509Certificate").item(0);
		byte[] bencoded = javax.xml.bind.DatatypeConverter.parseBase64Binary(certElem.getTextContent());
		InputStream in = new ByteArrayInputStream(bencoded);
		
		cf = CertificateFactory.getInstance("X.509");		
		X509Certificate certSigner = (X509Certificate) cf.generateCertificate(in);
		certChainList = getCertChain(certSigner);
		
		CertificateValidationProvider provider = null;
		CertStore certStore = null;
		KeyStore ks;

        String storeDir = config.getCertStoreDir();
        String storeType = config.getTrustStoreType();
        String storePath = config.getTrustStorePath();
        String storePassword = config.getTrustStorePassword();

		if (!certChainList.isEmpty()) {
			X509Certificate[] certChain = new X509Certificate[certChainList.size()];
			certChainList.toArray(certChain);
			
			CollectionCertStoreParameters params = new CollectionCertStoreParameters(certChainList);
			certStore = CertStore.getInstance("Collection", params);
		}
		else { 
			//For etax seminar only
			certStore = new FileSystemDirectoryCertStore(storeDir).getStore();
		}
		
		if (storeType.equals("jks")) {
			
			try (FileInputStream fis = new FileInputStream(storePath)) {
				ks = KeyStore.getInstance(storeType);
				ks.load(fis, storePassword.toCharArray());
			}
            provider = PKIXCertificateValidationProvider
                        .builder(ks)
                        .checkRevocation(false)
                        .intermediateCertStores(certStore)
                        .build();

		} else if (storeType.equals("Windows-ROOT")) {
			ks = KeyStore.getInstance(storeType);
			ks.load(null, null);
            provider = PKIXCertificateValidationProvider
                        .builder(ks)
                        .checkRevocation(false)
                        .intermediateCertStores(certStore)
                        .build();
		}
				
		XadesVerificationProfile profile = new XadesVerificationProfile(provider);
		XadesVerifier verifier = profile.newVerifier();
		XAdESVerificationResult r = verifier.verify(sigElem, null);

		System.out.println("Signature form: " + r.getSignatureForm());
		System.out.println("Algorithm URI: " + r.getSignatureAlgorithmUri());
		System.out.println("Signed objects: " + r.getSignedDataObjects().size());
		System.out.println("Qualifying properties: " + r.getQualifyingProperties().all().size());

		for (QualifyingProperty qp : r.getQualifyingProperties().all()) {
			if ("SigningCertificate".equals(qp.getName())) {
				Collection<X509Certificate> certs = ((SigningCertificateProperty) qp).getsigningCertificateChain();
				certs.forEach((cert) -> {
					System.out.println(cert.getSubjectDN());
				});
			} else if ("SigningTime".equals(qp.getName())) {
				System.out.println(
						qp.getName() + ": " + ((SigningTimeProperty) qp).getSigningTime().getTime().toString());
			} else if ("SignatureTimeStamp".equals(qp.getName())) {
				System.out.println(qp.getName() + ": " + ((SignatureTimeStampProperty) qp).getTime().toString());
			} else {
				System.out.println("QP name: " + qp.getName());
			}
		}
	}
	
	private ArrayList<X509Certificate> getCertChain(X509Certificate cert) {		
		ArrayList<X509Certificate> certChain = new ArrayList<X509Certificate>();
		try {
			X500Principal subjectDN = cert.getSubjectX500Principal();
			X500Principal issuerDN = cert.getIssuerX500Principal();

			certChain.add(cert);
			while (!subjectDN.toString().equals(issuerDN.toString())) {
				cert = getAiaIssuerCert(cert);
				if (cert == null) {
					break;
				}
				certChain.add(cert);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return certChain;
	}

	private X509Certificate getAiaIssuerCert(X509Certificate cert) {
		
		X509Certificate issuerCert = null;
		String issuerUrl = null;

		try {
			issuerUrl = getAccessLocation(cert, org.bouncycastle.asn1.x509.X509ObjectIdentifiers.id_ad_caIssuers);
			if (issuerUrl != null) {
				InputStream inputStream = URI.create(issuerUrl).toURL().openStream();
				issuerCert = (X509Certificate) cf.generateCertificate(inputStream);
				return issuerCert;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return issuerCert;
	}
	
	private String getAccessLocation(final X509Certificate certificate, ASN1ObjectIdentifier accessMethod) 
			throws IOException {

		final byte[] authInfoAccessExtensionValue = certificate.getExtensionValue(Extension.authorityInfoAccess.getId());
		if (null == authInfoAccessExtensionValue) {
			return null;
		}

		final ByteArrayInputStream bais = new ByteArrayInputStream(authInfoAccessExtensionValue);
		ASN1InputStream ais1 = new ASN1InputStream(bais);

		final DEROctetString oct = (DEROctetString) (ais1.readObject());
		try (ASN1InputStream ais2 = new ASN1InputStream(oct.getOctets())){
			final AuthorityInformationAccess authorityInformationAccess = AuthorityInformationAccess.getInstance(ais2.readObject());

			System.out.println("Access Information Access: " + authorityInformationAccess);
			final AccessDescription[] accessDescriptions = authorityInformationAccess.getAccessDescriptions();
			for (AccessDescription accessDescription : accessDescriptions) {
				System.out.println("Access method: " + accessDescription.getAccessMethod());

				final boolean correctAccessMethod = accessDescription.getAccessMethod().equals(accessMethod);
				final GeneralName gn = accessDescription.getAccessLocation();
				if (!correctAccessMethod || (gn.getTagNo() != GeneralName.uniformResourceIdentifier)) {
					continue;
				}

				final DERIA5String str = (DERIA5String) gn.toASN1Primitive();
				final String accessLocation = str.getString();

				return accessLocation;
			}
		}
		return null;
	}

	private XadesConfig loadConfig(String configPath) {
        XadesConfig conf = new XadesConfig();

		try {
			Properties properties = new Properties();
			try (InputStream inputStream = new FileInputStream(configPath)){
				properties.load(inputStream);

				conf.setVerifyInput(properties.getProperty("VERIFY_INPUT_PATH"));
				conf.setTrustStoreType(properties.getProperty("TRUST_STORE_TYPE"));
				conf.setTrustStorePath(properties.getProperty("TRUST_STORE_PATH"));
				conf.setTrustStorePassword(properties.getProperty("TRUST_STORE_PASSWORD"));
				conf.setCertStoreDir(properties.getProperty("CERT_STORE_DIR"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

        return conf;
	}

    public class XadesConfig {
        String verifyInput;
        String trustStoreType;
        String trustStorePath;
        String trustStorePassword;
        String certStoreDir;

        public String getVerifyInput() {
            return verifyInput;
        }

        public void setVerifyInput(String verifyInput) {
            this.verifyInput = verifyInput;
        }

        public String getTrustStoreType() {
            return trustStoreType;
        }

        public void setTrustStoreType(String trustStoreType) {
            this.trustStoreType = trustStoreType;
        }

        public String getTrustStorePath() {
            return trustStorePath;
        }

        public void setTrustStorePath(String trustStorePath) {
            this.trustStorePath = trustStorePath;
        }

        public String getTrustStorePassword() {
            return trustStorePassword;
        }

        public void setTrustStorePassword(String trustStorePassword) {
            this.trustStorePassword = trustStorePassword;
        }

        public String getCertStoreDir() {
            return certStoreDir;
        }

        public void setCertStoreDir(String certStoreDir) {
            this.certStoreDir = certStoreDir;
        }
    }
}

