package rd.checker;

import model.ErrorMessage;
import model.pojo.RootXml;
import rd.checker.mandatory_field.AbbreviatedTaxInvoiceMandatoryField;
import rd.checker.mandatory_field.CancellationNoteMandatoryField;
import rd.checker.mandatory_field.DebitCreditNoteMandatoryField;
import rd.checker.mandatory_field.InvoiceMandatoryField;
import rd.checker.mandatory_field.MandatoryFieldChecker;
import rd.checker.mandatory_field.ReceiptMandatoryField;
import rd.checker.mandatory_field.TaxInvoiceMandatoryField;

public class TransactionMandatoryFieldChecker {
    public String verifyMandatoryField(RootXml rootXml) {
        String errorMessage = checkMandatoryField(rootXml);
        if (!errorMessage.isBlank())
            return "Check Mandatory Fields:\n" + errorMessage;
        return "";
    }

    private String checkMandatoryField(RootXml rootXml) {
        MandatoryFieldChecker mandatory;
        switch (rootXml.getTransaction()) {
            case "INV":
                mandatory = new InvoiceMandatoryField();
                break;
            case "RCT":
                mandatory = new ReceiptMandatoryField();
                break;
            case "TIV":
                mandatory = new TaxInvoiceMandatoryField();
                break;
            case "DCN":
                mandatory = new DebitCreditNoteMandatoryField();
                break;
            case "CLN":
                mandatory = new CancellationNoteMandatoryField();
                break;
            case "ABB":
                mandatory = new AbbreviatedTaxInvoiceMandatoryField();
                break;
            default:
                return "Invalid TransactionType (INV/RCT/TIV/DCN/ABB/CLN): Current TransactionType: " + rootXml.getTransaction();
        }

        mandatory.checkRequiredField(rootXml);
        //mandatory.checkNotUsedField(rootXml);
        ErrorMessage errors = mandatory.getError();
        if (!errors.getErrorMessage().isBlank())
            return errors.getErrorMessage();
        return "";
    }
}
