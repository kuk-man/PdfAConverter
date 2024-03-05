package rd.checker.rule;

import java.util.regex.Pattern;

import model.ErrorMessage;
import model.pojo.trade_transaction.trade_line_item.SpecifiedTradeProduct;
import rd.checker.Checker;

public class Rule_20 extends Checker {
    public void checkSpecifiedTradeProduct(SpecifiedTradeProduct stp, String object) {
        if (!isNull(new N<>(() -> "" + stp.getId()))) {
            String id = stp.getId();
            checkSpecifiedTradeProductID(id, object);
        }

        if (!isNull(new N<>(() -> "" + stp.getGlobalID()))) {
            String id = stp.getGlobalID().getValue() == null ? "" : stp.getGlobalID().getValue();
            String schemeId = stp.getGlobalID().getSchemeID() == null ? "" : stp.getGlobalID().getSchemeID();
            String schemeAgencyId = stp.getGlobalID().getSchemeAgencyID() == null ? "" : stp.getGlobalID().getSchemeAgencyID();
            checkSpecifiedTradeProductGlobalID(id, schemeId, schemeAgencyId, object);
        }
    }

    private void checkSpecifiedTradeProductID(String id, String object){
        String regex = "^[A-Z0-9]{1,35}$";
        Pattern pattern = Pattern.compile(regex);
        if (!pattern.matcher(id).matches()) {
            errors.setErrorMassage("Check " + object + "|ID: Invalid ID (<=35 characters of A-Z/0-9). Current ID = " + id);
        }
    }

    private void checkSpecifiedTradeProductGlobalID(String id, String schemeId, String schemeAgencyId, String object){
        String regex;
        Pattern pattern;

        regex = "^[0-9]{14}$";
        pattern = Pattern.compile(regex);
        if (!pattern.matcher(id).matches()) {
            errors.setErrorMassage("Check " + object + "|GlobalID: Invalid GlobalID (14 characters of 0-9). Current GlobalID = " + id);
        }

        if (!(schemeId.equals("GLN") && schemeAgencyId.equals("GS1"))) {
            errors.setErrorMassage("Check " + object + "|SchemeId/SchemeAgencyId: Invalid SchemeId/SchemeAgencyId (SchememId=GLN, SchemeAgencyId=GS1). Current SchemeId = " + schemeId + ", SchemeAgencyId = " + schemeAgencyId);
        }
    }
}
