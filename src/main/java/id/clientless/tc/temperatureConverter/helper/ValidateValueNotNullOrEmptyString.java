package id.clientless.tc.temperatureConverter.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ValidateValueNotNullOrEmptyString {
    private static final Logger logger = LoggerFactory.getLogger(ValidateValueNotNullOrEmptyString.class);

    public boolean validateNotNullOrEmptyString(Object value) {
        if (value == null) {
            logger.info("Value is null");
            return false;
        }
        if (value instanceof String stringValue) {
            if (stringValue.isEmpty()) {
                logger.info("Value is an empty String");
                return false;
            }
            logger.info("Value is a non-empty String");
            return true;
        }
        logger.info("Value is not a String");
        return true;
    }
}
