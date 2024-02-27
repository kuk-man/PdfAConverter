package model.pojo.trade_transaction.trade_line_item;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class AssociatedDocumentLineDocument {

    @JacksonXmlProperty(localName = "ram:LineID")
    private String lineID;

    public String getLineID() {
        return lineID;
    }

    public void setLineID(String lineID) {
        this.lineID = lineID;
    }
}
