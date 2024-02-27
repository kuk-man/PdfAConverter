package model.pojo.common;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonPropertyOrder({"id", "globalID", "name", "definedTradeContact", "postalTradeAddress"})
public class ShipTradeParty extends TradeParty {

    @JacksonXmlProperty(localName = "ram:PostalTradeAddress")
    protected ShipTradePostalTradeAddress postalTradeAddress;

    public ShipTradePostalTradeAddress getPostalTradeAddress() {
        return postalTradeAddress;
    }

    public void setPostalTradeAddress(ShipTradePostalTradeAddress postalTradeAddress) {
        this.postalTradeAddress = postalTradeAddress;
    }
}
