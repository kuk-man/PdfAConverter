package rd.checker.mandatory_field;

import model.ErrorMessage;
import model.pojo.RootXml;

import rd.checker.Checker;

public abstract class MandatoryFieldChecker extends Checker {
    protected MandatoryFieldChecker() {
        errors = new ErrorMessage();
    }

    public void checkRequiredField(RootXml rootXml) {
    }

    public void checkNotUsedField(RootXml rootXml) {
    }
}
