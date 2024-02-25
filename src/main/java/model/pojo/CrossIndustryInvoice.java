package model.pojo;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import model.pojo.exchanged_document_context.ExchangedDocumentContext;
import model.pojo.trade_transaction.SupplyChainTradeTransaction;
import model.pojo.exchanged_document.ExchangedDocument;

@JsonPropertyOrder({"ExchangedDocumentContext", "ExchangedDocument", "SupplyChainTradeTransaction"})
public class CrossIndustryInvoice {
    
    @JacksonXmlProperty(localName = "rsm:ExchangedDocumentContext")
    private ExchangedDocumentContext exchangedDocumentContext;

    @JacksonXmlProperty(localName = "rsm:ExchangedDocument")
    private ExchangedDocument exchangedDocument;

    @JacksonXmlProperty(localName = "rsm:SupplyChainTradeTransaction")
    private SupplyChainTradeTransaction supplyChainTradeTransaction;

    public ExchangedDocumentContext getExchangedDocumentContext() {
        return exchangedDocumentContext;
    }

    public void setExchangedDocumentContext(ExchangedDocumentContext exchangedDocumentContext) {
        this.exchangedDocumentContext = exchangedDocumentContext;
    }

    public ExchangedDocument getExchangedDocument() {
        return exchangedDocument;
    }

    public void setExchangedDocument(ExchangedDocument exchangedDocument) {
        this.exchangedDocument = exchangedDocument;
    }

    public SupplyChainTradeTransaction getSupplyChainTradeTransaction() {
        return supplyChainTradeTransaction;
    }

    public void setSupplyChainTradeTransaction(SupplyChainTradeTransaction supplyChainTradeTransaction) {
        this.supplyChainTradeTransaction = supplyChainTradeTransaction;
    }
}
