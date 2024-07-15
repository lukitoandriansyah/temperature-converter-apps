package id.clientless.tc.temperatureConverter.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ValidateUnitTemperature {
    private final Logger logger = LoggerFactory.getLogger(ValidateUnitTemperature.class);
    private final ValidateValueIsString validateValueIsString;

    public ValidateUnitTemperature(ValidateValueIsString validateValueIsString) {
        this.validateValueIsString = validateValueIsString;
    }

    public String resultValidate(String unitTemperature, String unitFullnameTemperature, String unitInitialTemperature){
        if(!checkUnitTemperatureIsString(unitTemperature).equals("Yes")){
            return checkUnitTemperatureIsString(unitTemperature);
        }
        if(!checkUnitTemperatureIsValid(unitTemperature,unitFullnameTemperature,unitInitialTemperature).equals("Yes")){
            return checkUnitTemperatureIsValid(unitTemperature,unitFullnameTemperature, unitInitialTemperature);
        }
        logger.info("Parameter unitTemperature is Valid");
        return "Valid";
    }

    private String checkUnitTemperatureIsString(String unitTemperature){
        boolean checkUnitTemperatureIsString = validateValueIsString.valueTypeIsString(unitTemperature);
        if(!checkUnitTemperatureIsString){
            String errMsg = "Parameter unitTemperature must be string and not empty";
            logger.error(errMsg);
            return errMsg;
        }
        return "Yes";
    }

    private String checkUnitTemperatureIsValid(String unitTemperature, String unitFullnameTemperature, String unitInitialTemperature){
        boolean unitTemperatureIsValid = unitTemperature.equals(unitFullnameTemperature)||unitTemperature.equals(unitInitialTemperature);
        if(!unitTemperatureIsValid){
            String errMsg = "Parameter unitTemperature must be "+unitFullnameTemperature+" or "+unitInitialTemperature;
            logger.error(errMsg);
            return errMsg;
        }
        return "Yes";
    }
}
