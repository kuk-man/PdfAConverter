package model.pojo.trade_transaction.trade_line_item;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import model.pojo.common.Amount;

@JsonPropertyOrder({"taxTotalAmount", "netLineTotalAmount", "netIncludingTaxesLineTotalAmount"})
public class SpecifiedTradeSettlementLineMonetarySummation {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "ram:TaxTotalAmount")
    private Amount[] taxTotalAmount;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "ram:NetLineTotalAmount")
    private Amount[] netLineTotalAmount;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "ram:NetIncludingTaxesLineTotalAmount")
    private Amount[] netIncludingTaxesLineTotalAmount;

    public Amount[] getTaxTotalAmount() {
        return taxTotalAmount;
    }

    public void setTaxTotalAmount(Amount[] taxTotalAmount) {
        this.taxTotalAmount = taxTotalAmount;
    }

    public Amount[] getNetLineTotalAmount() {
        return netLineTotalAmount;
    }

    public void setNetLineTotalAmount(Amount[] netLineTotalAmount) {
        this.netLineTotalAmount = netLineTotalAmount;
    }

    public Amount[] getNetIncludingTaxesLineTotalAmount() {
        return netIncludingTaxesLineTotalAmount;
    }

    public void setNetIncludingTaxesLineTotalAmount(Amount[] netIncludingTaxesLineTotalAmount) {
        this.netIncludingTaxesLineTotalAmount = netIncludingTaxesLineTotalAmount;
    }

}
