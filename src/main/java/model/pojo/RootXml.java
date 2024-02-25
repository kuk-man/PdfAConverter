package model.pojo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class RootXml {

    @JacksonXmlProperty(localName = "rsm:CrossIndustryInvoice")
    private CrossIndustryInvoice crossIndustryInvoice;

    private String transaction;

    public CrossIndustryInvoice getCrossIndustryInvoice() {
        return crossIndustryInvoice;
    }

    public void setCrossIndustryInvoice(CrossIndustryInvoice crossIndustryInvoice) {
        this.crossIndustryInvoice = crossIndustryInvoice;
    }

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }
}
