package rd.checker.rule;

import java.util.List;
import java.util.Map;

import model.ErrorMessage;
import model.pojo.exchanged_document.ExchangedDocument;

import rd.checker.Checker;

public class Rule_3 extends Checker {
    public void checkExchangedDocumentTypeCodeAndTypeName(String transactionType, ExchangedDocument ed, 
        Map<String, String> invoiceTypeCodeToName, Map<String, String> taxInvoiceTypeCodeToName, 
        String object, ErrorMessage errors){

        String[] typeNames = {};
        if (!isNull(new N<>(() -> "" + ed.getName()))) {
            typeNames = ed.getName();
        }

        String typeCode = "";
        if (!isNull(new N<>(() -> "" + ed.getTypeCode()))) {
            typeCode = ed.getTypeCode();
        }

        switch(transactionType) {
            case "INV":
                checkTypeCodeAndName(invoiceTypeCodeToName, typeCode, typeNames, object, errors);
                break;
            case "TIV":case "RCT": case "DCN": case "ABB":
                checkTypeCodeAndName(taxInvoiceTypeCodeToName, typeCode, typeNames, object, errors);
                break;
            default:
                errors.setErrorMassage("Check " + object + ": Invalid TransactionType (INV/RCT/TIV/DCN/ABB/CLN). Current TransactionType = " + transactionType);
        }
    }

    private void checkTypeCodeAndName(Map<String, String> typeCodeToName, String typeCode, String[] typeNames, 
        String object, ErrorMessage errors) {
        if (typeCodeToName.containsKey(typeCode)) {
            boolean hasValue = false;
            String name = typeCodeToName.get(typeCode);

            for (String typeName : typeNames) {
                if (typeName.equals(name)) {
                    hasValue = true;
                    break;
                }
            }
            if (!hasValue)
                errors.setErrorMassage("Check " + object + "|Name: Name is not in the list. Current TypeCode = " + typeCode + ". System Name = " + name);
        } else {
            errors.setErrorMassage("Check " + object + "|TypeCode: Invalid TypeCode (INV/RCT/TIV/DCN/ABB/CLN). Current TypeCode = " + typeCode);
        }
    }
}
