package model.pojo.trade_transaction.application_header;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import model.pojo.common.ID;

public class SpecifiedTaxRegistration {
    
    @JacksonXmlProperty(localName = "ram:ID")
    private ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}
