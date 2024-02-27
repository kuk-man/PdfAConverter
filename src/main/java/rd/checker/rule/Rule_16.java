package rd.checker.rule;

import java.util.List;

import model.ErrorMessage;
import model.pojo.common.Amount;
import model.pojo.common.Charge;
import model.pojo.trade_transaction.application_header.SpecifiedTradeSettlementHeaderMonetarySummation;
import rd.checker.Checker;

public class Rule_16 extends Checker {
    public void checkAllowanceCharge(Charge[] allowanceCharges, SpecifiedTradeSettlementHeaderMonetarySummation monetarySummation, 
        List<String> allowanceChargeTypeCodes, String object1, String object2, ErrorMessage errors){
        for (Charge allowanceCharge : allowanceCharges) {
            // TypeCode
            if (!isNull(new N<>(() -> "" + allowanceCharge.getTypeCode()))) {
                String typeCode = allowanceCharge.getTypeCode();
                if (!allowanceChargeTypeCodes.contains(typeCode))
                    errors.setErrorMassage("Check " + object1 + "|TypeCode: TypeCode is not in the list. Current TypeCode = " + typeCode + "");
            }
        }
        checkAllowanceCharge(allowanceCharges, monetarySummation, object1, object2, errors);
    }

    private void checkAllowanceCharge(Charge[] allowanceCharges, SpecifiedTradeSettlementHeaderMonetarySummation monetarySummation, 
        String object1, String object2, ErrorMessage errors){
        Float sumAmount = 0.0f;
        for (Charge allowanceCharge : allowanceCharges) {
            // ChargeIndicator
            if (!isNull(new N<>(() -> "" + allowanceCharge.getChargeIndicator()))) {
                String chargeIndicator = allowanceCharge.getChargeIndicator();
                if (chargeIndicator.equals("True") || chargeIndicator.equals("False")) {
                    // ActualAmount
                    if (!isNull(new N<>(() -> "" + allowanceCharge.getActualAmount()))) {
                        Amount[] actualAmounts = allowanceCharge.getActualAmount();
                        for (Amount actualAmount : actualAmounts) {   
                            String value = actualAmount.getValue() == null ? "0.0" : actualAmount.getValue();
                            try {
                                sumAmount += Float.parseFloat(value);
                            } catch (NumberFormatException ex) {
                                errors.setErrorMassage("Check " + object1 + "|ActualAmount: Invalid ActualAmount (Float). Current ActualAmount = " + value);
                            }
                        }
                    }
                    if (monetarySummation != null)
                        checkTotalAmount(chargeIndicator, sumAmount, monetarySummation, object1, object2, errors);
                } else {
                    errors.setErrorMassage("Check " + object1 + "|ChargeIndicator: ChargeIndicator can be True/False only. Current ChargeIndicator = " + chargeIndicator + "");
                }
            }
        }
    }

    private void checkTotalAmount(String chargeIndicator, Float sumAmount, SpecifiedTradeSettlementHeaderMonetarySummation monetarySummation, 
        String object1, String object2, ErrorMessage errors) {
        Float totalAmount = 0.0f;
        switch (chargeIndicator) {
            case "True":
                // ChargeTotalAmount
                if (!isNull(new N<>(() -> "" + monetarySummation.getChargeTotalAmount()))) {
                    Amount[] totalAmounts = monetarySummation.getChargeTotalAmount();
                    for (Amount amount : totalAmounts) {
                        // Value
                        if (!isNull(new N<>(() -> "" + amount.getValue()))) {
                            String value = amount.getValue() == null ? "0.0" : amount.getValue();
                            try {
                                totalAmount += Float.parseFloat(value);
                            } catch (NumberFormatException ex) {
                                errors.setErrorMassage("Check " + object2 + "|ChargeTotalAmount: Invalid ChargeTotalAmount (Float). Current ChargeTotalAmount = " + value);
                            }
                        }
                    }
                    if (!sumAmount.equals(totalAmount)) {
                        errors.setErrorMassage("Check " + object1 + "|ActualAmount or " + object2 + "|ChargeTotalAmount: ChargeIndicator=True (Charge) then Sum ActualAmount must equal to TotalAmount. Current Sum ActualAmount = " + sumAmount.toString() + ", TotalAmount = " + totalAmount.toString());
                    }
                }
                break;
            case "False":
                // AllowanceTotalAmount
                if (!isNull(new N<>(() -> "" + monetarySummation.getAllowanceTotalAmount()))) {
                    Amount[] totalAmounts = monetarySummation.getAllowanceTotalAmount();
                    for (Amount amount : totalAmounts) {
                        // Value
                        if (!isNull(new N<>(() -> "" + amount.getValue()))) {
                            String value = amount.getValue() == null ? "0.0" : amount.getValue();
                            try {
                                totalAmount += Float.parseFloat(value);
                            } catch (NumberFormatException ex) {
                                errors.setErrorMassage("Check " + object2 + "|AllowanceTotalAmount: Invalid AllowanceTotalAmount (Float). Current AllowanceTotalAmount = " + value);
                            }
                        }
                    }
                    if (!sumAmount.equals(totalAmount)) {
                        errors.setErrorMassage("Check " + object1 + "|ActualAmount or " + object2 + "|AllowanceTotalAmount: ChargeIndicator=False (Allowance) then Sum ActualAmount must equal to TotalAmount (..|SpecifiedTradeSettlementHeaderMonetarySummation|AllowanceTotalAmount). Current Sum ActualAmount = " + sumAmount.toString() + ", TotalAmount = " + totalAmount.toString());
                    }
                }
            break;
            default:
            break;
        }
    }
}