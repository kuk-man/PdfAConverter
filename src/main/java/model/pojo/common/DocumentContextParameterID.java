package model.pojo.common;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

@JsonPropertyOrder({"schemeAgencyID", "schemeVersionID", "value"})
public class DocumentContextParameterID {

    public DocumentContextParameterID() {
        super();
    }
    
    @JacksonXmlProperty(isAttribute = true)
    private String schemeAgencyID;

    @JacksonXmlProperty(isAttribute = true)
    private String schemeVersionID;

    @JacksonXmlText
    private String value;

    public String getSchemeAgencyID() {
        return schemeAgencyID;
    }

    public void setSchemeAgencyID(String schemeAgencyID) {
        this.schemeAgencyID = schemeAgencyID;
    }

    public String getSchemeVersionID() {
        return schemeVersionID;
    }

    public void setSchemeVersionID(String schemeVersionID) {
        this.schemeVersionID = schemeVersionID;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
