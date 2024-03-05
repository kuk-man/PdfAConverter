package rd.checker.rule;

import java.util.Map;

import model.ErrorMessage;
import rd.checker.Checker;

public class Rule_4 extends Checker {
// rule 8.4
    public void checkPurpose(String purposeCode, String purposeDescription, Map<String, String> purposeCodeToDescription, String object) {
        if (purposeCodeToDescription.containsKey(purposeCode)){
            String purposeDesc = purposeCodeToDescription.get(purposeCode);
            if (!purposeDesc.equals(purposeDescription)) {
                errors.setErrorMassage("Check " + object + "|Purpose: Purpose is not in the list. Current Purpose = " + purposeDescription);    
            }
        } else {
            errors.setErrorMassage("Check " + object + "|PurposeCode: PurposeCode is not in the list. Current PurposeCode = " + purposeCode);
        }
    }

    public void checkPurpose(String actionTransactionType, String causeTransactionType, String goodsService, String purposeCode, String purposeDescription,
        Map<String, String> purposeCodeToDescriptionForDebitNoteOnGoods, Map<String, String> purposeCodeToDescriptionForDebitNoteOnService,
        Map<String, String> purposeCodeToDescriptionForCreditNoteOnGoods, Map<String, String> purposeCodeToDescriptionForCreditNoteOnService, 
        Map<String, String> purposeCodeToDescriptionForCancellationCauseOnNewTaxInvoice, Map<String, String> purposeCodeToDescriptionForCancellationCauseOnNewReceipt,
        String object) {

        if (actionTransactionType.equals("DBN") && causeTransactionType.equals("DCN")) {
            switch(goodsService) {
                case "Goods":
                    checkPurpose(purposeCode, purposeDescription, purposeCodeToDescriptionForDebitNoteOnGoods, object);
                    break;    
                case "Service":
                    checkPurpose(purposeCode, purposeDescription, purposeCodeToDescriptionForDebitNoteOnService, object);
                    break;
                default:
                    errors.setErrorMassage("Check " + object + "|Purpose: Invalid Goods And Service (Goods/Service). Current Action Transaction Type = " + 
                                   actionTransactionType + ", Cause Transaction Type = " + causeTransactionType + ", Goods And Service =" + goodsService);
            }
        } else if (actionTransactionType.equals("CDN") && causeTransactionType.equals("DCN")) {
            switch(goodsService) {
                case "Goods":
                    checkPurpose(purposeCode, purposeDescription, purposeCodeToDescriptionForCreditNoteOnGoods, object);
                    break;
                case "Service":
                    checkPurpose(purposeCode, purposeDescription, purposeCodeToDescriptionForCreditNoteOnService, object);
                    break;
                default:
                    errors.setErrorMassage("Check " + object + ": Invalid Goods And Service (Goods/Service). Current Action Transaction Type = " + 
                                   actionTransactionType + ", Cause Transaction Type = " + causeTransactionType + ", Goods and Service =" + goodsService);
            }
        } else if (actionTransactionType.equals("TIV") && causeTransactionType.equals("CLN")) {
            checkPurpose(purposeCode, purposeDescription, purposeCodeToDescriptionForCancellationCauseOnNewTaxInvoice, object);
        } else if (actionTransactionType.equals("RCT") && causeTransactionType.equals("CLN")) {
            checkPurpose(purposeCode, purposeDescription, purposeCodeToDescriptionForCancellationCauseOnNewReceipt, object);
        }
    }
}
