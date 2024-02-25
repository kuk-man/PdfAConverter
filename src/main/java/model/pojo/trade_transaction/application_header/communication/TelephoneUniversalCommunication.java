package model.pojo.trade_transaction.application_header.communication;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class TelephoneUniversalCommunication {
    
    @JacksonXmlProperty(localName = "ram:CompleteNumber")
    private String completeNumber;

    public String getCompleteNumber() {
        return completeNumber;
    }

    public void setCompleteNumber(String completeNumber) {
        this.completeNumber = completeNumber;
    }

}
