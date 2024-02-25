package model.pojo.common;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonPropertyOrder({"postcodeCode", "buildingName", "lineOne", "lineTwo", "lineThree", "lineFour", "lineFive", 
                    "streetName", "cityName", "citySubDivisionName", "countryID", "buildingNumber"})
public class PostalTradeAddress {

    @JacksonXmlProperty(localName = "ram:PostcodeCode")
    private String postcodeCode;

    @JacksonXmlProperty(localName = "ram:BuildingName")
    private String buildingName;

    @JacksonXmlProperty(localName = "ram:LineOne")
    private String lineOne;

    @JacksonXmlProperty(localName = "ram:LineTwo")
    private String lineTwo;

    @JacksonXmlProperty(localName = "ram:LineThree")
    private String lineThree;

    @JacksonXmlProperty(localName = "ram:LineFour")
    private String lineFour;
    
    @JacksonXmlProperty(localName = "ram:LineFive")
    private String lineFive;
    
    @JacksonXmlProperty(localName = "ram:StreetName")
    private String streetName;

    @JacksonXmlProperty(localName = "ram:CityName")
    private String cityName;
    
    @JacksonXmlProperty(localName = "ram:CitySubDivisionName")
    private String citySubDivisionName;
    
    @JacksonXmlProperty(localName = "ram:CountryID")
    private String countryID;

    @JacksonXmlProperty(localName = "ram:BuildingNumber")
    private String buildingNumber;

    public String getPostcodeCode() {
        return postcodeCode;
    }

    public void setPostcodeCode(String postcodeCode) {
        this.postcodeCode = postcodeCode;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getLineOne() {
        return lineOne;
    }

    public void setLineOne(String lineOne) {
        this.lineOne = lineOne;
    }

    public String getLineTwo() {
        return lineTwo;
    }

    public void setLineTwo(String lineTwo) {
        this.lineTwo = lineTwo;
    }

    public String getLineThree() {
        return lineThree;
    }

    public void setLineThree(String lineThree) {
        this.lineThree = lineThree;
    }

    public String getLineFour() {
        return lineFour;
    }

    public void setLineFour(String lineFour) {
        this.lineFour = lineFour;
    }

    public String getLineFive() {
        return lineFive;
    }

    public void setLineFive(String lineFive) {
        this.lineFive = lineFive;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCitySubDivisionName() {
        return citySubDivisionName;
    }

    public void setCitySubDivisionName(String citySubDivisionName) {
        this.citySubDivisionName = citySubDivisionName;
    }

    public String getCountryID() {
        return countryID;
    }

    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }
}

