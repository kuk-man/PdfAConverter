package model.pojo.trade_transaction.trade_line_item;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "ram:OriginTradeCountry")
public class OriginTradeCountry {

    @JacksonXmlProperty(localName = "ram:ID")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
