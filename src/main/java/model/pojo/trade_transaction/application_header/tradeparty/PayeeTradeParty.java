package model.pojo.trade_transaction.application_header.tradeparty;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import model.pojo.common.InvolverTradeParty;

@JacksonXmlRootElement(localName = "ram:PayeeTradeParty")
public class PayeeTradeParty extends InvolverTradeParty {
}
