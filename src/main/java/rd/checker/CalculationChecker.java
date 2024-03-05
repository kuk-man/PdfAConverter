package rd.checker;

import java.math.BigDecimal;

import model.ErrorMessage;
import model.pojo.RootXml;
import model.pojo.common.Amount;
import model.pojo.trade_transaction.application_header.ApplicableTradeTax;
import model.pojo.trade_transaction.application_header.SpecifiedTradeSettlementHeaderMonetarySummation;
import rd.checker.calculation.Calculation;

public class CalculationChecker extends Checker {
    public CalculationChecker() { 
        errors = new ErrorMessage();
    }

    public String verifyCalculations(RootXml rootXml) {
        String errorMessage = checkCalculation(rootXml);
        if (!errorMessage.isBlank())
            return "Check Calculation:\n" + errorMessage;
        return "";
    }

    public String checkCalculation(RootXml rootXml) {
        ErrorMessage errors = new ErrorMessage();
        Calculation calculation = new Calculation();

        String object1 = "";
        String object2 = "";
        String object3 = "";

        if ("DCN".equals(rootXml.getTransaction())) {
            // 3.3.5.2 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|LineTotalAmount
            // 3.3.2.3 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|ApplicableTradeTax|BasisAmount
            object1 = "SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|LineTotalAmount";
            object2 = "SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|ApplicableTradeTax|BasisAmount";
            calculation.checkLineTotalAmount(rootXml, object1, object2);

            // 3.3.5.8 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|GrandTotalAmount
            // 3.3.5.3 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|DifferenceInformationAmount
            // 3.3.5.7 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|TaxTotalAmount
            object1 = "SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|GrandTotalAmount";
            object2 = "SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|DifferenceInformationAmount";
            object3 = "SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|TaxTotalAmount";
            calculation.checkGrandTotalAmount(rootXml, object1, object2, object3);

            // 3.3.5.3 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|DifferenceInformationAmount
            // 3.3.5.1 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|OriginalInformationAmount
            // 3.3.5.2 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|LineTotalAmount
            object1 = "SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|DifferenceInformationAmount";
            object2 = "SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|OriginalInformationAmount";
            object3 = "SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|LineTotalAmount";
            calculation.checkDifferenceTotalAmount(rootXml, object1, object2, object3);
        }

        errors = calculation.getError();
        if (!errors.getErrorMessage().isBlank())
            return errors.getErrorMessage();
        return "";
    }
}
