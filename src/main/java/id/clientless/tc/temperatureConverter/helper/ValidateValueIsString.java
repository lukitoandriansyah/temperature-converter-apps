package id.clientless.tc.temperatureConverter.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ValidateValueIsString {
    private static final Logger logger = LoggerFactory.getLogger(ValidateValueIsString.class);

    private  final ValidateValueNotNullOrEmptyString validateValueNotNullOrEmptyString;

    public ValidateValueIsString(ValidateValueNotNullOrEmptyString validateValueNotNullOrEmptyString) {
        this.validateValueNotNullOrEmptyString = validateValueNotNullOrEmptyString;
    }

    private boolean validateValueString(Object value) {
        if (value instanceof String) {
            logger.info("Value is a String");
            return true;
        } else {
            logger.info("Value is not a String");
            return false;
        }
    }

    public boolean valueTypeIsString(String value){
        boolean isHaveValue = validateValueNotNullOrEmptyString.validateNotNullOrEmptyString(value);
        return isHaveValue && validateValueString(value);
    }

}
