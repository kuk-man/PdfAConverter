package model.pojo.trade_transaction.trade_line_item;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import model.pojo.common.Name;
import model.pojo.common.ClassCode;

@JsonPropertyOrder({"classCode", "className"})
public class DesignatedProductClassification {

    @JacksonXmlProperty(localName = "ram:ClassCode")
    private ClassCode classCode;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "ram:ClassName")
    private Name[] className;

    public ClassCode getClassCode() {
        return classCode;
    }

    public void setClassCode(ClassCode classCode) {
        this.classCode = classCode;
    }

    public Name[] getClassName() {
        return className;
    }

    public void setClassName(Name[] className) {
        this.className = className;
    }
}
