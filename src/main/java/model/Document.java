package model;

public class Document {
    String issuerAssignedID;
    String issueDateTime;
    String referenceTypeCode;

    public Document(String issuerAssignedID, String issueDateTime, String referenceTypeCode) {
        this.issuerAssignedID = issuerAssignedID;
        this.issueDateTime = issueDateTime;
        this.referenceTypeCode = referenceTypeCode;
    }

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
