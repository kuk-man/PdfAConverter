package model.pojo.trade_transaction;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import model.pojo.trade_transaction.application_header.ApplicableHeaderTradeAgreement;
import model.pojo.trade_transaction.application_header.ApplicableHeaderTradeDelivery;
import model.pojo.trade_transaction.application_header.ApplicableHeaderTradeSettlement;
import model.pojo.trade_transaction.trade_line_item.IncludedSupplyChainTradeLineItem;

@JsonPropertyOrder({"shipToTradeParty", "shipFromTradeParty", "actualDeliverySupplyChainEvent"})
public class SupplyChainTradeTransaction {
    
    @JacksonXmlProperty(localName = "ram:ApplicableHeaderTradeAgreement")
    private ApplicableHeaderTradeAgreement applicableHeaderTradeAgreement;

    @JacksonXmlProperty(localName = "ram:ApplicableHeaderTradeDelivery")
    private ApplicableHeaderTradeDelivery applicableHeaderTradeDelivery;

    @JacksonXmlProperty(localName = "ram:ApplicableHeaderTradeSettlement")
    private ApplicableHeaderTradeSettlement applicableHeaderTradeSettlement;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "ram:IncludedSupplyChainTradeLineItem")
    private IncludedSupplyChainTradeLineItem[] includedSupplyChainTradeLineItem;

    public ApplicableHeaderTradeAgreement getApplicableHeaderTradeAgreement() {
        return applicableHeaderTradeAgreement;
    }

    public void setApplicableHeaderTradeAgreement(ApplicableHeaderTradeAgreement applicableHeaderTradeAgreement) {
        this.applicableHeaderTradeAgreement = applicableHeaderTradeAgreement;
    }

    public ApplicableHeaderTradeDelivery getApplicableHeaderTradeDelivery() {
        return applicableHeaderTradeDelivery;
    }

    public void setApplicableHeaderTradeDelivery(ApplicableHeaderTradeDelivery applicableHeaderTradeDelivery) {
        this.applicableHeaderTradeDelivery = applicableHeaderTradeDelivery;
    }

    public ApplicableHeaderTradeSettlement getApplicableHeaderTradeSettlement() {
        return applicableHeaderTradeSettlement;
    }

    public void setApplicableHeaderTradeSettlement(ApplicableHeaderTradeSettlement applicableHeaderTradeSettlement) {
        this.applicableHeaderTradeSettlement = applicableHeaderTradeSettlement;
    }

    public IncludedSupplyChainTradeLineItem[] getIncludedSupplyChainTradeLineItem() {
        return includedSupplyChainTradeLineItem;
    }
    
    public void setIncludedSupplyChainTradeLineItem(IncludedSupplyChainTradeLineItem[] includedSupplyChainTradeLineItem) {
        this.includedSupplyChainTradeLineItem = includedSupplyChainTradeLineItem;
    }    
}
