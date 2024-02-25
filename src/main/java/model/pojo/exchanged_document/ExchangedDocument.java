package model.pojo.exchanged_document;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import model.pojo.common.ID;

@JsonPropertyOrder({"id", "name", "typeCode", "issueDateTime", "purpose", "purposeCode", "globalID", 
                    "creationDateTime", "includedNote"})
@JacksonXmlRootElement(localName = "rsm:ExchangedDocument")
public class ExchangedDocument {
    
    @JacksonXmlProperty(localName = "ram:ID")
    private String id;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "ram:Name")
    private String[] name;

    @JacksonXmlProperty(localName = "ram:TypeCode")
    private String typeCode;

    @JacksonXmlProperty(localName = "ram:IssueDateTime")
    private String issueDateTime;

    @JacksonXmlProperty(localName = "ram:Purpose")
    private String purpose;

    @JacksonXmlProperty(localName = "ram:PurposeCode")
    private String purposeCode;

    @JacksonXmlProperty(localName = "ram:GlobalID")
    private ID globalID;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "ram:CreationDateTime")
    private String[] creationDateTime;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "ram:IncludedNote")
    private IncludedNote[] includedNote;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getName() {
        return name;
    }

    public void setName(String[] name) {
        this.name = name;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getIssueDateTime() {
        return issueDateTime;
    }

    public void setIssueDateTime(String issueDateTime) {
        this.issueDateTime = issueDateTime;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getPurposeCode() {
        return purposeCode;
    }

    public void setPurposeCode(String purposeCode) {
        this.purposeCode = purposeCode;
    }

    public ID getGlobalID() {
        return globalID;
    }

    public void setGlobalID(ID globalID) {
        this.globalID = globalID;
    }

    public String[] getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(String[] creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public IncludedNote[] getIncludedNote() {
        return includedNote;
    }

    public void setIncludedNote(IncludedNote[] includedNote) {
        this.includedNote = includedNote;
    }

}
