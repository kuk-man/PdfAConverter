package rd.checker.rule;

import java.util.regex.Pattern;

import model.ErrorMessage;
import model.pojo.common.GlobalID;
import model.pojo.common.ID;
import rd.checker.Checker;

public class Rule_6 extends Checker {
    public void checkID(ID id, String object, ErrorMessage errors) {
        String value = id.getValue() == null ? "" : id.getValue();
        String schemeId = id.getSchemeID() == null ? "" : id.getSchemeID();

        String regex;
        Pattern pattern;

        switch(schemeId) {
            case "":
                regex = "^[A-Z0-9]{13}$";
                pattern = Pattern.compile(regex);
                if (!pattern.matcher(value).matches()) {
                    errors.setErrorMassage("Check " + object + ": Invalid ID (13 characters of A-Z/0-9). Current ID = " + value + "");
                }
            break;
            case "TXID": 
                regex = "^[0-9]{18}$";
                pattern = Pattern.compile(regex);
                if (!pattern.matcher(value).matches()) {
                    errors.setErrorMassage("Check " + object + ": Invalid ID (18 numerics of 0-9). Current ID = " + value + "");
                }
            break;
            case "NIDN": 
                regex = "^[0-9]{13}$";
                pattern = Pattern.compile(regex);
                if (!pattern.matcher(value).matches()) {
                    errors.setErrorMassage("Check " + object + ": Invalid ID (13 numberics of 0-9). Current ID = " + value + "");
                }
            break;
            case "CCPT":
                // do nothing
            break;
            case "OTHR":
                if ("N/A".equals(id)) {
                    errors.setErrorMassage("Check " + object + ": Invalid ID (\"N/A\"). Current ID = " + value + "");
                }
            break;
            default:
                errors.setErrorMassage("Check " + object + ": Invalid Scheme ID (Blank/TXID/NIDN/CCPT/OTHR). Current Scheme ID = " + schemeId);
            break;
        }
    }

    public void checkGlobalID(GlobalID globalID, String object, ErrorMessage errors) {
        String value = globalID.getValue() == null ? "" : globalID.getValue();
        String schemeId = globalID.getSchemeID() == null ? "" : globalID.getSchemeID();
        String schemeAgencyId = globalID.getSchemeAgencyID() == null ? "" : globalID.getSchemeAgencyID();

        String regex = "^[A-Z0-9]{1,70}$";
        Pattern pattern= Pattern.compile(regex);

        if (!pattern.matcher(value).matches()) {
            errors.setErrorMassage("Check " + object + ": Invalid GlobalID (<=70 characters of A-Z or 0-9). Current GlobalID = " + value + "");
        }

        if (!(schemeId.equals("GLN") && schemeAgencyId.equals("GS1"))) {
            errors.setErrorMassage("Check " + object + ": Invalid GlobalID (SchememId=GLN, SchemeAgencyId=GS1). Current GlobalID = " + value + 
                            ", SchemeId = " + schemeId + ", SchemeAgencyId = " + schemeAgencyId);
        }
    }
}
