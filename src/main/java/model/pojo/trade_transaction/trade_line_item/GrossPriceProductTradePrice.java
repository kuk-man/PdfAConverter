package model.pojo.trade_transaction.trade_line_item;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import model.pojo.common.Amount;
import model.pojo.trade_transaction.trade_line_item.charge.AppliedTradeAllowanceCharge;

@JsonPropertyOrder({"chargeAmount", "appliedTradeAllowanceCharge"})
public class GrossPriceProductTradePrice {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "ram:ChargeAmount")
    private Amount[] chargeAmount;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "ram:AppliedTradeAllowanceCharge")
    private AppliedTradeAllowanceCharge[] appliedTradeAllowanceCharge;

    public Amount[] getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(Amount[] chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public AppliedTradeAllowanceCharge[] getAppliedTradeAllowanceCharge() {
        return appliedTradeAllowanceCharge;
    }

    public void setAppliedTradeAllowanceCharge(AppliedTradeAllowanceCharge[] appliedTradeAllowanceCharge) {
        this.appliedTradeAllowanceCharge = appliedTradeAllowanceCharge;
    }
}
