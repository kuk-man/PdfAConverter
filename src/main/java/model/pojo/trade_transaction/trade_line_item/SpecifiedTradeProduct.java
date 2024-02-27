package model.pojo.trade_transaction.trade_line_item;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import model.pojo.common.GlobalID;
import model.pojo.common.Name;

@JsonPropertyOrder({"id", "globalID", "name", "description", "individualTradeProductInstance", 
                    "designatedProductClassification", "originTradeCountry", "informationNote"})
public class SpecifiedTradeProduct {
    
    @JacksonXmlProperty(localName = "ram:ID")
    private String id;

    @JacksonXmlProperty(localName = "ram:GlobalID")
    private GlobalID globalID;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "ram:Name")
    private Name[] name;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "ram:Description")
    private String[] description;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "ram:IndividualTradeProductInstance")
    private IndividualTradeProductInstance[] individualTradeProductInstance;
    
    @JacksonXmlProperty(localName = "ram:DesignatedProductClassification")
    private DesignatedProductClassification designatedProductClassification;

    @JacksonXmlProperty(localName = "ram:OriginTradeCountry")
    private OriginTradeCountry originTradeCountry;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "ram:InformationNote")
    private InformationNote[] informationNote;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GlobalID getGlobalID() {
        return globalID;
    }

    public void setGlobalID(GlobalID globalID) {
        this.globalID = globalID;
    }

    public Name[] getName() {
        return name;
    }

    public void setName(Name[] name) {
        this.name = name;
    }

    public String[] getDescription() {
        return description;
    }

    public void setDescription(String[] description) {
        this.description = description;
    }

    public IndividualTradeProductInstance[] getIndividualTradeProductInstance() {
        return individualTradeProductInstance;
    }

    public void setIndividualTradeProductInstance(IndividualTradeProductInstance[] individualTradeProductInstance) {
        this.individualTradeProductInstance = individualTradeProductInstance;
    }

    public DesignatedProductClassification getDesignatedProductClassification() {
        return designatedProductClassification;
    }

    public void setDesignatedProductClassification(DesignatedProductClassification designatedProductClassification) {
        this.designatedProductClassification = designatedProductClassification;
    }

    public OriginTradeCountry getOriginTradeCountry() {
        return originTradeCountry;
    }

    public void setOriginTradeCountry(OriginTradeCountry originTradeCountry) {
        this.originTradeCountry = originTradeCountry;
    }

    public InformationNote[] getInformationNote() {
        return informationNote;
    }

    public void setInformationNote(InformationNote[] informationNote) {
        this.informationNote = informationNote;
    }
}
