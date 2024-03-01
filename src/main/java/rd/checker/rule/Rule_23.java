package rd.checker.rule;

import model.ErrorMessage;

public class Rule_23 {
    public void checkInvoicerTradeParty(String transactionType, String object, ErrorMessage errors) {
        if (transactionType.equals("TIV") || transactionType.equals("DCN") || transactionType.equals("RCT") || transactionType.equals("CLN")) {
            errors.setErrorMassage("Check " + object + ": Missing InvoicerTradeParty");
        }
    }
}
