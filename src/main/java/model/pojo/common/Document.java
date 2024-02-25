package model.pojo.common;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonPropertyOrder({"issuerAssignedID", "issueDateTime", "referenceTypeCode"})
public class Document {

    @JacksonXmlProperty(localName = "ram:IssuerAssignedID")
    protected String issuerAssignedID;

    @JacksonXmlProperty(localName = "ram:IssueDateTime")
    protected String issueDateTime;

    @JacksonXmlProperty(localName = "ram:ReferenceTypeCode")
    protected String referenceTypeCode;

    public String getIssuerAssignedID() {
        return issuerAssignedID;
    }

    public void setIssuerAssignedID(String issuerAssignedID) {
        this.issuerAssignedID = issuerAssignedID;
    }

    public String getIssueDateTime() {
        return issueDateTime;
    }

    public void setIssueDateTime(String issueDateTime) {
        this.issueDateTime = issueDateTime;
    }

    public String getReferenceTypeCode() {
        return referenceTypeCode;
    }

    public void setReferenceTypeCode(String referenceTypeCode) {
        this.referenceTypeCode = referenceTypeCode;
    }
}
