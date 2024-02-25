package model.pojo.trade_transaction.trade_line_item;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonPropertyOrder({"associatedDocumentLineDocument", "specifiedTradeProduct",
                    "specifiedLineTradeAgreement", "specifiedLineTradeDelivery",
                    "specifiedLineTradeSettlement"})
public class IncludedSupplyChainTradeLineItem {

    @JacksonXmlProperty(localName = "ram:AssociatedDocumentLineDocument")
    private AssociatedDocumentLineDocument associatedDocumentLineDocument;

    @JacksonXmlProperty(localName = "ram:SpecifiedTradeProduct")
    private SpecifiedTradeProduct specifiedTradeProduct;

    @JacksonXmlProperty(localName = "ram:SpecifiedLineTradeAgreement")
    private SpecifiedLineTradeAgreement specifiedLineTradeAgreement;

    @JacksonXmlProperty(localName = "ram:SpecifiedLineTradeDelivery")
    private SpecifiedLineTradeDelivery specifiedLineTradeDelivery;

    @JacksonXmlProperty(localName = "ram:SpecifiedLineTradeSettlement")
    private SpecifiedLineTradeSettlement specifiedLineTradeSettlement;

    public AssociatedDocumentLineDocument getAssociatedDocumentLineDocument() {
        return associatedDocumentLineDocument;
    }

    public void setAssociatedDocumentLineDocument(AssociatedDocumentLineDocument associatedDocumentLineDocument) {
        this.associatedDocumentLineDocument = associatedDocumentLineDocument;
    }

    public SpecifiedTradeProduct getSpecifiedTradeProduct() {
        return specifiedTradeProduct;
    }

    public void setSpecifiedTradeProduct(SpecifiedTradeProduct specifiedTradeProduct) {
        this.specifiedTradeProduct = specifiedTradeProduct;
    }

    public SpecifiedLineTradeAgreement getSpecifiedLineTradeAgreement() {
        return specifiedLineTradeAgreement;
    }

    public void setSpecifiedLineTradeAgreement(SpecifiedLineTradeAgreement specifiedLineTradeAgreement) {
        this.specifiedLineTradeAgreement = specifiedLineTradeAgreement;
    }

    public SpecifiedLineTradeDelivery getSpecifiedLineTradeDelivery() {
        return specifiedLineTradeDelivery;
    }

    public void setSpecifiedLineTradeDelivery(SpecifiedLineTradeDelivery specifiedLineTradeDelivery) {
        this.specifiedLineTradeDelivery = specifiedLineTradeDelivery;
    }
    
    public SpecifiedLineTradeSettlement getSpecifiedLineTradeSettlement() {
        return specifiedLineTradeSettlement;
    }

    public void setSpecifiedLineTradeSettlement(SpecifiedLineTradeSettlement specifiedLineTradeSettlement) {
        this.specifiedLineTradeSettlement = specifiedLineTradeSettlement;
    }
}
