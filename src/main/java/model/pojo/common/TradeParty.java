package model.pojo.common;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import model.pojo.trade_transaction.application_header.DefinedTradeContact;

@JsonPropertyOrder({"id", "globalID", "name", "definedTradeContact", "postalTradeAddress"})
public class TradeParty {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "ram:ID")
    protected ID[] id;
    
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "ram:GlobalID")
    protected GlobalID[] globalID;

    @JacksonXmlProperty(localName = "ram:Name")
    protected String name;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "ram:DefinedTradeContact")
    protected DefinedTradeContact[] definedTradeContact;

    public ID[] getId() {
        return id;
    }

    public void setId(ID[] id) {
        this.id = id;
    }

    public GlobalID[] getGlobalID() {
        return globalID;
    }

    public void setGlobalID(GlobalID[] globalID) {
        this.globalID = globalID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DefinedTradeContact[] getDefinedTradeContact() {
        return definedTradeContact;
    }

    public void setDefinedTradeContact(DefinedTradeContact[] definedTradeContact) {
        this.definedTradeContact = definedTradeContact;
    }
}
