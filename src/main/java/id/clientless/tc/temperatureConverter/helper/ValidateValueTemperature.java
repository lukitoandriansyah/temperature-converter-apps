package id.clientless.tc.temperatureConverter.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ValidateValueTemperature {
    private final Logger logger = LoggerFactory.getLogger(ValidateValueTemperature.class);
    private final ValidateValueIsDouble validateValueIsDouble;

    public ValidateValueTemperature(ValidateValueIsDouble validateValueIsDouble) {
        this.validateValueIsDouble = validateValueIsDouble;
    }

    public String resultValidate(Double valueTemperature){
        if(!checkValueTemperatureIsDouble(valueTemperature).equals("Yes")){
            return checkValueTemperatureIsDouble(valueTemperature);
        }

        logger.info("Parameter valueTemperature is Valid");
        return "Valid";
    }

    private String checkValueTemperatureIsDouble(Double unitTemperature){
        boolean checkValuesTemperatureIsDouble = validateValueIsDouble.valueTypeIsDouble(unitTemperature);
        if(!checkValuesTemperatureIsDouble){
            String errMsg = "Parameter valueTemperature must be type Double and not null";
            logger.error(errMsg);
            return errMsg;
        }
        return "Yes";
    }
}
