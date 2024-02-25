package model.pojo.trade_transaction.trade_line_item.charge;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import model.pojo.common.Charge;

@JacksonXmlRootElement(localName = "ram:SpecifiedTradeAllowanceCharge")
public class SpecifiedTradeAllowanceCharge extends Charge{
}
