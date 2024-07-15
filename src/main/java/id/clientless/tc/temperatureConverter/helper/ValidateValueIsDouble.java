package id.clientless.tc.temperatureConverter.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ValidateValueIsDouble {

    private static final Logger logger = LoggerFactory.getLogger(ValidateValueIsDouble.class);
    private final ValidateValueNotNullOrEmptyString validateValueNotNullOrEmptyString;

    public ValidateValueIsDouble(ValidateValueNotNullOrEmptyString validateValueNotNullOrEmptyString) {
        this.validateValueNotNullOrEmptyString = validateValueNotNullOrEmptyString;
    }

    private boolean validateValueDouble(Double value){

        if (Double.isNaN(value)) {
            logger.info("Value is NAN");
            return false;
        }
        if (Double.isInfinite(value)) {
            logger.info("Value is Infinite");
            return false;
        }
        logger.info("Value Type is Double");
        return true;
    }

    public boolean valueTypeIsDouble(Double value){
        boolean isHaveValue = validateValueNotNullOrEmptyString.validateNotNullOrEmptyString(value);
        return isHaveValue && validateValueDouble(value);
    }

}
