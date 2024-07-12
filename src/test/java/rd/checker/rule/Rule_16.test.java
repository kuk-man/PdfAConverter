package rd.checker.rule;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import connector.DatabaseConnecter;

import model.pojo.common.Amount;
import model.pojo.common.Charge;
import model.pojo.trade_transaction.application_header.SpecifiedTradeSettlementHeaderMonetarySummation;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class Rule_16Test {
    String object1, object2;
    String typeCode;
    String chargeIndicator;
    String allowanceChargeAmount;
    String totalAmount;
    String compareValue;

    DatabaseConnecter dbCon = new DatabaseConnecter("connection");
    List<String> allowanceChargeTypeCodes = dbCon.getAllowanceChargeTypeCodes();

    @BeforeEach
    private void setBeforEach() {
        object1 = "TestA";
        object2 = "TestB";
    }

    @AfterEach
    private void setAfterEach() {
        object1 = "";
        object2 = "";
    }
    
    @Test
    void testCheckAllowanceCharge() {
        // Arrange & Act & Assert
        // TypeCode
        typeCode = "XYZ";
        chargeIndicator = "true";
        allowanceChargeAmount = "100.50";
        totalAmount = "100.50";
        compareValue = "Check " + object1 + "|TypeCode: TypeCode is not in the list. Current TypeCode = " + typeCode + "\n";
        checkData(typeCode, chargeIndicator, allowanceChargeAmount, totalAmount, compareValue);

        // ChargeIndicator
        typeCode = "28";
        chargeIndicator = "XYZ";
        allowanceChargeAmount = "100.50";
        totalAmount = "100.50";
        compareValue = "Check " + object1 + "|ChargeIndicator: ChargeIndicator can be true/false only. Current ChargeIndicator = " + chargeIndicator + "\n";
        checkData(typeCode, chargeIndicator, allowanceChargeAmount, totalAmount, compareValue);

        // ActualAmount
        typeCode = "28";
        chargeIndicator = "true";
        allowanceChargeAmount = "XYZ";
        totalAmount = "100.50";
        compareValue = "Check " + object1 + "|ActualAmount: Invalid ActualAmount (Decimal). Current ActualAmount = " + allowanceChargeAmount + "\n";
        checkData(typeCode, chargeIndicator, allowanceChargeAmount, totalAmount, compareValue);

        // Charge
        typeCode = "28";
        chargeIndicator = "true";
        allowanceChargeAmount = "100.50";
        totalAmount = "100.50";
        compareValue = "";
        checkData(typeCode, chargeIndicator, allowanceChargeAmount, totalAmount, compareValue);

        typeCode = "28";
        chargeIndicator = "true";
        allowanceChargeAmount = "100.50";
        totalAmount = "XYZ";
        compareValue = "Check " + object2 + "|ChargeTotalAmount: Invalid ChargeTotalAmount (Decimal). Current ChargeTotalAmount = " + totalAmount + "\n";
        checkData(typeCode, chargeIndicator, allowanceChargeAmount, totalAmount, compareValue);

        typeCode = "28";
        chargeIndicator = "true";
        allowanceChargeAmount = "100.50";
        totalAmount = "200";
        compareValue = "Check " + object1 + "|ActualAmount or " + object2 + "|ChargeTotalAmount: ChargeIndicator=true (Charge) then Sum ActualAmount must equal to TotalAmount. Current Sum ActualAmount = " + allowanceChargeAmount + ", TotalAmount = " + totalAmount + "\n";
        checkData(typeCode, chargeIndicator, allowanceChargeAmount, totalAmount, compareValue);

        // Allowance
        typeCode = "28";
        chargeIndicator = "false";
        allowanceChargeAmount = "100.50";
        totalAmount = "100.50";
        compareValue = "";
        checkData(typeCode, chargeIndicator, allowanceChargeAmount, totalAmount, compareValue);

        typeCode = "28";
        chargeIndicator = "false";
        allowanceChargeAmount = "100.50";
        totalAmount = "XYZ";
        compareValue = "Check " + object2 + "|AllowanceTotalAmount: Invalid AllowanceTotalAmount (Decimal). Current AllowanceTotalAmount = " + totalAmount + "\n";
        checkData(typeCode, chargeIndicator, allowanceChargeAmount, totalAmount, compareValue);

        typeCode = "28";
        chargeIndicator = "false";
        allowanceChargeAmount = "100.50";
        totalAmount = "200";
        compareValue = "Check " + object1 + "|ActualAmount or " + object2 + "|AllowanceTotalAmount: ChargeIndicator=false (Allowance) then Sum ActualAmount must equal to TotalAmount (..|SpecifiedTradeSettlementHeaderMonetarySummation|AllowanceTotalAmount). Current Sum ActualAmount = " + allowanceChargeAmount + ", TotalAmount = " + totalAmount + "\n";
        checkData(typeCode, chargeIndicator, allowanceChargeAmount, totalAmount, compareValue);
    }

    private void checkData(String typeCode, String chargeIndicator, String allowanceChargeAmount, String totalAmount, String compareValue) {
        // Allowance/Charge
        Amount amount = new Amount();
        amount.setValue(allowanceChargeAmount);
        List<Amount> allowanceChargeAmounts = new ArrayList<Amount>();
        allowanceChargeAmounts.add(amount);

        Charge allowanceCharge = new Charge();
        allowanceCharge.setTypeCode(typeCode);
        allowanceCharge.setChargeIndicator(chargeIndicator);
        allowanceCharge.setActualAmount(allowanceChargeAmounts.toArray(new Amount[allowanceChargeAmounts.size()]));

        List<Charge> allowanceCharges = new ArrayList<Charge>();
        allowanceCharges.add(allowanceCharge);

        // MonetarySummation
        amount = new Amount();
        amount.setValue(totalAmount);
        List<Amount> totalAmounts = new ArrayList<Amount>();
        totalAmounts.add(amount);

        SpecifiedTradeSettlementHeaderMonetarySummation monetarySummation = new SpecifiedTradeSettlementHeaderMonetarySummation();
        monetarySummation.setChargeTotalAmount(totalAmounts.toArray(new Amount[totalAmounts.size()]));
        monetarySummation.setAllowanceTotalAmount(totalAmounts.toArray(new Amount[totalAmounts.size()]));
        
        Rule_16 rule = new Rule_16();
        rule.checkAllowanceCharge(allowanceCharges.toArray(new Charge[allowanceCharges.size()]), monetarySummation, allowanceChargeTypeCodes, object1, object2);
        assertEquals(compareValue, rule.getError().getErrorMessage());
    }
}