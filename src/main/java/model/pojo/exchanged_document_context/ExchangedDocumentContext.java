package model.pojo.exchanged_document_context;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class ExchangedDocumentContext {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "ram:GuidelineSpecifiedDocumentContextParameter")
    private GuidelineSpecifiedDocumentContextParameter[] guidelineSpecifiedDocumentContextParameter;

    public GuidelineSpecifiedDocumentContextParameter[] getGuidelineSpecifiedDocumentContextParameter() {
        return guidelineSpecifiedDocumentContextParameter;
    }

    public void setGuidelineSpecifiedDocumentContextParameter(
            GuidelineSpecifiedDocumentContextParameter[] guidelineSpecifiedDocumentContextParameter) {
        this.guidelineSpecifiedDocumentContextParameter = guidelineSpecifiedDocumentContextParameter;
    }
}
