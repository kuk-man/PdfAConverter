package rd.checker.calculation;

import java.math.BigDecimal;

import model.pojo.common.Amount;
import model.pojo.trade_transaction.application_header.ApplicableTradeTax;
import rd.checker.Checker;

public class Calculation extends Checker {

    // LineTotalAmount must equal to Sum(BasisAmount)
    public void checkLineTotalAmount(Amount[] lineTotalAmount, ApplicableTradeTax[] basisAmount, String object1, String object2) {
        BigDecimal sumLineTotalAmount = new BigDecimal(0);
        BigDecimal sumBasisAmount = new BigDecimal(0);

        for (Amount amount : lineTotalAmount) {
            if (!isNull(new N<>(() -> "" + amount.getValue()))) {
                String value = amount.getValue() == null ? "" : amount.getValue();
                try {
                    BigDecimal amountValue = new BigDecimal(value);
                    sumLineTotalAmount = sumLineTotalAmount.add(amountValue);
                } catch (NumberFormatException  ex) {
                    errors.setErrorMassage("Check " + object1 + ": Invalid LineTotalAmount (Decimal). Current LineTotalAmount = " + value);
                }
            }
        }

        for (ApplicableTradeTax att : basisAmount) {
            if (!isNull(new N<>(() -> "" + att.getBasisAmount()))) {
                for (Amount amount : att.getBasisAmount()) {
                    if (!isNull(new N<>(() -> "" + amount.getValue()))) {
                        String value = amount.getValue() == null ? "" : amount.getValue();
                        try {
                            BigDecimal amountValue = new BigDecimal(value);
                            sumBasisAmount = sumBasisAmount.add(amountValue);
                        } catch (NumberFormatException ex) {
                            errors.setErrorMassage("Check " + object2 + ": Invalid BasisAmount (Decimal). Current BasisAmount = " + value);
                        }
                    }
                }
            }
        }

        if (!sumLineTotalAmount.equals(sumBasisAmount)) {
            errors.setErrorMassage("Check " + object1 + " or " + object2 + ": LineTotalAmount must equal to Sum(BasisAmount). Current LineTotalAmount = " + sumLineTotalAmount.toString() + ", Sum(BasisAmount) = " + sumBasisAmount.toString());
        }
    }

    // GrandTotalAmount = DifferenceInformationAmount + TaxTotalAmount
    public void checkGrandTotalAmount(Amount[] grandTotalAmount, Amount[] differenceInformationAmount, Amount[] taxTotalAmount, String object1, String object2, String object3) {
        BigDecimal sumGrandTotalAmount = new BigDecimal(0);
        BigDecimal sumDifferenceInformationAmount = new BigDecimal(0);
        BigDecimal sumTaxTotalAmount = new BigDecimal(0);

        for (Amount amount : grandTotalAmount) {
            if (!isNull(new N<>(() -> "" + amount.getValue()))) {
                String value = amount.getValue() == null ? "" : amount.getValue();
                try {
                    BigDecimal amountValue = new BigDecimal(value);
                    sumGrandTotalAmount = sumGrandTotalAmount.add(amountValue);
                } catch (NumberFormatException ex) {
                    errors.setErrorMassage("Check " + object1 + ": Invalid GrandTotalAmount (Decimal). Current GrandTotalAmount = " + value);
                }
            }
        }
    
        for (Amount amount : differenceInformationAmount) {
            if (!isNull(new N<>(() -> "" + amount.getValue()))) {
                String value = amount.getValue() == null ? "" : amount.getValue();
                try {
                    BigDecimal amountValue = new BigDecimal(value);
                    sumDifferenceInformationAmount = sumDifferenceInformationAmount.add(amountValue);
                } catch (NumberFormatException ex) {
                    errors.setErrorMassage("Check " + object2 + ": Invalid DifferenceInformationAmount (Decimal). Current DifferenceInformationAmount = " + value);
                }
            }
        }

        for (Amount amount : taxTotalAmount) {
            if (!isNull(new N<>(() -> "" + amount.getValue()))) {
                String value = amount.getValue() == null ? "" : amount.getValue();
                try {
                    BigDecimal amountValue = new BigDecimal(value);
                    sumTaxTotalAmount = sumTaxTotalAmount.add(amountValue);
                } catch (NumberFormatException ex) {
                    errors.setErrorMassage("Check " + object3 + ": Invalid TaxTotalAmount (Decimal). Current TaxTotalAmount = " + value);
                }
            }
        }

        if (!sumGrandTotalAmount.equals(sumDifferenceInformationAmount.add(sumTaxTotalAmount))) {
            errors.setErrorMassage("Check " + object1 + " or " + object2 + " or " + object3 + ": GrandTotalAmount must equal to DifferenceInformationAmount + TaxTotalAmount. Current GrandTotalAmount = " + sumGrandTotalAmount.toString() + ", DifferenceInformationAmount = " + sumDifferenceInformationAmount.toString() + ", TaxTotalAmount = " + sumTaxTotalAmount.toString());
        }
    }

