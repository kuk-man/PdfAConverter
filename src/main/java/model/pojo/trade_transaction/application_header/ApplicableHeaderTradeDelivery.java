package model.pojo.trade_transaction.application_header;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import model.pojo.trade_transaction.application_header.tradeparty.ShipFromTradeParty;
import model.pojo.trade_transaction.application_header.tradeparty.ShipToTradeParty;

@JsonPropertyOrder({"shipToTradeParty", "shipFromTradeParty", "actualDeliverySupplyChainEvent"})
@JacksonXmlRootElement(localName = "ram:ApplicableHeaderTradeDelivery")
public class ApplicableHeaderTradeDelivery {
    
    @JacksonXmlProperty(localName = "ram:ShipToTradeParty")
    private ShipToTradeParty shipToTradeParty;

    @JacksonXmlProperty(localName = "ram:ShipFromTradeParty")
    private ShipFromTradeParty shipFromTradeParty;

    @JacksonXmlProperty(localName = "ram:ActualDeliverySupplyChainEvent")
    private ActualDeliverySupplyChainEvent actualDeliverySupplyChainEvent;

    public ShipToTradeParty getShipToTradeParty() {
        return shipToTradeParty;
    }

    public void setShipToTradeParty(ShipToTradeParty shipToTradeParty) {
        this.shipToTradeParty = shipToTradeParty;
    }

    public ShipFromTradeParty getShipFromTradeParty() {
        return shipFromTradeParty;
    }

    public void setShipFromTradeParty(ShipFromTradeParty shipFromTradeParty) {
        this.shipFromTradeParty = shipFromTradeParty;
    }

    public ActualDeliverySupplyChainEvent getActualDeliverySupplyChainEvent() {
        return actualDeliverySupplyChainEvent;
    }

    public void setActualDeliverySupplyChainEvent(ActualDeliverySupplyChainEvent actualDeliverySupplyChainEvent) {
        this.actualDeliverySupplyChainEvent = actualDeliverySupplyChainEvent;
    }
}
