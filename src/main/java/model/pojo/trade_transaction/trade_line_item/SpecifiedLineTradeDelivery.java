package model.pojo.trade_transaction.trade_line_item;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import model.pojo.common.Quantity;

@JsonPropertyOrder({"billedQuantity", "perPackageUnitQuantity"})
@JacksonXmlRootElement(localName = "ram:SpecifiedLineTradeDelivery")
public class SpecifiedLineTradeDelivery {

    @JacksonXmlProperty(localName = "ram:BilledQuantity")
    private Quantity billedQuantity;

    @JacksonXmlProperty(localName = "ram:PerPackageUnitQuantity")
    private Quantity perPackageUnitQuantity;

    public Quantity getBilledQuantity() {
        return billedQuantity;
    }

    public void setBilledQuantity(Quantity billedQuantity) {
        this.billedQuantity = billedQuantity;
    }

    public Quantity getPerPackageUnitQuantity() {
        return perPackageUnitQuantity;
    }

    public void setPerPackageUnitQuantity(Quantity perPackageUnitQuantity) {
        this.perPackageUnitQuantity = perPackageUnitQuantity;
    }

}
