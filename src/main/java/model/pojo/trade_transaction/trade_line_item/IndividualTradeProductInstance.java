package model.pojo.trade_transaction.trade_line_item;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JsonPropertyOrder({"batchID", "expiryDateTime"})
@JacksonXmlRootElement(localName = "ram:IndividualTradeProductInstance")
public class IndividualTradeProductInstance {

    @JacksonXmlProperty(localName = "ram:BatchID")
    private String batchID;

    @JacksonXmlProperty(localName = "ram:ExpiryDateTime")
    private String expiryDateTime;

    public String getBatchID() {
        return batchID;
    }

    public void setBatchID(String batchID) {
        this.batchID = batchID;
    }

    public String getExpiryDateTime() {
        return expiryDateTime;
    }

    public void setExpiryDateTime(String expiryDateTime) {
        this.expiryDateTime = expiryDateTime;
    }
}
