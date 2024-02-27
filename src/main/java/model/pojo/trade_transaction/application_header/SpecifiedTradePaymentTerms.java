package model.pojo.trade_transaction.application_header;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import model.pojo.common.Description;

@JsonPropertyOrder({"description", "dueDateDateTime", "typeCode"})
public class SpecifiedTradePaymentTerms {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "ram:Description")
    private Description[] description;

    @JacksonXmlProperty(localName = "ram:DueDateDateTime")
    private String dueDateDateTime;

    @JacksonXmlProperty(localName = "ram:TypeCode")
    private String typeCode;

    public Description[] getDescription() {
        return description;
    }

    public void setDescription(Description[] description) {
        this.description = description;
    }

    public String getDueDateDateTime() {
        return dueDateDateTime;
    }

    public void setDueDateDateTime(String dueDateDateTime) {
        this.dueDateDateTime = dueDateDateTime;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }
}
