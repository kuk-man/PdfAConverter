package rd.checker.rule;

import java.math.BigDecimal;
import java.util.List;

import model.pojo.common.Amount;
import model.pojo.common.Charge;
import model.pojo.trade_transaction.application_header.SpecifiedTradeSettlementHeaderMonetarySummation;
import rd.checker.Checker;

public class Rule_16 extends Checker {
    public void checkAllowanceCharge(Charge[] allowanceCharges, SpecifiedTradeSettlementHeaderMonetarySummation monetarySummation, 
        List<String> allowanceChargeTypeCodes, String object1, String object2) {
        for (Charge allowanceCharge : allowanceCharges) {
            // TypeCode
            if (!isNull(new N<>(() -> "" + allowanceCharge.getTypeCode()))) {
                String typeCode = allowanceCharge.getTypeCode();
                if (!allowanceChargeTypeCodes.contains(typeCode))
                    errors.setErrorMassage("Check " + object1 + "|TypeCode: TypeCode is not in the list. Current TypeCode = " + typeCode);
            }
        }
        checkAllowanceCharge(allowanceCharges, monetarySummation, object1, object2);
    }

    private void checkAllowanceCharge(Charge[] allowanceCharges, SpecifiedTradeSettlementHeaderMonetarySummation monetarySummation, 
        String object1, String object2) {
        BigDecimal sumAmount = new BigDecimal(0);
        for (Charge allowanceCharge : allowanceCharges) {
            // ChargeIndicator
            if (!isNull(new N<>(() -> "" + allowanceCharge.getChargeIndicator()))) {
                String chargeIndicator = allowanceCharge.getChargeIndicator();
                if (chargeIndicator.equals("true") || chargeIndicator.equals("false")) {
                    // ActualAmount
                    if (!isNull(new N<>(() -> "" + allowanceCharge.getActualAmount()))) {
                        Amount[] actualAmounts = allowanceCharge.getActualAmount();
                        for (Amount actualAmount : actualAmounts) {   
                            String value = actualAmount.getValue() == null ? "0.0" : actualAmount.getValue();
                            try {
                                BigDecimal amountValue = new BigDecimal(value);
                                sumAmount = sumAmount.add(amountValue);
                            } catch (NumberFormatException ex) {
                                errors.setErrorMassage("Check " + object1 + "|ActualAmount: Invalid ActualAmount (Decimal). Current ActualAmount = " + value);
                                return;
                            }
                        }
                    }
                    if (monetarySummation != null)
                        checkTotalAmount(chargeIndicator, sumAmount, monetarySummation, object1, object2);
                } else {
                    errors.setErrorMassage("Check " + object1 + "|ChargeIndicator: ChargeIndicator can be true/false only. Current ChargeIndicator = " + chargeIndicator);
                }
            }
        }
    }

    private void checkTotalAmount(String chargeIndicator, BigDecimal sumAmount, SpecifiedTradeSettlementHeaderMonetarySummation monetarySummation, 
        String object1, String object2) {
        BigDecimal totalAmount = new BigDecimal(0);
        switch (chargeIndicator) {
            case "true":
                // ChargeTotalAmount
                if (!isNull(new N<>(() -> "" + monetarySummation.getChargeTotalAmount()))) {
                    Amount[] totalAmounts = monetarySummation.getChargeTotalAmount();
                    for (Amount amount : totalAmounts) {
                        // Value
                        if (!isNull(new N<>(() -> "" + amount.getValue()))) {
                            String value = amount.getValue() == null ? "" : amount.getValue();
                            try {
                                BigDecimal amountValue = new BigDecimal(value);
                                totalAmount = totalAmount.add(amountValue);
                            } catch (NumberFormatException ex) {
                                errors.setErrorMassage("Check " + object2 + "|ChargeTotalAmount: Invalid ChargeTotalAmount (Decimal). Current ChargeTotalAmount = " + value);
                                return;
                            }
                        }
                    }
                    if (!sumAmount.equals(totalAmount)) {
                        errors.setErrorMassage("Check " + object1 + "|ActualAmount or " + object2 + "|ChargeTotalAmount: ChargeIndicator=true (Charge) then Sum ActualAmount must equal to TotalAmount. Current Sum ActualAmount = " + sumAmount.toString() + ", TotalAmount = " + totalAmount.toString());
                    }
                }
                break;
            case "false":
                // AllowanceTotalAmount
                if (!isNull(new N<>(() -> "" + monetarySummation.getAllowanceTotalAmount()))) {
                    Amount[] totalAmounts = monetarySummation.getAllowanceTotalAmount();
                    for (Amount amount : totalAmounts) {
                        // Value
                        if (!isNull(new N<>(() -> "" + amount.getValue()))) {
                            String value = amount.getValue() == null ? "" : amount.getValue();
                            try {
                                BigDecimal amountValue = new BigDecimal(value);
                                totalAmount = totalAmount.add(amountValue);
                            } catch (NumberFormatException ex) {
                                errors.setErrorMassage("Check " + object2 + "|AllowanceTotalAmount: Invalid AllowanceTotalAmount (Decimal). Current AllowanceTotalAmount = " + value);
                                return;
                            }
                        }
                    }
                    if (!sumAmount.equals(totalAmount)) {
                        errors.setErrorMassage("Check " + object1 + "|ActualAmount or " + object2 + "|AllowanceTotalAmount: ChargeIndicator=false (Allowance) then Sum ActualAmount must equal to TotalAmount (..|SpecifiedTradeSettlementHeaderMonetarySummation|AllowanceTotalAmount). Current Sum ActualAmount = " + sumAmount.toString() + ", TotalAmount = " + totalAmount.toString());
                    }
                }
            break;
            default:
            break;
        }
    }
}
