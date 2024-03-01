package rd.checker;

import java.math.BigDecimal;

import model.ErrorMessage;
import model.pojo.RootXml;
import model.pojo.common.Amount;
import model.pojo.trade_transaction.application_header.ApplicableTradeTax;
import model.pojo.trade_transaction.application_header.SpecifiedTradeSettlementHeaderMonetarySummation;

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

    private String checkCalculation(RootXml rootXml) {
        ErrorMessage errors = new ErrorMessage();

        if ("DCN".equals(rootXml.getTransaction())) {
            checkLineTotalAmount(rootXml, errors);
            checkGrandTotalAmount(rootXml, errors);
            checkDifferenceTotalAmount(rootXml, errors);
        }

        if (!errors.getErrorMessage().isBlank())
            return errors.getErrorMessage();
        return "";
    }

    // 3.3.5.2 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|LineTotalAmount
    // 3.3.2.3 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|ApplicableTradeTax|BasisAmount
    private void checkLineTotalAmount(RootXml rootXml, ErrorMessage errors) {
        // LineTotalAmount must equal to Sum(BasisAmount)
        // Sum LineTotalAmount
        String object1 = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|LineTotalAmount";
        boolean hasLineTotalAmount = false;
        BigDecimal sumLineTotalAmount = new BigDecimal(0);
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeSettlementHeaderMonetarySummation().getLineTotalAmount()))) {
            hasLineTotalAmount = true;
            for (Amount amount : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeSettlementHeaderMonetarySummation().getLineTotalAmount()) {
                if (!isNull(new N<>(() -> "" + amount.getValue()))) {
                    String value = amount.getValue() == null ? "" : amount.getValue();
                    try {
                        BigDecimal amountValue = new BigDecimal(value);
                        sumLineTotalAmount = sumLineTotalAmount.add(amountValue);
                    } catch (NullPointerException  ex) {
                        errors.setErrorMassage("Check " + object1 + ": Invalid LineTotalAmount (Decimal). Current LineTotalAmount = " + value);
                    }
                }
            }
        }
        
        // Sum BasisAmount
        String object2 = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|ApplicableTradeTax|BasisAmount";
        boolean hasBasisAmount = false;
        BigDecimal sumBasisAmount = new BigDecimal(0);
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getApplicableTradeTax()))) {
            for (ApplicableTradeTax att : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getApplicableTradeTax()) {
                if (!isNull(new N<>(() -> "" + att.getBasisAmount()))) {
                    hasBasisAmount = true;
                    for (Amount amount : att.getBasisAmount()) {
                        if (!isNull(new N<>(() -> "" + amount.getValue()))) {
                            String value = amount.getValue() == null ? "" : amount.getValue();
                            try {
                                BigDecimal amountValue = new BigDecimal(value);
                                sumBasisAmount = sumBasisAmount.add(amountValue);
                            } catch (NullPointerException ex) {
                                errors.setErrorMassage("Check " + object2 + ": Invalid BasisAmount (Decimal). Current BasisAmount = " + value);
                            }
                        }
                    }
                }
            }
        }

        if (hasBasisAmount && hasLineTotalAmount) {
            if (!sumLineTotalAmount.equals(sumBasisAmount))
                errors.setErrorMassage("Check " + object1 + " or " + object2 + ": LineTotalAmount must equal to Sum(BasisAmount). Current LineTotalAmount = " + sumLineTotalAmount.toString() + ", Sum(BasisAmount) = " + sumBasisAmount.toString());
        }
    }

    // 3.3.5.8 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|GrandTotalAmount
    // 3.3.5.3 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|DifferenceInformationAmount
    // 3.3.5.7 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|TaxTotalAmount
    private void checkGrandTotalAmount(RootXml rootXml, ErrorMessage errors) {
        // GrandTotalAmount = DifferenceInformationAmount + TaxTotalAmount
        String object1 = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|GrandTotalAmount";
        Boolean hasGrandTotalAmount = Boolean.FALSE;
        BigDecimal sumGrandTotalAmount = new BigDecimal(0);

        String object2 = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|DifferenceInformationAmount";
        Boolean hasDifferenceInformationAmount = Boolean.FALSE;
        BigDecimal sumDifferenceInformationAmount = new BigDecimal(0);

        String object3 = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|TaxTotalAmount";
        Boolean hasTaxTotalAmount = Boolean.FALSE;
        BigDecimal sumTaxTotalAmount = new BigDecimal(0);

        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeSettlementHeaderMonetarySummation()))) {
            SpecifiedTradeSettlementHeaderMonetarySummation stshms = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeSettlementHeaderMonetarySummation();

            if (!isNull(new N<>(() -> "" + stshms.getGrandTotalAmount()))) {
                hasGrandTotalAmount = Boolean.TRUE;
                for (Amount amount : stshms.getGrandTotalAmount()) {
                    if (!isNull(new N<>(() -> "" + amount.getValue()))) {
                        String value = amount.getValue() == null ? "" : amount.getValue();
                        try {
                            BigDecimal amountValue = new BigDecimal(value);
                            sumGrandTotalAmount = sumGrandTotalAmount.add(amountValue);
                        } catch (NullPointerException ex) {
                            errors.setErrorMassage("Check " + object1 + ": Invalid GrandTotalAmount (Decimal). Current GrandTotalAmount = " + value);
                        }
                    }
                }
            }
    
    
            if (!isNull(new N<>(() -> "" + stshms.getDifferenceInformationAmount()))) {
                hasDifferenceInformationAmount = Boolean.TRUE;
                for (Amount amount : stshms.getDifferenceInformationAmount()) {
                    if (!isNull(new N<>(() -> "" + amount.getValue()))) {
                        String value = amount.getValue() == null ? "" : amount.getValue();
                        try {
                            BigDecimal amountValue = new BigDecimal(value);
                            sumDifferenceInformationAmount = sumDifferenceInformationAmount.add(amountValue);
                        } catch (NullPointerException ex) {
                            errors.setErrorMassage("Check " + object2 + ": Invalid DifferenceInformationAmount (Decimal). Current DifferenceInformationAmount = " + value);
                        }
                    }
                }
            }
    
            if (!isNull(new N<>(() -> "" + stshms.getTaxTotalAmount()))) {
                hasTaxTotalAmount = Boolean.TRUE;
                for (Amount amount : stshms.getTaxTotalAmount()) {
                    if (!isNull(new N<>(() -> "" + amount.getValue()))) {
                        String value = amount.getValue() == null ? "" : amount.getValue();
                        try {
                            BigDecimal amountValue = new BigDecimal(value);
                            sumTaxTotalAmount = sumTaxTotalAmount.add(amountValue);
                        } catch (NullPointerException ex) {
                            errors.setErrorMassage("Check " + object3 + ": Invalid TaxTotalAmount (Decimal). Current TaxTotalAmount = " + value);
                        }
                    }
                }
            }
        }

        if (hasGrandTotalAmount.equals(Boolean.TRUE)) {
            if (hasDifferenceInformationAmount && hasTaxTotalAmount) {
                if (!sumGrandTotalAmount.equals(sumDifferenceInformationAmount.add(sumTaxTotalAmount))) {
                    errors.setErrorMassage("Check " + object1 + " or " + object2 + " or " + object3 + ": GrandTotalAmount must be equal to DifferenceInformationAmount + TaxTotalAmount. Current GrandTotalAmount = " + sumGrandTotalAmount.toString() + ", DifferenceInformationAmount = " + sumDifferenceInformationAmount.toString() + ", TaxTotalAmount = " + sumTaxTotalAmount.toString());
                }
            } else {
                errors.setErrorMassage("Check " + object2 + " or " + object3 + ": GrandTotalAmount Calculation must have DifferenceInformationAmount and TaxTotalAmount. Current Has DifferenceInformationAmount = " + hasDifferenceInformationAmount.toString() + ", Has TaxTotalAmount = " + hasTaxTotalAmount.toString());
            }
        }
    }

    // 3.3.5.3 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|DifferenceInformationAmount
    // 3.3.5.1 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|OriginalInformationAmount
    // 3.3.5.2 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|LineTotalAmount
    private void checkDifferenceTotalAmount(RootXml rootXml, ErrorMessage errors) {
        // DifferenceInformationAmount = OriginalInformationAmount - LineTotalAmount
        String object1 = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|DifferenceInformationAmount";
        Boolean hasDifferenceInformationAmount = Boolean.FALSE;
        BigDecimal sumDifferenceInformationAmount = new BigDecimal(0);

        String object2 = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|OriginalInformationAmount";
        Boolean hasOriginalInformationAmount = Boolean.FALSE;
        BigDecimal sumOriginalInformationAmount = new BigDecimal(0);

        String object3 = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|LineTotalAmount";
        Boolean hasLineTotalAmount = Boolean.FALSE;
        BigDecimal sumLineTotalAmount = new BigDecimal(0);

        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeSettlementHeaderMonetarySummation()))) {
            SpecifiedTradeSettlementHeaderMonetarySummation stshms = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeSettlementHeaderMonetarySummation();

            if (!isNull(new N<>(() -> "" + stshms.getDifferenceInformationAmount()))) {
                hasDifferenceInformationAmount = Boolean.TRUE;
                for (Amount amount : stshms.getDifferenceInformationAmount()) {
                    if (!isNull(new N<>(() -> "" + amount.getValue()))) {
                        String value = amount.getValue() == null ? "" : amount.getValue();
                        try {
                            BigDecimal amountValue = new BigDecimal(value);
                            sumDifferenceInformationAmount = sumDifferenceInformationAmount.add(amountValue);
                        } catch (NullPointerException ex) {
                            errors.setErrorMassage("Check " + object1 + ": Invalid DifferenceInformationAmount (Decimal). Current DifferenceInformationAmount = " + value);
                        }
                    }
                }
            }
    
    
            if (!isNull(new N<>(() -> "" + stshms.getOriginalInformationAmount()))) {
                hasOriginalInformationAmount = Boolean.TRUE;
                for (Amount amount : stshms.getOriginalInformationAmount()) {
                    if (!isNull(new N<>(() -> "" + amount.getValue()))) {
                        String value = amount.getValue() == null ? "" : amount.getValue();
                        try {
                            BigDecimal amountValue = new BigDecimal(value);
                            sumOriginalInformationAmount = sumOriginalInformationAmount.add(amountValue);
                        } catch (NullPointerException ex) {
                            errors.setErrorMassage("Check " + object2 + ": Invalid OriginalInformationAmount (Decimal). Current OriginalInformationAmount = " + value);
                        }
                    }
                }
            }
    
            if (!isNull(new N<>(() -> "" + stshms.getLineTotalAmount()))) {
                hasLineTotalAmount = Boolean.TRUE;
                for (Amount amount : stshms.getLineTotalAmount()) {
                    if (!isNull(new N<>(() -> "" + amount.getValue()))) {
                        String value = amount.getValue() == null ? "" : amount.getValue();
                        try {
                            BigDecimal amountValue = new BigDecimal(value);
                            sumLineTotalAmount = sumLineTotalAmount.add(amountValue);
                        } catch (NullPointerException ex) {
                            errors.setErrorMassage("Check " + object3 + ": Invalid LineTotalAmount (Decimal). Current LineTotalAmount = " + value);
                        }
                    }
                }
            }
        }

        if (hasDifferenceInformationAmount.equals(Boolean.TRUE)) {
            if (hasOriginalInformationAmount && hasLineTotalAmount) {
                if (!sumDifferenceInformationAmount.equals(sumOriginalInformationAmount.subtract(sumLineTotalAmount))) {
                    errors.setErrorMassage("Check " + object1 + " or " + object2 + " or " + object3 + ": DifferenceInformationAmount must be equal to OriginalInformationAmount - LineTotalAmount. Current DifferenceInformationAmount = " + sumDifferenceInformationAmount.toString() + ", OriginalInformationAmount = " + sumOriginalInformationAmount.toString() + ", LineTotalAmount = " + sumLineTotalAmount.toString());
                }
            } else {
                errors.setErrorMassage("Check " + object2 + " or " + object3 + ": DifferenceInformationAmount Calculation must have OriginalInformationAmount and LineTotalAmount. Current Has OriginalInformationAmount = " + hasOriginalInformationAmount.toString() + ", Has LineTotalAmount = " + hasLineTotalAmount.toString());
            }
        }
    }
}
