package rd.checker.rule;

import java.util.Map;

import model.ErrorMessage;

public class Rule_4 {
// rule 8.4
    public static void checkPurpose(String actionTransactionType, String causeTransactionType, String goodsService, String purposeCode, String purposeDescription,
        Map<String, String> purposeCodeToDescriptionForDebitNoteOnGoods, Map<String, String> purposeCodeToDescriptionForDebitNoteOnService,
        Map<String, String> purposeCodeToDescriptionForCreditNoteOnGoods, Map<String, String> purposeCodeToDescriptionForCreditNoteOnService, 
        Map<String, String> purposeCodeToDescriptionForCancellationCauseOnNewTaxInvoice, Map<String, String> purposeCodeToDescriptionForCancellationCauseOnNewReceipt,
        String object, ErrorMessage errors) {
        if (actionTransactionType.equals("DBN") && causeTransactionType.equals("DCN")) {
            switch(goodsService) {
                case "Goods":
                    checkPurpose(purposeCodeToDescriptionForDebitNoteOnGoods, purposeCode, purposeDescription, object, errors);
                    break;    
                case "Service":
                    checkPurpose(purposeCodeToDescriptionForDebitNoteOnService, purposeCode, purposeDescription, object, errors);
                    break;
                default:
                    errors.setErrorMassage("Check " + object + "|Purpose: Invalid Goods And Service (Goods/Service). Current Action Transaction Type = " + 
                                   actionTransactionType + ", Cause Transaction Type = " + causeTransactionType + ", Goods And Service =" + goodsService);
            }
        } else if (actionTransactionType.equals("CDN") && causeTransactionType.equals("DCN")) {
            switch(goodsService) {
                case "Goods":
                    checkPurpose(purposeCodeToDescriptionForCreditNoteOnGoods, purposeCode, purposeDescription, object, errors);
                    break;
                case "Service":
                    checkPurpose(purposeCodeToDescriptionForCreditNoteOnService, purposeCode, purposeDescription, object, errors);
                    break;
                default:
                    errors.setErrorMassage("Check " + object + ": Invalid Goods And Service (Goods/Service). Current Action Transaction Type = " + 
                                   actionTransactionType + ", Cause Transaction Type = " + causeTransactionType + ", Goods and Service =" + goodsService);
            }
        } else if (actionTransactionType.equals("TIV") && causeTransactionType.equals("CLN")) {
            checkPurpose(purposeCodeToDescriptionForCancellationCauseOnNewTaxInvoice, purposeCode, purposeDescription, object, errors);
        } else if (actionTransactionType.equals("RCT") && causeTransactionType.equals("CLN")) {
            checkPurpose(purposeCodeToDescriptionForCancellationCauseOnNewReceipt, purposeCode, purposeDescription, object, errors);
        }
    }

    private static void checkPurpose(Map<String, String> purposeCodeToDescription, String purposeCode, String purposeDescription,
        String object, ErrorMessage errors) {
        if (purposeCodeToDescription.containsKey(purposeCode)){
            String purposeDesc = purposeCodeToDescription.get(purposeCode);
            if (purposeDesc.equals(purposeDescription)) {
                errors.setErrorMassage("Check " + object + "|Purpose: Purpose is not in the list. Current Purpose = " + purposeDescription);    
            }
        } else {
            errors.setErrorMassage("Check " + object + "|PurposeCode: PurposeCode is not in the list. Current PurposeCode = " + purposeCode);
        }
    }
}
