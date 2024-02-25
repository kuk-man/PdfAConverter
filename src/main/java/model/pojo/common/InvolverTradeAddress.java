package model.pojo.common;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonPropertyOrder({"postcodeCode", "buildingName", "lineOne", "lineTwo", "lineThree", "lineFour", "lineFive", 
                    "streetName", "cityName", "citySubDivisionName", "countryID", "countrySubDivisionID", "buildingNumber"})
public class InvolverTradeAddress extends PostalTradeAddress {

    @JacksonXmlProperty(localName = "ram:CountrySubDivisionID")
    private String countrySubDivisionID;

    public String getCountrySubDivisionID() {
        return countrySubDivisionID;
    }

    public void setCountrySubDivisionID(String countrySubDivisionID) {
        this.countrySubDivisionID = countrySubDivisionID;
    }
}
