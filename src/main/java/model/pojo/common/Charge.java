package model.pojo.common;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JsonPropertyOrder({"chargeIndicator, actualAmount, reasonCode, reason, typeCode"})
@JacksonXmlRootElement(localName = "ram:Charge")
public class Charge {

    @JacksonXmlProperty(localName = "ram:ChargeIndicator")
    protected String chargeIndicator;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "ram:ActualAmount")
    protected Amount[] actualAmount;
    
    @JacksonXmlProperty(localName = "ram:ReasonCode")
    protected String reasonCode;
    
    @JacksonXmlProperty(localName = "ram:Reason")
    protected String reason;

    @JacksonXmlProperty(localName = "ram:TypeCode")
    protected String typeCode;

    public String getChargeIndicator() {
        return chargeIndicator;
    }

    public void setChargeIndicator(String chargeIndicator) {
        this.chargeIndicator = chargeIndicator;
    }

    public Amount[] getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(Amount[] actualAmount) {
        this.actualAmount = actualAmount;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }
}
