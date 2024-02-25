package model.pojo.trade_transaction.application_header.tradeparty;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import model.pojo.common.ShipTradeParty;

@JacksonXmlRootElement(localName = "ram:ShipToTradeParty")
public class ShipToTradeParty extends ShipTradeParty {
}
