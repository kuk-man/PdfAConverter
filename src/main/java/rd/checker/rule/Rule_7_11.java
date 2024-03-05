package rd.checker.rule;

import java.util.List;

import model.ErrorMessage;
import rd.checker.Checker;

public class Rule_7_11 extends Checker {
    public void checkAddress(String lineOne, String lineTwo, String citySubDivisionName, String cityName, String countryID, String postcodeCode, 
        List<String> citySubDivisionNames, List<String> cityNames, List<String> countryIDs, String object) {
            
        if (countryID.equals("")) {
            errors.setErrorMassage("Check "+object+"|CountryID : CountryID cannot be blank. Current CountryID = " + countryID);
        } else 
        if (countryID.equals("TH")){
            checkThaiAddress(citySubDivisionName, cityName, citySubDivisionNames, cityNames, object);
        } else {
            checkInternationalAddress(lineOne, lineTwo, countryID, countryIDs, object);
        }

        if (postcodeCode.equals("")){
            errors.setErrorMassage("Check " + object + "|PostcodeCode: PostcodeCode cannot be blank. Current PostcodeCode = " + postcodeCode);
        }
    }

    private void checkThaiAddress(String citySubDivisionName, String cityName, List<String> citySubDivisionNames, List<String> cityNames, String object) {
        if (citySubDivisionName.equals("")){
            errors.setErrorMassage("Check " + object + "|CitySubDivisionName: Country ID = TH then CitySubDivisionName cannot be blank. Current CitySubDivisionName = " + citySubDivisionName);
        } else {
            if (!citySubDivisionNames.contains(citySubDivisionName)) {
                errors.setErrorMassage("Check " + object + "|CitySubDivisionName: CitySubDivisionName is not in the list). Current CitySubDivisionName = " + citySubDivisionName);
            }
        }

        if (cityName.equals("")){
            errors.setErrorMassage("Check " + object + "|CityName: CountryID = TH then CityName cannot be blank. Current CityName = " + cityName);
        } else {
            if (!cityNames.contains(cityName)) {
                errors.setErrorMassage("Check " + object + "|CityName: CityName is not in the list. CityName = " + cityName);
            }
        }
    }

    public void checkCountrySubDivisionID(String[] countrySubDivisionIDArray, List<String> countrySubDivisionIDs, String object) {
        for (String id : countrySubDivisionIDArray) {        
            checkCountrySubDivisionID(id, countrySubDivisionIDs, object);
        }
    }

    public void checkCountrySubDivisionID(String countrySubDivisionID, List<String> countrySubDivisionIDs, String object) {
        if (countrySubDivisionID.equals("")){
            errors.setErrorMassage("Check " + object + "|CountrySubDivisionID: CountryID = TH then CountrySubDivisionID cannot be blank. Current CountrySubDivisionID = " + countrySubDivisionID);
        } else {
            if (!countrySubDivisionIDs.contains(countrySubDivisionID)) {
                errors.setErrorMassage("Check " + object + "|CountrySubDivisionID: CountrySubDivisionID is not in the list. Current CountrySubDivisionID = " + countrySubDivisionID);
            }
        }
    }

    private void checkInternationalAddress(String lineOne, String lineTwo, String countryID, List<String> countryIDs, String object) {
        if (countryIDs.contains(countryID)) {
            if (lineOne.equals("")){
                errors.setErrorMassage("Check " + object + "|LineOne: CountryID <> TH then LineOne cannot be blank. Current CountryID = " + countryID + ", LineOne = " + lineOne);
            }

            if (lineTwo.equals("")){
                errors.setErrorMassage("Check " + object + "|LineTwo: Country ID <> TH then LineTwo cannot be blank. Current Country ID = " + countryID + ", LineTwo = " + lineTwo);
            }
        } else {
            errors.setErrorMassage("Check " + object + "|CountryID: CountryID is not in the list. Current Country ID = " + countryID);
        }
    }    
}
