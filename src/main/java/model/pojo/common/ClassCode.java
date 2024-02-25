package model.pojo.common;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

@JsonPropertyOrder({"listName", "listVersionID", "value"})
public class ClassCode {

    @JacksonXmlProperty(isAttribute = true)
    private String listName;

    @JacksonXmlProperty(isAttribute = true)
    private String listVersionID;

    @JacksonXmlText
    private String value;

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getListVersionID() {
        return listVersionID;
    }

    public void setListVersionID(String listVersionID) {
        this.listVersionID = listVersionID;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
