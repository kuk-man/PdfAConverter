package model.pojo.trade_transaction.trade_line_item;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonPropertyOrder({"grossPriceProductTradePrice", "specifiedLineTradeDelivery", "specifiedLineTradeSettlement"})
public class SpecifiedLineTradeAgreement {

    @JacksonXmlProperty(localName = "ram:GrossPriceProductTradePrice")
    private GrossPriceProductTradePrice grossPriceProductTradePrice;

    @JacksonXmlProperty(localName = "ram:SpecifiedLineTradeDelivery")
    private SpecifiedLineTradeDelivery specifiedLineTradeDelivery;

    @JacksonXmlProperty(localName = "ram:SpecifiedLineTradeSettlement")
    private SpecifiedLineTradeSettlement specifiedLineTradeSettlement;

    public GrossPriceProductTradePrice getGrossPriceProductTradePrice() {
        return grossPriceProductTradePrice;
    }

    public void setGrossPriceProductTradePrice(GrossPriceProductTradePrice grossPriceProductTradePrice) {
        this.grossPriceProductTradePrice = grossPriceProductTradePrice;
    }

    public SpecifiedLineTradeDelivery getSpecifiedLineTradeDelivery() {
        return specifiedLineTradeDelivery;
    }

    public void setSpecifiedLineTradeDelivery(SpecifiedLineTradeDelivery specifiedLineTradeDelivery) {
        this.specifiedLineTradeDelivery = specifiedLineTradeDelivery;
    }

    public SpecifiedLineTradeSettlement getSpecifiedLineTradeSettlement() {
        return specifiedLineTradeSettlement;
    }

    public void setSpecifiedLineTradeSettlement(SpecifiedLineTradeSettlement specifiedLineTradeSettlement) {
        this.specifiedLineTradeSettlement = specifiedLineTradeSettlement;
    }
}
