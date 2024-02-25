package model.pojo.trade_transaction.application_header;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class ApplicableTradeDeliveryTerms {
    
    @JacksonXmlProperty(localName = "ram:DeliveryTypeCode")
    private String deliveryTypeCode;

    public String getDeliveryTypeCode() {
        return deliveryTypeCode;
    }

    public void setDeliveryTypeCode(String deliveryTypeCode) {
        this.deliveryTypeCode = deliveryTypeCode;
    }
}
