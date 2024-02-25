package model.pojo.trade_transaction.application_header.communication;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "rsm:EmailURIUniversalCommunication")
public class EmailURIUniversalCommunication {

    @JacksonXmlProperty(localName = "ram:URIID")
    private String uriID;

    public String getUriID() {
        return uriID;
    }

    public void setUriID(String uriID) {
        this.uriID = uriID;
    }
}
