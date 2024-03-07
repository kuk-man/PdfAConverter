package rd.checker.calculation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.pojo.common.Amount;
import model.pojo.trade_transaction.application_header.ApplicableTradeTax;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class CalculationTest {
    String object1;
    String object2;
    String object3;

    @BeforeEach
    private void setBeforEach() {
        object1 = "TestA";
        object2 = "TestB";
        object3 = "TestC";
    }

    @AfterEach
    private void setAfterEach() {
        object1 = "";
        object2 = "";
        object3 = "";
    }

    @Test
    void testCheckLineTotalAmount() {
        // Arrange
        String expected;
        // Act & Assert
        expected = "";
        assertLineTotalAmountData("10.50", "10.50", expected);

        expected = "Check " + object1 + ": Invalid LineTotalAmount (Decimal). Current LineTotalAmount = xyz\n" + 
                   "Check TestA or TestB: LineTotalAmount must equal to Sum(BasisAmount). Current LineTotalAmount = 0, Sum(BasisAmount) = 10.50\n";
        assertLineTotalAmountData("xyz", "10.50", expected);
        
        expected = "Check " + object2 + ": Invalid BasisAmount (Decimal). Current BasisAmount = xyz\n" + 
                   "Check TestA or TestB: LineTotalAmount must equal to Sum(BasisAmount). Current LineTotalAmount = 10.50, Sum(BasisAmount) = 0\n";
        assertLineTotalAmountData("10.50", "xyz", expected);

        expected = "Check " + object1 + " or " + object2 + ": LineTotalAmount must equal to Sum(BasisAmount). Current LineTotalAmount = 10.50, Sum(BasisAmount) = 9.50\n";
        assertLineTotalAmountData("10.50", "9.50", expected);
    }

    void assertLineTotalAmountData(String value1, String value2, String expected) {
        Amount amount1 = new Amount();
        amount1.setValue(value1);
        List<Amount> lineTotalAmount = new ArrayList<Amount>();
        lineTotalAmount.add(amount1);

        Amount amount2 = new Amount();
        amount2.setValue(value2);        
        List<Amount> basisAmount = new ArrayList<Amount>();
        basisAmount.add(amount2);

        ApplicableTradeTax applicableTradeTax = new ApplicableTradeTax();
        applicableTradeTax.setBasisAmount(basisAmount.toArray(new Amount[basisAmount.size()]));
        List<ApplicableTradeTax> basisAmounts = new ArrayList<ApplicableTradeTax>();
        basisAmounts.add(applicableTradeTax);

        Calculation calculation = new Calculation();
        calculation.checkLineTotalAmount(lineTotalAmount.toArray(new Amount[lineTotalAmount.size()]), basisAmounts.toArray(new ApplicableTradeTax[basisAmounts.size()]), object1, object2);
        assertEquals(expected, calculation.getError().getErrorMessage());
    }

    @Test
    void testCheckGrandTotalAmount() {
        // Arrange
        String expected;

        // Act & Assert
        expected = "";
        assertGrandTotalAmountData("25.50","10.00","15.50",expected);

        expected = "Check " + object1 + ": Invalid GrandTotalAmount (Decimal). Current GrandTotalAmount = xyz\n" + 
                   "Check TestA or TestB or TestC: GrandTotalAmount must equal to DifferenceInformationAmount + TaxTotalAmount. Current GrandTotalAmount = 0, DifferenceInformationAmount = 10.00, TaxTotalAmount = 15.50\n";
        assertGrandTotalAmountData("xyz","10.00","15.50", expected);

        expected = "Check " + object2 + ": Invalid DifferenceInformationAmount (Decimal). Current DifferenceInformationAmount = xyz\n" +
                   "Check TestA or TestB or TestC: GrandTotalAmount must equal to DifferenceInformationAmount + TaxTotalAmount. Current GrandTotalAmount = 25.50, DifferenceInformationAmount = 0, TaxTotalAmount = 15.50\n";
        assertGrandTotalAmountData("25.50","xyz","15.50", expected);

        expected = "Check " + object3 + ": Invalid TaxTotalAmount (Decimal). Current TaxTotalAmount = xyz\n" + 
                   "Check TestA or TestB or TestC: GrandTotalAmount must equal to DifferenceInformationAmount + TaxTotalAmount. Current GrandTotalAmount = 25.50, DifferenceInformationAmount = 10.00, TaxTotalAmount = 0\n";
        assertGrandTotalAmountData("25.50","10.00","xyz", expected);

        expected = "Check " + object1 + " or " + object2 + " or " + object3 + ": GrandTotalAmount must equal to DifferenceInformationAmount + TaxTotalAmount. Current GrandTotalAmount = 25.50, DifferenceInformationAmount = 10.00, TaxTotalAmount = 15.00\n";
        assertGrandTotalAmountData("25.50","10.00","15.00",expected);
    }

    void assertGrandTotalAmountData(String value1, String value2, String value3, String expected) {
        Amount amount1 = new Amount();
        amount1.setValue(value1);
        List<Amount> grandTotalAmount = new ArrayList<Amount>();
        grandTotalAmount.add(amount1);

        Amount amount2 = new Amount();
        amount2.setValue(value2);        
        List<Amount> differenceInformationAmount = new ArrayList<Amount>();
        differenceInformationAmount.add(amount2);

        Amount amount3 = new Amount();
        amount3.setValue(value3);        
        List<Amount> taxTotalAmount = new ArrayList<Amount>();
        taxTotalAmount.add(amount3);

        Calculation calculation = new Calculation();
        calculation.checkGrandTotalAmount(grandTotalAmount.toArray(new Amount[grandTotalAmount.size()]), 
                                            differenceInformationAmount.toArray(new Amount[differenceInformationAmount.size()]),
                                            taxTotalAmount.toArray(new Amount[taxTotalAmount.size()]),
                                            object1, object2, object3);
        assertEquals(expected, calculation.getError().getErrorMessage());
    }

    @Test
    void testDifferenceTotalAmount() {
        // Arrange
        String expected;

        // Act & Assert
        expected = "";
        assertCheckDifferenceTotalAmountData("10.50","20.00","9.50", expected);

        expected = "Check " + object1 + ": Invalid DifferenceInformationAmount (Decimal). Current DifferenceInformationAmount = xyz\n" + 
                   "Check TestA or TestB or TestC: DifferenceInformationAmount must equal to OriginalInformationAmount - LineTotalAmount. Current DifferenceInformationAmount = 0, OriginalInformationAmount = 20.00, LineTotalAmount = 9.50\n";
        assertCheckDifferenceTotalAmountData("xyz","20.00","9.50", expected);
        
        expected = "Check " + object2 + ": Invalid OriginalInformationAmount (Decimal). Current OriginalInformationAmount = xyz\n" + 
                   "Check TestA or TestB or TestC: DifferenceInformationAmount must equal to OriginalInformationAmount - LineTotalAmount. Current DifferenceInformationAmount = 10.50, OriginalInformationAmount = 0, LineTotalAmount = 9.50\n";
        assertCheckDifferenceTotalAmountData("10.50","xyz","9.50", expected);
        
        expected = "Check " + object3 + ": Invalid LineTotalAmount (Decimal). Current LineTotalAmount = xyz\n" + 
                   "Check TestA or TestB or TestC: DifferenceInformationAmount must equal to OriginalInformationAmount - LineTotalAmount. Current DifferenceInformationAmount = 10.50, OriginalInformationAmount = 20.00, LineTotalAmount = 0\n";
        assertCheckDifferenceTotalAmountData("10.50","20.00","xyz", expected);
        
        expected = "Check " + object1 + " or " + object2 + " or " + object3 + ": DifferenceInformationAmount must equal to OriginalInformationAmount - LineTotalAmount. Current DifferenceInformationAmount = 10.50, OriginalInformationAmount = 20.00, LineTotalAmount = 9.00\n";
        assertCheckDifferenceTotalAmountData("10.50","20.00","9.00", expected);
    }

    void assertCheckDifferenceTotalAmountData(String value1, String value2, String value3, String expected) {
        Amount amount1 = new Amount();
        amount1.setValue(value1);
        List<Amount> differenceInformationAmount = new ArrayList<Amount>();
        differenceInformationAmount.add(amount1);

        Amount amount2 = new Amount();
        amount2.setValue(value2);        
        List<Amount> originalInformationAmount = new ArrayList<Amount>();
        originalInformationAmount.add(amount2);

        Amount amount3 = new Amount();
        amount3.setValue(value3);        
        List<Amount> lineTotalAmount = new ArrayList<Amount>();
        lineTotalAmount.add(amount3);

        Calculation calculation = new Calculation();
        calculation.checkDifferenceTotalAmount(differenceInformationAmount.toArray(new Amount[differenceInformationAmount.size()]), 
                                                originalInformationAmount.toArray(new Amount[originalInformationAmount.size()]),
                                                lineTotalAmount.toArray(new Amount[lineTotalAmount.size()]),
                                                object1, object2, object3);
        assertEquals(expected, calculation.getError().getErrorMessage());
    }
}
