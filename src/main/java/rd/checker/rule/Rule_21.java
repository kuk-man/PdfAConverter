package rd.checker.rule;

import java.util.List;

import model.ErrorMessage;
import model.pojo.common.Name;
import model.pojo.trade_transaction.trade_line_item.DesignatedProductClassification;
import rd.checker.Checker;

public class Rule_21 extends Checker {
    public void checkDesignatedProductClassification(DesignatedProductClassification dpc, List<String> unspscCodes, List<String> languageCodes, String object, ErrorMessage errors) {
        // ClassCode
        if (!isNull(new N<>(() -> "" + dpc.getClassCode()))) {
            String classCode = dpc.getClassCode().getValue() == null ? "" : dpc.getClassCode().getValue();
            String listName = dpc.getClassCode().getListName() == null ? "" : dpc.getClassCode().getListName();
            String listVersionID = dpc.getClassCode().getListVersionID() == null ? "" : dpc.getClassCode().getListVersionID();
            checkClassCode(classCode, listName, listVersionID, unspscCodes, object, errors);
        }

        // ClassName
        if (!isNull(new N<>(() -> "" + dpc.getClassName()))) {
            for (Name className : dpc.getClassName()) {
                String languageID = className.getLanguageID() == null ? "" : className.getLanguageID();
                checkLanguageID(languageID, languageCodes, object, errors);
            }
        }
    }

    private void checkClassCode(String classCode, String listName, String listVersionID, List<String> unspscCodes, String object, ErrorMessage errors) {
        if (!listName.equals("UNSPSC")) {
            errors.setErrorMassage("Check " + object + "|ListName: Invalid ListName (UNSPSC1). Current ListName = " + listName);
        }

        if (!listVersionID.isBlank()) {
            errors.setErrorMassage("Check " + object + "|ListVersionID: ListVersionID cannot be blank. Current ListVersionID = " + listVersionID);
        }

        if (classCode.length() != 8) {
            errors.setErrorMassage("Check " + object + "|ClassCode: Invalid ClassCode (8 numerics). Current ClassCode = " + classCode);
        } else {
            if (!unspscCodes.contains(classCode)) {
                errors.setErrorMassage("Check " + object + "|ClassCode: ClassCode is not in the list. Current ClassCode = " + classCode);
            }
        }
    }

    private void checkLanguageID(String languageID, List<String> languageCodes, String object, ErrorMessage errors) {
        if (!languageCodes.contains(languageID)) {
            errors.setErrorMassage("Check " + object + "|LanguageID: LanguageID is not in the list. Current LanguageID = " + languageID);
        }
    }
}
