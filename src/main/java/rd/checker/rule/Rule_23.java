package rd.checker.rule;

import rd.checker.Checker;

public class Rule_23 extends Checker {
    public void checkInvoicerTradeParty(String transactionType, String object) {
        if (transactionType.equals("TIV") || transactionType.equals("DCN") || transactionType.equals("RCT") || transactionType.equals("CLN")) {
            errors.setErrorMassage("Check " + object + ": Missing InvoicerTradeParty when TransactionType = TIV/DCN/RCT/CLN. Current TransactionType = " + transactionType);
        }
    }
}
