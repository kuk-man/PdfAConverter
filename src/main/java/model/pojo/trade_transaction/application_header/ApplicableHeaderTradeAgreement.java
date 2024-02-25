package model.pojo.trade_transaction.application_header;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import model.pojo.trade_transaction.application_header.document.AdditionalReferencedDocument;
import model.pojo.trade_transaction.application_header.document.BuyerOrderReferencedDocument;
import model.pojo.trade_transaction.application_header.tradeparty.BuyerTradeParty;
import model.pojo.trade_transaction.application_header.tradeparty.SellerTradeParty;

@JsonPropertyOrder({"sellerTradeParty", "applicableTradeDeliveryTerms", "buyerOrderReferencedDocument", 
                    "additionalReferencedDocument"})
public class ApplicableHeaderTradeAgreement {

    @JacksonXmlProperty(localName = "ram:SellerTradeParty")
    private SellerTradeParty sellerTradeParty;

    @JacksonXmlProperty(localName = "ram:BuyerTradeParty")
    private BuyerTradeParty buyerTradeParty;
    
    @JacksonXmlProperty(localName = "ram:ApplicableTradeDeliveryTerms")
    private ApplicableTradeDeliveryTerms applicableTradeDeliveryTerms;
    
    @JacksonXmlProperty(localName = "ram:BuyerOrderReferencedDocument")
    private BuyerOrderReferencedDocument buyerOrderReferencedDocument;
    
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "ram:AdditionalReferencedDocument")
    private AdditionalReferencedDocument[] additionalReferencedDocument;

    public SellerTradeParty getSellerTradeParty() {
        return sellerTradeParty;
    }

    public void setSellerTradeParty(SellerTradeParty sellerTradeParty) {
        this.sellerTradeParty = sellerTradeParty;
    }

    public BuyerTradeParty getBuyerTradeParty() {
        return buyerTradeParty;
    }

    public void setBuyerTradeParty(BuyerTradeParty buyerTradeParty) {
        this.buyerTradeParty = buyerTradeParty;
    }

    public ApplicableTradeDeliveryTerms getApplicableTradeDeliveryTerms() {
        return applicableTradeDeliveryTerms;
    }

    public void setApplicableTradeDeliveryTerms(ApplicableTradeDeliveryTerms applicableTradeDeliveryTerms) {
        this.applicableTradeDeliveryTerms = applicableTradeDeliveryTerms;
    }

    public BuyerOrderReferencedDocument getBuyerOrderReferencedDocument() {
        return buyerOrderReferencedDocument;
    }

    public void setBuyerOrderReferencedDocument(BuyerOrderReferencedDocument buyerOrderReferencedDocument) {
        this.buyerOrderReferencedDocument = buyerOrderReferencedDocument;
    }

    public AdditionalReferencedDocument[] getAdditionalReferencedDocument() {
        return additionalReferencedDocument;
    }

    public void setAdditionalReferencedDocument(AdditionalReferencedDocument[] additionalReferencedDocument) {
        this.additionalReferencedDocument = additionalReferencedDocument;
    }
}
