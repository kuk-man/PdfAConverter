package model.pojo.trade_transaction.application_header;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import model.pojo.trade_transaction.application_header.tradeparty.InvoiceeTradeParty;
import model.pojo.trade_transaction.application_header.tradeparty.InvoicerTradeParty;
import model.pojo.trade_transaction.application_header.tradeparty.PayeeTradeParty;
import model.pojo.trade_transaction.application_header.tradeparty.PayerTradeParty;
import model.pojo.trade_transaction.trade_line_item.charge.SpecifiedTradeAllowanceCharge;

@JsonPropertyOrder({"invoiceCurrencyCode", "applicableTradeTax", "specifiedTradeAllowanceCharge",
                    "specifiedTradePaymentTerms", "specifiedTradeSettlementHeaderMonetarySummation", 
                    "invoicerTradeParty", "invoiceeTradeParty", "payerTradeParty", "payeeTradeParty"})
public class ApplicableHeaderTradeSettlement {

    @JacksonXmlProperty(localName = "ram:InvoiceCurrencyCode")
    private String invoiceCurrencyCode;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "ram:ApplicableTradeTax")
    private ApplicableTradeTax[] applicableTradeTax;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "ram:SpecifiedTradeAllowanceCharge")
    private SpecifiedTradeAllowanceCharge[] specifiedTradeAllowanceCharge;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "ram:SpecifiedTradePaymentTerms")
    private SpecifiedTradePaymentTerms[] specifiedTradePaymentTerms;
    
    @JacksonXmlProperty(localName = "ram:SpecifiedTradeSettlementHeaderMonetarySummation")
    private SpecifiedTradeSettlementHeaderMonetarySummation specifiedTradeSettlementHeaderMonetarySummation;

    @JacksonXmlProperty(localName = "ram:InvoicerTradeParty")
    private InvoicerTradeParty invoicerTradeParty;

    @JacksonXmlProperty(localName = "ram:InvoiceeTradeParty")
    private InvoiceeTradeParty invoiceeTradeParty;

    @JacksonXmlProperty(localName = "ram:PayerTradeParty")
    private PayerTradeParty payerTradeParty;

    @JacksonXmlProperty(localName = "ram:PayeeTradeParty")
    private PayeeTradeParty payeeTradeParty;

    public String getInvoiceCurrencyCode() {
        return invoiceCurrencyCode;
    }

    public void setInvoiceCurrencyCode(String invoiceCurrencyCode) {
        this.invoiceCurrencyCode = invoiceCurrencyCode;
    }

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

    public SpecifiedTradePaymentTerms[] getSpecifiedTradePaymentTerms() {
        return specifiedTradePaymentTerms;
    }

    public void setSpecifiedTradePaymentTerms(SpecifiedTradePaymentTerms[] specifiedTradePaymentTerms) {
        this.specifiedTradePaymentTerms = specifiedTradePaymentTerms;
    }

    public SpecifiedTradeSettlementHeaderMonetarySummation getSpecifiedTradeSettlementHeaderMonetarySummation() {
        return specifiedTradeSettlementHeaderMonetarySummation;
    }

    public void setSpecifiedTradeSettlementHeaderMonetarySummation(
            SpecifiedTradeSettlementHeaderMonetarySummation specifiedTradeSettlementHeaderMonetarySummation) {
        this.specifiedTradeSettlementHeaderMonetarySummation = specifiedTradeSettlementHeaderMonetarySummation;
    }

    public InvoicerTradeParty getInvoicerTradeParty() {
        return invoicerTradeParty;
    }

    public void setInvoicerTradeParty(InvoicerTradeParty invoicerTradeParty) {
        this.invoicerTradeParty = invoicerTradeParty;
    }

    public InvoiceeTradeParty getInvoiceeTradeParty() {
        return invoiceeTradeParty;
    }

    public void setInvoiceeTradeParty(InvoiceeTradeParty invoiceeTradeParty) {
        this.invoiceeTradeParty = invoiceeTradeParty;
    }

    public PayerTradeParty getPayerTradeParty() {
        return payerTradeParty;
    }

    public void setPayerTradeParty(PayerTradeParty payerTradeParty) {
        this.payerTradeParty = payerTradeParty;
    }

    public PayeeTradeParty getPayeeTradeParty() {
        return payeeTradeParty;
    }
    
    public void setPayeeTradeParty(PayeeTradeParty payeeTradeParty) {
        this.payeeTradeParty = payeeTradeParty;
    }
}
