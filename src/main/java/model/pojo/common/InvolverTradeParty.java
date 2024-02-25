package model.pojo.common;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import model.pojo.trade_transaction.application_header.SpecifiedTaxRegistration;

@JsonPropertyOrder({"id", "globalID", "name", "specifiedTaxRegistration", "definedTradeContact", "postalTradeAddress"})
public class InvolverTradeParty extends TradeParty {

    @JacksonXmlProperty(localName = "ram:SpecifiedTaxRegistration")
    protected SpecifiedTaxRegistration specifiedTaxRegistration;

    @JacksonXmlProperty(localName = "ram:PostalTradeAddress")
    protected InvolverTradeAddress postalTradeAddress;

    public SpecifiedTaxRegistration getSpecifiedTaxRegistration() {
        return specifiedTaxRegistration;
    }

    public void setSpecifiedTaxRegistration(SpecifiedTaxRegistration specifiedTaxRegistration) {
        this.specifiedTaxRegistration = specifiedTaxRegistration;
    }

    public InvolverTradeAddress getPostalTradeAddress() {
        return postalTradeAddress;
    }

    public void setPostalTradeAddress(InvolverTradeAddress postalTradeAddress) {
        this.postalTradeAddress = postalTradeAddress;
    }
    
}
