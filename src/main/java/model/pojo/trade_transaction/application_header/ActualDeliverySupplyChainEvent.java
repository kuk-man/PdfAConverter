package model.pojo.trade_transaction.application_header;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "ram:ActualDeliverySupplyChainEvent")
public class ActualDeliverySupplyChainEvent {

    @JacksonXmlProperty(localName = "ram:OccurrenceDateTime")
    private String occurrenceDateTime;

    public String getOccurrenceDateTime() {
        return occurrenceDateTime;
    }

    public void setOccurrenceDateTime(String occurrenceDateTime) {
        this.occurrenceDateTime = occurrenceDateTime;
    }
}
