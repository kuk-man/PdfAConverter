package model.pojo.common;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonPropertyOrder({"schemeID", "schemeAgencyID", "value"})
public class GlobalID extends ID{

    @JacksonXmlProperty(isAttribute = true)
    private String schemeAgencyID;

    public String getSchemeAgencyID() {
        return schemeAgencyID;
    }

    public void setSchemeAgencyID(String schemeAgencyID) {
        this.schemeAgencyID = schemeAgencyID;
    }
}
