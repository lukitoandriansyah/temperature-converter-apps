package id.clientless.tc.temperatureConverter.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import id.clientless.tc.temperatureConverter.data.accessObject.ConverterToKelvinAccessObject;
import id.clientless.tc.temperatureConverter.data.transferObject.ConverterToKelvinTransferObject;
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
public class ConverterToKelvinService {
    private final GenerateUnitTemperature generateUnitTemperature;
    private final ValidateUnitTemperature validateUnitTemperature;
    private final ValidateValueTemperature validateValueTemperature;

    public ConverterToKelvinService(GenerateUnitTemperature generateUnitTemperature, ValidateUnitTemperature validateUnitTemperature, ValidateValueTemperature validateValueTemperature) {
        this.generateUnitTemperature = generateUnitTemperature;
        this.validateUnitTemperature = validateUnitTemperature;
        this.validateValueTemperature = validateValueTemperature;
    }

    private final Logger logger = LoggerFactory.getLogger(ConverterToKelvinService.class);

    public ResponseEntity<Response> kelvinToKelvin(Double valueTemperature, String unitTemperature) throws JsonProcessingException {
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

        //Data Access Object
        ConverterToKelvinAccessObject converterToKelvinAccessObject = new ConverterToKelvinAccessObject(valueTemperature,unitTemperature);
        //Data Transfer Object
        ConverterToKelvinTransferObject converterToKelvinTransferObject = new ConverterToKelvinTransferObject(converterToKelvinAccessObject.getValue(), "K");

        logger.info("================ FINISH PROCESS CONVERT ================");

        //return
        return GenerateResponse.success("Successfully Converted Data", converterToKelvinTransferObject);
    }



    public ResponseEntity<Response> celsiusToKelvin(Double valueTemperature, String unitTemperature) throws JsonProcessingException {
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

        Double lowestPointKelvin = 273.0;
        Double highestPointKelvin = 373.0;
        Double deltaHighestToLowestKelvin = highestPointKelvin - lowestPointKelvin;

        Double lowestPointCelsius = 0.0;
        Double highestPointCelsius = 100.0;
        Double deltaHighestToLowestCelsius = highestPointCelsius - lowestPointCelsius;
        Double deltaPresentToLowestCelsius = valueTemperature - lowestPointCelsius;


        Double presentKelvin = ((deltaPresentToLowestCelsius/deltaHighestToLowestCelsius)*deltaHighestToLowestKelvin)+lowestPointKelvin;
        //Data Access Object
        ConverterToKelvinAccessObject converterToKelvinAccessObject = new ConverterToKelvinAccessObject(presentKelvin,unitTemperature);
        //Data Transfer Object
        ConverterToKelvinTransferObject converterToKelvinTransferObject = new ConverterToKelvinTransferObject(converterToKelvinAccessObject.getValue(), "K");

        logger.info("================ FINISH PROCESS CONVERT ================");

        //return
        return GenerateResponse.success("Successfully Converted Data", converterToKelvinTransferObject);

    }

    public ResponseEntity<Response> fahrenheitToKelvin(Double valueTemperature, String unitTemperature) throws JsonProcessingException {
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

        Double lowestPointKelvin = 273.0;
        Double highestPointKelvin = 373.0;
        Double deltaHighestToLowestKelvin = highestPointKelvin - lowestPointKelvin;

        Double lowestPointFahrenheit = 32.0;
        Double highestPointFahrenheit = 212.0;
        Double deltaHighestToLowestFahrenheit = highestPointFahrenheit - lowestPointFahrenheit;
        Double deltaPresentToLowestFahrenheit = valueTemperature - lowestPointFahrenheit;

        Double presentKelvin = ((deltaPresentToLowestFahrenheit/deltaHighestToLowestFahrenheit)*deltaHighestToLowestKelvin)+lowestPointKelvin;
        //Data Access Object
        ConverterToKelvinAccessObject converterToKelvinAccessObject = new ConverterToKelvinAccessObject(presentKelvin,unitTemperature);
        //Data Transfer Object
        ConverterToKelvinTransferObject converterToKelvinTransferObject = new ConverterToKelvinTransferObject(converterToKelvinAccessObject.getValue(), "K");

        logger.info("================ FINISH PROCESS CONVERT ================");

        //return
        return GenerateResponse.success("Successfully Converted Data", converterToKelvinTransferObject);
    }

    public ResponseEntity<Response> reaumurToKelvin(Double valueTemperature, String unitTemperature) throws JsonProcessingException {
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

        Double lowestPointKelvin = 273.0;
        Double highestPointKelvin = 373.0;
        Double deltaHighestToLowestKelvin = highestPointKelvin - lowestPointKelvin;

        Double lowestPointReaumur = 0.0;
        Double highestPointReaumur = 80.0;
        Double deltaHighestToLowestReaumur = highestPointReaumur - lowestPointReaumur;
        Double deltaPresentToLowestPointReaumur = valueTemperature - lowestPointReaumur;

        Double presentKelvin = ((deltaPresentToLowestPointReaumur/deltaHighestToLowestReaumur)*deltaHighestToLowestKelvin)+lowestPointKelvin;
        //Data Access Object
        ConverterToKelvinAccessObject converterToKelvinAccessObject = new ConverterToKelvinAccessObject(presentKelvin,unitTemperature);
        //Data Transfer Object
        ConverterToKelvinTransferObject converterToKelvinTransferObject = new ConverterToKelvinTransferObject(converterToKelvinAccessObject.getValue(), "K");

        logger.info("================ FINISH PROCESS CONVERT ================");

        //return
        return GenerateResponse.success("Successfully Converted Data", converterToKelvinTransferObject);
    }



}
