package model.pojo.trade_transaction.application_header;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import model.pojo.common.Amount;

@JsonPropertyOrder({"typeCode", "calculatedRate", "basisAmount", "calculatedAmount"})
@JacksonXmlRootElement(localName = "ram:ApplicableTradeTax")
public class ApplicableTradeTax {

    @JacksonXmlProperty(localName = "ram:TypeCode")
    private String typeCode;

    @JacksonXmlProperty(localName = "ram:CalculatedRate")
    private String calculatedRate;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "ram:BasisAmount")
    private Amount[] basisAmount;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "ram:CalculatedAmount")
    private Amount[] calculatedAmount;

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getCalculatedRate() {
        return calculatedRate;
    }

    public void setCalculatedRate(String calculatedRate) {
        this.calculatedRate = calculatedRate;
    }

    public Amount[] getBasisAmount() {
        return basisAmount;
    }

    public void setBasisAmount(Amount[] basisAmount) {
        this.basisAmount = basisAmount;
    }

    public Amount[] getCalculatedAmount() {
        return calculatedAmount;
    }

    public void setCalculatedAmount(Amount[] calculatedAmount) {
        this.calculatedAmount = calculatedAmount;
    }
}
