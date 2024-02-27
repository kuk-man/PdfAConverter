package rd.checker.rule;

import java.util.regex.Pattern;

import model.ErrorMessage;
import model.pojo.trade_transaction.trade_line_item.SpecifiedTradeProduct;
import rd.checker.Checker;

public class Rule_20 extends Checker {
    public void checkSpecifiedTradeProduct(SpecifiedTradeProduct stp, String object, ErrorMessage errors) {
        if (stp.getId() != null) {
            String id = stp.getId();
            checkSpecifiedTradeProductID(id, object, errors);
        }

        if (!isNull(new N<>(() -> "" + stp.getGlobalID()))) {
            String id = stp.getGlobalID().getValue() == null ? "" : stp.getGlobalID().getValue();
            String schemeId = stp.getGlobalID().getSchemeID() == null ? "" : stp.getGlobalID().getSchemeID();
            String schemeAgencyId = stp.getGlobalID().getSchemeAgencyID() == null ? "" : stp.getGlobalID().getSchemeAgencyID();
            checkSpecifiedTradeProductGlobalID(id, schemeId, schemeAgencyId, object, errors);
        }

        // String name, String description ???
        // TIV/DCN/ABB	
        // รายการข้อมูลชื่อสินค้า (3.4.2.3) รายละเอียดสินค้า (3.4.2.4) ต้องประกอบด้วยรายละเอียดสินค้า ได้แก่ ชื่อ ประเภท และ ชนิด ???
        // RCT	
        // รายการข้อมูลชื่อสินค้า (3.4.2.3) รายละเอียดสินค้า (3.4.2.4) ต้องประกอบด้วยรายละเอียดสินค้า ได้แก่ ชื่อ และ ชนิด ???
    }

    private void checkSpecifiedTradeProductID(String id, String object, ErrorMessage errors){
        String regex = "^[A-Z0-9]^[A-Z0-9]{1,35}$";
        Pattern pattern = Pattern.compile(regex);
        if (!pattern.matcher(id).matches()) {
            errors.setErrorMassage("Check " + object + "|ID: Invalid ID (<=35 characters of A-Z/0-9). Current ID = " + id);
        }
    }

    private void checkSpecifiedTradeProductGlobalID(String id, String schemeId, String schemeAgencyId, String object, ErrorMessage errors){
        String regex;
        Pattern pattern;

        regex = "^[0-9]{14}$";
        pattern = Pattern.compile(regex);
        if (!pattern.matcher(id).matches()) {
            errors.setErrorMassage("Check " + object + "|GlobalID: Invalid GlobalID (14 characters of A-Z or 0-9). Current GlobalID = " + id + "");
        }

        if (!(schemeId.equals("GLN") && schemeAgencyId.equals("GS1"))) {
            errors.setErrorMassage("Check " + object + "|SchemeId/SchemeAgencyId: Invalid SchemeId/SchemeAgencyId (SchememId=GLN, SchemeAgencyId=GS1). Current SchemeId = " + schemeId + ", SchemeAgencyId = " + schemeAgencyId);
        }
    }
}