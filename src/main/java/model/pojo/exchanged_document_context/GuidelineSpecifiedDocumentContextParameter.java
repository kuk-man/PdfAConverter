package model.pojo.exchanged_document_context;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import model.pojo.common.DocumentContextParameterID;

public class GuidelineSpecifiedDocumentContextParameter {

    @JacksonXmlProperty(localName = "ram:ID")
    private DocumentContextParameterID id;

    public DocumentContextParameterID getId() {
        return id;
    }

    public void setId(DocumentContextParameterID id) {
        this.id = id;
    }
}
