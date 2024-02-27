package model.pojo.trade_transaction.trade_line_item;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import model.pojo.trade_transaction.application_header.ApplicableTradeTax;
import model.pojo.trade_transaction.trade_line_item.charge.SpecifiedTradeAllowanceCharge;

@JsonPropertyOrder({"applicableTradeTax", "specifiedTradeAllowanceCharge",
                    "specifiedTradeSettlementLineMonetarySummation"})
public class SpecifiedLineTradeSettlement {
    
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "ram:ApplicableTradeTax")
    private ApplicableTradeTax[] applicableTradeTax;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "ram:SpecifiedTradeAllowanceCharge")
    private SpecifiedTradeAllowanceCharge[] specifiedTradeAllowanceCharge;
    
    @JacksonXmlProperty(localName = "ram:SpecifiedTradeSettlementLineMonetarySummation")
    private SpecifiedTradeSettlementLineMonetarySummation specifiedTradeSettlementLineMonetarySummation;

    public ApplicableTradeTax[] getApplicableTradeTax() {
        return applicableTradeTax;
    }

    public void setApplicableTradeTax(ApplicableTradeTax[] applicableTradeTax) {
        this.applicableTradeTax = applicableTradeTax;
    }

    public SpecifiedTradeAllowanceCharge[] getSpecifiedTradeAllowanceCharge() {
        return specifiedTradeAllowanceCharge;
    }

    public void setSpecifiedTradeAllowanceCharge(SpecifiedTradeAllowanceCharge[] specifiedTradeAllowanceCharge) {
        this.specifiedTradeAllowanceCharge = specifiedTradeAllowanceCharge;
    }

    public SpecifiedTradeSettlementLineMonetarySummation getSpecifiedTradeSettlementLineMonetarySummation() {
        return specifiedTradeSettlementLineMonetarySummation;
    }

    public void setSpecifiedTradeSettlementLineMonetarySummation(
            SpecifiedTradeSettlementLineMonetarySummation specifiedTradeSettlementLineMonetarySummation) {
        this.specifiedTradeSettlementLineMonetarySummation = specifiedTradeSettlementLineMonetarySummation;
    }
}
