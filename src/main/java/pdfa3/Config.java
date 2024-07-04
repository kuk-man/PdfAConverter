package pdfa3;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    String colorProfilePath;
    String xmpTempatePath;
    String documentVersion;
    String selesctPkcs;    
    String pkcs11LibPath;
    String pkcs11ProviderName;
    int pkcs11SlotId;
    String pkcs11Password;
    String pkcs12Path;
    String pkcs12Password;
    String trustStoreType;
    String trustStorePath;
    String trustStorePassword;
    String certStoreDir;

    public Config() {
        Properties properties = new Properties();
        try (InputStream inputStream = new FileInputStream("../config.properties")){
            properties.load(inputStream);        
        } catch (IOException e) {
            e.printStackTrace();
        }

        setColorProfilePath(properties.getProperty("COLOR_PROFILE_PATH"));
        setXmpTempatePath(properties.getProperty("XMP_TEMPLATE_PATH"));
        setDocumentVersion(properties.getProperty("DOCUMENT_VERSION"));
        setSelesctPkcs(properties.getProperty("SELECT_PKCS"));
        setPkcs11LibPath(properties.getProperty("PKCS11_LIB_PATH"));
        setPkcs11ProviderName(properties.getProperty("PKCS11_PROVIDER_NAME"));
        setPkcs11SlotId(Integer.parseInt(properties.getProperty("PKCS11_SLOT_ID")));
        setPkcs11Password(properties.getProperty("PKCS11_PASSWORD"));
        setPkcs12Path(properties.getProperty("PKCS12_PATH"));
        setPkcs12Password(properties.getProperty("PKCS12_PASSWORD"));
        setTrustStoreType(properties.getProperty("TRUST_STORE_TYPE"));
        setTrustStorePath(properties.getProperty("TRUST_STORE_PATH"));
        setTrustStorePassword(properties.getProperty("TRUST_STORE_PASSWORD"));
        setCertStoreDir(properties.getProperty("CERT_STORE_DIR"));
    }

    public String getColorProfilePath() {
        return colorProfilePath;
    }

    public void setColorProfilePath(String colorProfilePath) {
        this.colorProfilePath = colorProfilePath;
    }

    public String getXmpTempatePath() {
        return xmpTempatePath;
    }

    public void setXmpTempatePath(String xmpTempatePath) {
        this.xmpTempatePath = xmpTempatePath;
    }

    public String getDocumentVersion() {
        return documentVersion;
    }

    public void setDocumentVersion(String documentVersion) {
        this.documentVersion = documentVersion;
    }

    public String getSelesctPkcs() {
        return selesctPkcs;
    }

    public void setSelesctPkcs(String selesctPkcs) {
        this.selesctPkcs = selesctPkcs;
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

