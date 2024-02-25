package rd.checker.rule;

import java.util.List;
import java.util.Map;
import java.util.Set;

import model.ErrorMessage;
import model.pojo.trade_transaction.application_header.document.AdditionalReferencedDocument;
import model.pojo.trade_transaction.application_header.document.BuyerOrderReferencedDocument;
import rd.checker.Checker;

public class Rule_13 extends Checker{
    public void checkBuyerOrderReferencedDocument(BuyerOrderReferencedDocument document, List<String> referencedTypeCodes, String object, ErrorMessage errors){
        if (!isNull(new N<>(() -> "" + document.getIssueDateTime()))) {
            (new Rule_5()).checkDateTimeFormat(document.getIssueDateTime(), object, errors);
        }

        if (!isNull(new N<>(() -> "" + document.getReferenceTypeCode()))) {
            if (!referencedTypeCodes.contains(document.getReferenceTypeCode()))
                errors.setErrorMassage("Check " + object + "|ReferencedTypeCodes: ReferencedTypeCodes is not in the list. Current ReferencedTypeCodes = " + document.getReferenceTypeCode());
        }
    }

    public void checkAdditionalReferencedDocument(AdditionalReferencedDocument[] documents, String transactionType, Map<String, String> invoiceTypeCodeToName, 
        Map<String, String> taxInvoiceTypeCodeToName, String object, ErrorMessage errors){
        if (documents.length > 0) {
            if (transactionType.equals("CLN")) {
                if (documents.length > 1) {
                    errors.setErrorMassage("Check " + object + "|Document: Transaction = CancellationNote then Total Document Number can be 1 only. Current Total Document Number = " + documents.length);
                }
            } else {
                Set<String> invDocumentCodes = invoiceTypeCodeToName.keySet();
                Set<String> tivDocumentCodes = taxInvoiceTypeCodeToName.keySet();

                for (AdditionalReferencedDocument document : documents)
                    checkAdditionalReferencedDocument(document, invDocumentCodes, tivDocumentCodes, object, errors);
            }
        }
    }

    private void checkAdditionalReferencedDocument(AdditionalReferencedDocument document, Set<String> invDocumentCodes, Set<String> tivDocumentCodes, 
        String object, ErrorMessage errors) {
        if (!isNull(new N<>(() -> "" + document.getIssueDateTime()))) {
            (new Rule_5()).checkDateTimeFormat(document.getIssueDateTime(), object, errors);
        }

        if (!isNull(new N<>(() -> "" + document.getReferenceTypeCode()))) {
            String referenceTypeCode = document.getReferenceTypeCode();
            if (!(invDocumentCodes.contains(referenceTypeCode) || tivDocumentCodes.contains(referenceTypeCode))) {
                errors.setErrorMassage("Check " + object + "|ReferenceTypeCode: ReferenceTypeCode is not in the list. Current ReferenceTypeCode = " + document.getReferenceTypeCode());
            }
        }
    }

}
