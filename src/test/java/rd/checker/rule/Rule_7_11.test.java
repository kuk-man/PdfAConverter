package rd.checker.rule;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import connector.DatabaseConnecter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class Rule_7_11Test {
    String object;
    String lineOne, lineTwo, citySubDivisionName, cityName, countryID, postcodeCode;
    String countrySubDivisionID;

    DatabaseConnecter dbCon = new DatabaseConnecter("connection");
    List<String> citySubDivisionNames = dbCon.getCitySubDivisionNames();
    List<String> cityNames = dbCon.getCityNames();
    List<String> countrySubDivisionIDs = dbCon.getCountrySubDivisionIDs();
    List<String> countryIDs = dbCon.getCountryIDs();

    @BeforeEach
    private void setBeforEach() {
        object = "Test";
    }

    @AfterEach
    private void setAfterEach() {
        object = "";
    }
    
    @Test
    void testCheckAddress() {
        // Arrange
        Rule_7_11 rule;

        // Act & Assert
        // PostCode
        lineOne = "A";
        lineTwo = "B";
        citySubDivisionName = "100701";
        cityName = "1007";
        countryID = "TH";
        postcodeCode = "11000";
        rule = new Rule_7_11();
        rule.checkAddress(lineOne, lineTwo, citySubDivisionName, cityName, countryID, postcodeCode, citySubDivisionNames, cityNames, countryIDs, object);
        assertEquals("", rule.getError().getErrorMessage());

        lineOne = "A";
        lineTwo = "B";
        citySubDivisionName = "100701";
        cityName = "1007";
        countryID = "TH";
        postcodeCode = "";
        rule = new Rule_7_11();
        rule.checkAddress(lineOne, lineTwo, citySubDivisionName, cityName, countryID, postcodeCode, citySubDivisionNames, cityNames, countryIDs, object);
        assertEquals("Check " + object + "|PostcodeCode: PostcodeCode cannot be blank. Current PostcodeCode = " + postcodeCode + "\n", rule.getError().getErrorMessage());

        // CountryCode
        lineOne = "A";
        lineTwo = "B";
        citySubDivisionName = "100701";
        cityName = "1007";
        countryID = "";
        postcodeCode = "11000";
        rule = new Rule_7_11();
        rule.checkAddress(lineOne, lineTwo, citySubDivisionName, cityName, countryID, postcodeCode, citySubDivisionNames, cityNames, countryIDs, object);
        assertEquals("Check "+object+"|CountryID : CountryID cannot be blank. Current CountryID = " + countryID + "\n", rule.getError().getErrorMessage());

        // Thai
        lineOne = "A";
        lineTwo = "B";
        citySubDivisionName = "100701";
        cityName = "1007";
        countryID = "TH";
        postcodeCode = "11000";
        rule = new Rule_7_11();
        rule.checkAddress(lineOne, lineTwo, citySubDivisionName, cityName, countryID, postcodeCode, citySubDivisionNames, cityNames, countryIDs, object);
        assertEquals("", rule.getError().getErrorMessage());

        lineOne = "A";
        lineTwo = "B";
        citySubDivisionName = "";
        cityName = "1007";
        countryID = "TH";
        postcodeCode = "11000";
        rule = new Rule_7_11();
        rule.checkAddress(lineOne, lineTwo, citySubDivisionName, cityName, countryID, postcodeCode, citySubDivisionNames, cityNames, countryIDs, object);
        assertEquals("Check " + object + "|CitySubDivisionName: Country ID = TH then CitySubDivisionName cannot be blank. Current CitySubDivisionName = " + citySubDivisionName + "\n", rule.getError().getErrorMessage());
        
        lineOne = "A";
        lineTwo = "B";
        citySubDivisionName = "XYZ";
        cityName = "1007";
        countryID = "TH";
        postcodeCode = "11000";
        rule = new Rule_7_11();
        rule.checkAddress(lineOne, lineTwo, citySubDivisionName, cityName, countryID, postcodeCode, citySubDivisionNames, cityNames, countryIDs, object);
        assertEquals("Check " + object + "|CitySubDivisionName: CitySubDivisionName is not in the list). Current CitySubDivisionName = " + citySubDivisionName + "\n", rule.getError().getErrorMessage());

        lineOne = "A";
        lineTwo = "B";
        citySubDivisionName = "100701";
        cityName = "";
        countryID = "TH";
        postcodeCode = "11000";
        rule = new Rule_7_11();
        rule.checkAddress(lineOne, lineTwo, citySubDivisionName, cityName, countryID, postcodeCode, citySubDivisionNames, cityNames, countryIDs, object);
        assertEquals("Check " + object + "|CityName: CountryID = TH then CityName cannot be blank. Current CityName = " + cityName + "\n", rule.getError().getErrorMessage());

        lineOne = "A";
        lineTwo = "B";
        citySubDivisionName = "100701";
        cityName = "XYZ";
        countryID = "TH";
        postcodeCode = "11000";
        rule = new Rule_7_11();
        rule.checkAddress(lineOne, lineTwo, citySubDivisionName, cityName, countryID, postcodeCode, citySubDivisionNames, cityNames, countryIDs, object);
        assertEquals("Check " + object + "|CityName: CityName is not in the list. CityName = " + cityName + "\n", rule.getError().getErrorMessage());

        // International
        lineOne = "A";
        lineTwo = "B";
        citySubDivisionName = "100701";
        cityName = "1007";
        countryID = "JP";
        postcodeCode = "11000";
        rule = new Rule_7_11();
        rule.checkAddress(lineOne, lineTwo, citySubDivisionName, cityName, countryID, postcodeCode, citySubDivisionNames, cityNames, countryIDs, object);
        assertEquals("", rule.getError().getErrorMessage());

        lineOne = "A";
        lineTwo = "B";
        citySubDivisionName = "100701";
        cityName = "1007";
        countryID = "XYZ";
        postcodeCode = "11000";
        rule = new Rule_7_11();
        rule.checkAddress(lineOne, lineTwo, citySubDivisionName, cityName, countryID, postcodeCode, citySubDivisionNames, cityNames, countryIDs, object);
        assertEquals("Check " + object + "|CountryID: CountryID is not in the list. Current Country ID = " + countryID + "\n", rule.getError().getErrorMessage());

        lineOne = "";
        lineTwo = "B";
        citySubDivisionName = "100701";
        cityName = "1007";
        countryID = "JP";
        postcodeCode = "11000";
        rule = new Rule_7_11();
        rule.checkAddress(lineOne, lineTwo, citySubDivisionName, cityName, countryID, postcodeCode, citySubDivisionNames, cityNames, countryIDs, object);
        assertEquals("Check " + object + "|LineOne: CountryID <> TH then LineOne cannot be blank. Current CountryID = " + countryID + ", LineOne = " + lineOne + "\n", rule.getError().getErrorMessage());

        lineOne = "A";
        lineTwo = "";
        citySubDivisionName = "100701";
        cityName = "1007";
        countryID = "JP";
        postcodeCode = "11000";
        rule = new Rule_7_11();
        rule.checkAddress(lineOne, lineTwo, citySubDivisionName, cityName, countryID, postcodeCode, citySubDivisionNames, cityNames, countryIDs, object);
        assertEquals("Check " + object + "|LineTwo: Country ID <> TH then LineTwo cannot be blank. Current Country ID = " + countryID + ", LineTwo = " + lineTwo + "\n", rule.getError().getErrorMessage());
    }

    @Test
    void testCheckCountrySubDivisionID() {
        // Arrange
        Rule_7_11 rule;
        String[] countrySubDivisionIDArray;

        // Act & Assert
        countrySubDivisionID = "37";
        countrySubDivisionIDArray = new String[] {countrySubDivisionID};
        rule = new Rule_7_11();
        rule.checkCountrySubDivisionID(countrySubDivisionIDArray, countrySubDivisionIDs, object);
        assertEquals("", rule.getError().getErrorMessage());

        countrySubDivisionID = "";
        countrySubDivisionIDArray = new String[] {countrySubDivisionID};
        rule = new Rule_7_11();
        rule.checkCountrySubDivisionID(countrySubDivisionIDArray, countrySubDivisionIDs, object);
        assertEquals("Check " + object + "|CountrySubDivisionID: CountryID = TH then CountrySubDivisionID cannot be blank. Current CountrySubDivisionID = " + countrySubDivisionID + "\n", rule.getError().getErrorMessage());

        countrySubDivisionID = "XYZ";
        countrySubDivisionIDArray = new String[] {countrySubDivisionID};
        rule = new Rule_7_11();
        rule.checkCountrySubDivisionID(countrySubDivisionIDArray, countrySubDivisionIDs, object);
        assertEquals("Check " + object + "|CountrySubDivisionID: CountrySubDivisionID is not in the list. Current CountrySubDivisionID = " + countrySubDivisionID + "\n", rule.getError().getErrorMessage());
    }
}