    // DifferenceInformationAmount = OriginalInformationAmount - LineTotalAmount
    public void checkDifferenceTotalAmount(Amount[] differenceInformationAmount, Amount[] originalInformationAmount, Amount[] lineTotalAmount, String object1, String object2, String object3) {
        BigDecimal sumDifferenceInformationAmount = new BigDecimal(0);
        BigDecimal sumOriginalInformationAmount = new BigDecimal(0);
        BigDecimal sumLineTotalAmount = new BigDecimal(0);

        for (Amount amount : differenceInformationAmount) {
            if (!isNull(new N<>(() -> "" + amount.getValue()))) {
                String value = amount.getValue() == null ? "" : amount.getValue();
                try {
                    BigDecimal amountValue = new BigDecimal(value);
                    sumDifferenceInformationAmount = sumDifferenceInformationAmount.add(amountValue);
                } catch (NumberFormatException ex) {
                    errors.setErrorMassage("Check " + object1 + ": Invalid DifferenceInformationAmount (Decimal). Current DifferenceInformationAmount = " + value);
                }
            }
        }

        for (Amount amount : originalInformationAmount) {
            if (!isNull(new N<>(() -> "" + amount.getValue()))) {
                String value = amount.getValue() == null ? "" : amount.getValue();
                try {
                    BigDecimal amountValue = new BigDecimal(value);
                    sumOriginalInformationAmount = sumOriginalInformationAmount.add(amountValue);
                } catch (NumberFormatException ex) {
                    errors.setErrorMassage("Check " + object2 + ": Invalid OriginalInformationAmount (Decimal). Current OriginalInformationAmount = " + value);
                }
            }
        }
        
        for (Amount amount : lineTotalAmount) {
            if (!isNull(new N<>(() -> "" + amount.getValue()))) {
                String value = amount.getValue() == null ? "" : amount.getValue();
                try {
                    BigDecimal amountValue = new BigDecimal(value);
                    sumLineTotalAmount = sumLineTotalAmount.add(amountValue);
                } catch (NumberFormatException ex) {
                    errors.setErrorMassage("Check " + object3 + ": Invalid LineTotalAmount (Decimal). Current LineTotalAmount = " + value);
                }
            }
        }

        if (!sumDifferenceInformationAmount.equals(sumOriginalInformationAmount.subtract(sumLineTotalAmount))) {
            errors.setErrorMassage("Check " + object1 + " or " + object2 + " or " + object3 + ": DifferenceInformationAmount must equal to OriginalInformationAmount - LineTotalAmount. Current DifferenceInformationAmount = " + sumDifferenceInformationAmount.toString() + ", OriginalInformationAmount = " + sumOriginalInformationAmount.toString() + ", LineTotalAmount = " + sumLineTotalAmount.toString());
        }
    }
}
