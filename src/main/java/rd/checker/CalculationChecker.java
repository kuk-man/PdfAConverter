package rd.checker;

import model.ErrorMessage;
import model.pojo.RootXml;
import model.pojo.common.Amount;
import model.pojo.trade_transaction.application_header.ApplicableTradeTax;
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
        Calculation calculation = new Calculation();

        String object1 = "";
        String object2 = "";
        String object3 = "";

        boolean hasLineTotalAmount;
        boolean hasBasisAmount;
        boolean hasGrandTotalAmount;
        boolean hasDifferenceInformationAmount;
        boolean hasTaxTotalAmount;
        boolean hasOriginalInformationAmount;

        if ("DCN".equals(rootXml.getTransaction())) {
            // 3.3.5.2 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|LineTotalAmount
            // 3.3.2.3 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|ApplicableTradeTax|BasisAmount
            object1 = "SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|LineTotalAmount";
            object2 = "SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|ApplicableTradeTax|BasisAmount";
            hasLineTotalAmount = !isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeSettlementHeaderMonetarySummation().getLineTotalAmount()));
            hasBasisAmount = !isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getApplicableTradeTax()));
            if (hasLineTotalAmount && hasBasisAmount) {
                Amount[] lineTotalAmount = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeSettlementHeaderMonetarySummation().getLineTotalAmount();
                ApplicableTradeTax[] basisAmount = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getApplicableTradeTax();
                calculation.checkLineTotalAmount(lineTotalAmount, basisAmount, object1, object2);
            }

            // 3.3.5.8 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|GrandTotalAmount
            // 3.3.5.3 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|DifferenceInformationAmount
            // 3.3.5.7 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|TaxTotalAmount
            object1 = "SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|GrandTotalAmount";
            object2 = "SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|DifferenceInformationAmount";
            object3 = "SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|TaxTotalAmount";
            hasGrandTotalAmount = !isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeSettlementHeaderMonetarySummation().getGrandTotalAmount()));
            hasDifferenceInformationAmount = !isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeSettlementHeaderMonetarySummation().getDifferenceInformationAmount()));
            hasTaxTotalAmount = !isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeSettlementHeaderMonetarySummation().getTaxTotalAmount()));
            if (hasGrandTotalAmount) {
                if (hasDifferenceInformationAmount && hasTaxTotalAmount) {
                    Amount[] grandTotalAmount = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeSettlementHeaderMonetarySummation().getGrandTotalAmount();
                    Amount[] differenceInformationAmount = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeSettlementHeaderMonetarySummation().getDifferenceInformationAmount();
                    Amount[] taxTotalAmount = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeSettlementHeaderMonetarySummation().getTaxTotalAmount();
                    calculation.checkGrandTotalAmount(grandTotalAmount, differenceInformationAmount, taxTotalAmount, object1, object2, object3);
                } else {
                    errors.setErrorMassage("Check " + object2 + " or " + object3 + ": GrandTotalAmount Calculation must have DifferenceInformationAmount and TaxTotalAmount. Current Has DifferenceInformationAmount = " + hasDifferenceInformationAmount + ", Has TaxTotalAmount = " + hasTaxTotalAmount);
                }
            }

            // 3.3.5.3 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|DifferenceInformationAmount
            // 3.3.5.1 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|OriginalInformationAmount
            // 3.3.5.2 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|LineTotalAmount
            object1 = "SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|DifferenceInformationAmount";
            object2 = "SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|OriginalInformationAmount";
            object3 = "SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|LineTotalAmount";
            hasDifferenceInformationAmount = !isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeSettlementHeaderMonetarySummation().getDifferenceInformationAmount()));
            hasOriginalInformationAmount = !isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeSettlementHeaderMonetarySummation().getOriginalInformationAmount()));
            hasLineTotalAmount = !isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeSettlementHeaderMonetarySummation().getLineTotalAmount()));
            if (hasDifferenceInformationAmount) {
                if (hasOriginalInformationAmount && hasLineTotalAmount) {
                    Amount[] differenceInformationAmount = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeSettlementHeaderMonetarySummation().getDifferenceInformationAmount();
                    Amount[] originalInformationAmount = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeSettlementHeaderMonetarySummation().getOriginalInformationAmount();
                    Amount[] lineTotalAmount = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeSettlementHeaderMonetarySummation().getLineTotalAmount();
                    calculation.checkDifferenceTotalAmount(differenceInformationAmount, originalInformationAmount, lineTotalAmount, object1, object2, object3);
                } else {
                    errors.setErrorMassage("Check " + object2 + " or " + object3 + ": DifferenceInformationAmount Calculation must have OriginalInformationAmount and LineTotalAmount. Current Has OriginalInformationAmount = " + hasOriginalInformationAmount + ", Has LineTotalAmount = " + hasLineTotalAmount);
                }
            }
        }

        if (!errors.getErrorMessage().isBlank() || !calculation.getError().getErrorMessage().isBlank())
            return errors.getErrorMessage() + calculation.getError().getErrorMessage();
        return "";
    }
}
