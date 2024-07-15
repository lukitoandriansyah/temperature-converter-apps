package id.clientless.tc.temperatureConverter.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import id.clientless.tc.temperatureConverter.data.accessObject.ConverterToCelsiusAccessObject;
import id.clientless.tc.temperatureConverter.data.transferObject.ConverterToCelsiusTransferObject;
import id.clientless.tc.temperatureConverter.helper.ValidateUnitTemperature;
import id.clientless.tc.temperatureConverter.helper.ValidateValueTemperature;
import id.clientless.tc.temperatureConverter.model.Response;
import id.clientless.tc.temperatureConverter.utils.GenerateResponse;
import id.clientless.tc.temperatureConverter.utils.GenerateUnitTemperature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ConverterToCelsiusService {

    private final GenerateUnitTemperature generateUnitTemperature;
    private final ValidateUnitTemperature validateUnitTemperature;
    private final ValidateValueTemperature validateValueTemperature;

    public ConverterToCelsiusService(GenerateUnitTemperature generateUnitTemperature, ValidateUnitTemperature validateUnitTemperature, ValidateValueTemperature validateValueTemperature) {
        this.generateUnitTemperature = generateUnitTemperature;
        this.validateUnitTemperature = validateUnitTemperature;
        this.validateValueTemperature = validateValueTemperature;
    }


    private final Logger logger = LoggerFactory.getLogger(ConverterToCelsiusService.class);

    public ResponseEntity<Response> celsiusToCelsius(Double valueTemperature, String unitTemperature) throws JsonProcessingException {
        logger.info("================ START PROCESS CONVERT ================");
        //Validate unitTemperature
        String unitCelsius = generateUnitTemperature.getUnitFullnameCelsius();
        String initialUnitCelsius = generateUnitTemperature.getUnitInitialCelsius();
        String resultValidateUnitTempC = (validateUnitTemperature.resultValidate(unitTemperature, unitCelsius, initialUnitCelsius));
        if (!resultValidateUnitTempC.equals("Valid")) {
            return GenerateResponse.badRequest(resultValidateUnitTempC, null);
        }

        //Validate valueTemperature
        String resultValidateValueTempC = (validateValueTemperature.resultValidate(valueTemperature));
        if (!resultValidateValueTempC.equals("Valid")) {
            return GenerateResponse.badRequest(resultValidateValueTempC, null);
        }

        //Data Access Object
        ConverterToCelsiusAccessObject converterToCelsiusAccessObject = new ConverterToCelsiusAccessObject(valueTemperature, unitTemperature);
        //Data Transfer Object
        ConverterToCelsiusTransferObject converterToCelsiusTransferObject = new ConverterToCelsiusTransferObject(converterToCelsiusAccessObject.getValue(), "oC");

        logger.info("================ FINISH PROCESS CONVERT ================");

        //return
        return GenerateResponse.success("Successfully Converted Data", converterToCelsiusTransferObject);
    }

    public ResponseEntity<Response> fahrenheitToCelsius(Double valueTemperature, String unitTemperature) throws JsonProcessingException {
        logger.info("================ START PROCESS CONVERT ================");
        //Validate unitTemperature
        String unitFahrenheit = generateUnitTemperature.getUnitFullnameFahrenheit();
        String initialUnitFahrenheit = generateUnitTemperature.getUnitInitialFahrenheit();
        String resultValidateUnitTempF = (validateUnitTemperature.resultValidate(unitTemperature, unitFahrenheit, initialUnitFahrenheit));
        if (!resultValidateUnitTempF.equals("Valid")) {
            return GenerateResponse.badRequest(resultValidateUnitTempF, null);
        }

        //Validate valueTemperature
        String resultValidateValueTempF = (validateValueTemperature.resultValidate(valueTemperature));
        if (!resultValidateValueTempF.equals("Valid")) {
            return GenerateResponse.badRequest(resultValidateValueTempF, null);
        }

        Double lowestPointFahrenheit = 32.0;
        Double highestPointFahrenheit = 212.0;
        Double deltaHighestToLowestFahrenheit = highestPointFahrenheit - lowestPointFahrenheit;
        Double deltaPresentToLowestPointFahrenheit = valueTemperature - lowestPointFahrenheit;

        Double lowestPointCelsius = 0.0;
        Double highestPointCelsius = 100.0;
        Double deltaHighestToLowestCelsius = highestPointCelsius - lowestPointCelsius;

        Double presentCelsius = ((deltaPresentToLowestPointFahrenheit / deltaHighestToLowestFahrenheit) * deltaHighestToLowestCelsius) + lowestPointCelsius;

        //Data Access Object
        ConverterToCelsiusAccessObject converterToCelsiusAccessObject = new ConverterToCelsiusAccessObject(presentCelsius, unitTemperature);
        //Data Transfer Object
        ConverterToCelsiusTransferObject converterToCelsiusTransferObject = new ConverterToCelsiusTransferObject(converterToCelsiusAccessObject.getValue(), "oC");

        logger.info("================ FINISH PROCESS CONVERT ================");

        //return
        return GenerateResponse.success("Successfully Converted Data", converterToCelsiusTransferObject);
    }

    public ResponseEntity<Response> kelvinToCelsius(Double valueTemperature, String unitTemperature) throws JsonProcessingException {
        logger.info("================ START PROCESS CONVERT ================");
        //Validate unitTemperature
        String unitKelvin = generateUnitTemperature.getUnitFullnameKelvin();
        String initialUnitKelvin = generateUnitTemperature.getUnitInitialKelvin();
        String resultValidateUnitTempK = (validateUnitTemperature.resultValidate(unitTemperature, unitKelvin, initialUnitKelvin));
        if (!resultValidateUnitTempK.equals("Valid")) {
            return GenerateResponse.badRequest(resultValidateUnitTempK, null);
        }

        //Validate valueTemperature
        String resultValidateValueTempK = (validateValueTemperature.resultValidate(valueTemperature));
        if (!resultValidateValueTempK.equals("Valid")) {
            return GenerateResponse.badRequest(resultValidateValueTempK, null);
        }

        Double lowestPointKelvin = 273.0;
        Double highestPointKelvin = 373.0;
        Double deltaHighestToLowestKelvin = highestPointKelvin - lowestPointKelvin;
        Double deltaPresentToLowestPointKelvin = valueTemperature - lowestPointKelvin;

        Double lowestPointCelsius = 0.0;
        Double highestPointCelsius = 100.0;
        Double deltaHighestToLowestCelsius = highestPointCelsius - lowestPointCelsius;

        Double presentCelsius = ((deltaPresentToLowestPointKelvin / deltaHighestToLowestKelvin) * deltaHighestToLowestCelsius) + lowestPointCelsius;

        //Data Access Object
        ConverterToCelsiusAccessObject converterToCelsiusAccessObject = new ConverterToCelsiusAccessObject(presentCelsius, unitTemperature);
        //Data Transfer Object
        ConverterToCelsiusTransferObject converterToCelsiusTransferObject = new ConverterToCelsiusTransferObject(converterToCelsiusAccessObject.getValue(), "oC");

        logger.info("================ FINISH PROCESS CONVERT ================");

        //return
        return GenerateResponse.success("Successfully Converted Data", converterToCelsiusTransferObject);
    }

    public ResponseEntity<Response> reaumurToCelsius(Double valueTemperature, String unitTemperature) throws JsonProcessingException {
        logger.info("================ START PROCESS CONVERT ================");
        //Validate unitTemperature
        String unitReaumur = generateUnitTemperature.getUnitFullnameReaumur();
        String initialUnitReaumur = generateUnitTemperature.getUnitInitialReaumur();
        String resultValidateUnitTempR = (validateUnitTemperature.resultValidate(unitTemperature, unitReaumur, initialUnitReaumur));
        if (!resultValidateUnitTempR.equals("Valid")) {
            return GenerateResponse.badRequest(resultValidateUnitTempR, null);
        }

        //Validate valueTemperature
        String resultValidateValueTempR = (validateValueTemperature.resultValidate(valueTemperature));
        if (!resultValidateValueTempR.equals("Valid")) {
            return GenerateResponse.badRequest(resultValidateValueTempR, null);
        }

        Double lowestPointReaumur = 0.0;
        Double highestPointReaumur = 80.0;
        Double deltaHighestToLowestReaumur = highestPointReaumur - lowestPointReaumur;
        Double deltaPresentToLowestPointReaumur = valueTemperature - lowestPointReaumur;

        Double lowestPointCelsius = 0.0;
        Double highestPointCelsius = 100.0;
        Double deltaHighestToLowestCelsius = highestPointCelsius - lowestPointCelsius;

        Double presentCelsius = ((deltaPresentToLowestPointReaumur / deltaHighestToLowestReaumur) * deltaHighestToLowestCelsius) + lowestPointCelsius;

        //Data Access Object
        ConverterToCelsiusAccessObject converterToCelsiusAccessObject = new ConverterToCelsiusAccessObject(presentCelsius, unitTemperature);
        //Data Transfer Object
        ConverterToCelsiusTransferObject converterToCelsiusTransferObject = new ConverterToCelsiusTransferObject(converterToCelsiusAccessObject.getValue(), "oC");

        logger.info("================ FINISH PROCESS CONVERT ================");

        //return
        return GenerateResponse.success("Successfully Converted Data", converterToCelsiusTransferObject);
    }

}
