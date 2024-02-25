package model.pojo.trade_transaction.application_header;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import model.pojo.common.Amount;

@JsonPropertyOrder({"originalInformationAmount", "lineTotalAmount", "differenceInformationAmount", 
                    "allowanceTotalAmount", "chargeTotalAmount", "taxBasisTotalAmount", "taxTotalAmount", 
                    "grandTotalAmount"})
public class SpecifiedTradeSettlementHeaderMonetarySummation {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "ram:OriginalInformationAmount")
    private Amount[] originalInformationAmount;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "ram:LineTotalAmount")
    private Amount[] lineTotalAmount;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "ram:DifferenceInformationAmount")
    private Amount[] differenceInformationAmount;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "ram:AllowanceTotalAmount")
    private Amount[] allowanceTotalAmount;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "ram:ChargeTotalAmount")
    private Amount[] chargeTotalAmount;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "ram:TaxBasisTotalAmount")
    private Amount[] taxBasisTotalAmount;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "ram:TaxTotalAmount")
    private Amount[] taxTotalAmount;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "ram:GrandTotalAmount")
    private Amount[] grandTotalAmount;

    public Amount[] getOriginalInformationAmount() {
        return originalInformationAmount;
    }

    public void setOriginalInformationAmount(Amount[] originalInformationAmount) {
        this.originalInformationAmount = originalInformationAmount;
    }

    public Amount[] getLineTotalAmount() {
        return lineTotalAmount;
    }

    public void setLineTotalAmount(Amount[] lineTotalAmount) {
        this.lineTotalAmount = lineTotalAmount;
    }

    public Amount[] getDifferenceInformationAmount() {
        return differenceInformationAmount;
    }

    public void setDifferenceInformationAmount(Amount[] differenceInformationAmount) {
        this.differenceInformationAmount = differenceInformationAmount;
    }

    public Amount[] getAllowanceTotalAmount() {
        return allowanceTotalAmount;
    }

    public void setAllowanceTotalAmount(Amount[] allowanceTotalAmount) {
        this.allowanceTotalAmount = allowanceTotalAmount;
    }

    public Amount[] getChargeTotalAmount() {
        return chargeTotalAmount;
    }

    public void setChargeTotalAmount(Amount[] chargeTotalAmount) {
        this.chargeTotalAmount = chargeTotalAmount;
    }

    public Amount[] getTaxBasisTotalAmount() {
        return taxBasisTotalAmount;
    }

    public void setTaxBasisTotalAmount(Amount[] taxBasisTotalAmount) {
        this.taxBasisTotalAmount = taxBasisTotalAmount;
    }

    public Amount[] getTaxTotalAmount() {
        return taxTotalAmount;
    }

    public void setTaxTotalAmount(Amount[] taxTotalAmount) {
        this.taxTotalAmount = taxTotalAmount;
    }

    public Amount[] getGrandTotalAmount() {
        return grandTotalAmount;
    }

    public void setGrandTotalAmount(Amount[] grandTotalAmount) {
        this.grandTotalAmount = grandTotalAmount;
    }
}
