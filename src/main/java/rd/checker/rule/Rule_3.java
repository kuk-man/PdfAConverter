package rd.checker.rule;

import java.util.Arrays;
import java.util.Map;

import model.pojo.exchanged_document.ExchangedDocument;

import rd.checker.Checker;

public class Rule_3 extends Checker {
    public void checkExchangedDocumentTypeCodeAndTypeName(String transactionType, ExchangedDocument ed, 
        Map<String, String> invoiceTypeCodeToName, Map<String, String> taxInvoiceTypeCodeToName, 
        String object){

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
                checkTypeCodeAndName(invoiceTypeCodeToName, typeCode, typeNames, object);
                break;
            case "TIV": case "RCT": case "DCN": case "ABB": case "CLN":
                checkTypeCodeAndName(taxInvoiceTypeCodeToName, typeCode, typeNames, object);
                break;
            default:
                errors.setErrorMassage("Check " + object + ": Invalid TransactionType (INV/RCT/TIV/DCN/ABB/CLN). Current TransactionType = " + transactionType);
        }
    }

    private void checkTypeCodeAndName(Map<String, String> typeCodeToName, String typeCode, String[] typeNames, String object) {
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
                errors.setErrorMassage("Check " + object + "|Name: Name is not in the list. Current TypeCode = " + typeCode + ", Name = " + Arrays.toString(typeNames) + ". System Name = " + name);
        } else {
            errors.setErrorMassage("Check " + object + "|TypeCode: TypeCode is not in the list. Current TypeCode = " + typeCode);
        }
    }
}
