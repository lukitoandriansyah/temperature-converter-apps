package id.clientless.tc.temperatureConverter.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import id.clientless.tc.temperatureConverter.data.accessObject.ConverterToAllAccessObject;
import id.clientless.tc.temperatureConverter.data.transferObject.*;
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
public class ConverterToAllService {

    private final GenerateUnitTemperature generateUnitTemperature;
    private final ValidateUnitTemperature validateUnitTemperature;
    private final ValidateValueTemperature validateValueTemperature;

    public ConverterToAllService(GenerateUnitTemperature generateUnitTemperature, ValidateUnitTemperature validateUnitTemperature, ValidateValueTemperature validateValueTemperature) {
        this.generateUnitTemperature = generateUnitTemperature;
        this.validateUnitTemperature = validateUnitTemperature;
        this.validateValueTemperature = validateValueTemperature;
    }


    private final Logger logger = LoggerFactory.getLogger(ConverterToAllService.class);

    public ResponseEntity<Response> celsiusToAll(Double valueTemperature, String unitTemperature) throws JsonProcessingException {
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
        ConverterToAllAccessObject converterToAllAccessObject = new ConverterToAllAccessObject(valueTemperature, unitTemperature);

        //celsius to celsius
        ConverterToCelsiusTransferObject converterToCelsiusTransferObject = new ConverterToCelsiusTransferObject();
        converterToCelsiusTransferObject.setValue(converterToAllAccessObject.getValue());
        converterToCelsiusTransferObject.setUnit("oC");
        //end celsius to celsius

        //celsius to fahrenheit
        Double lowestPointCelsius = 0.0;
        Double highestPointCelsius = 100.0;
        Double deltaHighestToLowestCelsius = highestPointCelsius - lowestPointCelsius;
        Double deltaPresentToLowestCelsius = converterToAllAccessObject.getValue() - lowestPointCelsius;

        Double lowestPointFahrenheit = 32.0;
        Double highestPointFahrenheit = 212.0;
        Double deltaHighestToLowestFahrenheit = highestPointFahrenheit - lowestPointFahrenheit;

        Double presentFahrenheit = ((deltaPresentToLowestCelsius/deltaHighestToLowestCelsius)*deltaHighestToLowestFahrenheit)+lowestPointFahrenheit;

        ConverterToFahrenheitTransferObject converterToFahrenheitTransferObject = new ConverterToFahrenheitTransferObject();
        converterToFahrenheitTransferObject.setValue(presentFahrenheit);
        converterToFahrenheitTransferObject.setUnit("oF");
        //end celsius to fahrenheit

        //celsius to Kelvin
        Double lowestPointKelvin = 273.0;
        Double highestPointKelvin = 373.0;
        Double deltaHighestToLowestKelvin = highestPointKelvin - lowestPointKelvin;

        Double presentKelvin = ((deltaPresentToLowestCelsius/deltaHighestToLowestCelsius)*deltaHighestToLowestKelvin)+lowestPointKelvin;

        ConverterToKelvinTransferObject converterToKelvinTransferObject = new ConverterToKelvinTransferObject();
        converterToKelvinTransferObject.setValue(presentKelvin);
        converterToKelvinTransferObject.setUnit("K");
        //end celsius to Kelvin

        //celsius to reaumur
        Double deltaPresentToLowestPointCelsius = converterToAllAccessObject.getValue() - lowestPointCelsius;

        Double lowestPointReaumur = 0.0;
        Double highestPointReaumur = 80.0;
        Double deltaHighestToLowestReaumur = highestPointReaumur - lowestPointReaumur;

        Double presentReaumur = ((deltaPresentToLowestPointCelsius/deltaHighestToLowestCelsius)*deltaHighestToLowestReaumur)+lowestPointReaumur;

        ConverterToReaumurTransferObject converterToReaumurTransferObject = new ConverterToReaumurTransferObject();
        converterToReaumurTransferObject.setValue(presentReaumur);
        converterToReaumurTransferObject.setUnit("oR");
        //end celsius to reaumur

        //Data Transfer Object
        ConverterToAllTransferObject converterToAllTransferObject = new ConverterToAllTransferObject();
        converterToAllTransferObject.setConverterToCelsiusTransferObject(converterToCelsiusTransferObject);
        converterToAllTransferObject.setConverterToKelvinTransferObject(converterToKelvinTransferObject);
        converterToAllTransferObject.setConverterToFahrenheitTransferObject(converterToFahrenheitTransferObject);
        converterToAllTransferObject.setConverterToReaumurTransferObject(converterToReaumurTransferObject);


        logger.info("================ FINISH PROCESS CONVERT ================");

        //return
        return GenerateResponse.success("Successfully Converted Data", converterToAllTransferObject);
    }

    public ResponseEntity<Response> fahrenheitToAll(Double valueTemperature, String unitTemperature) throws JsonProcessingException {
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

        //Data Access Object
        ConverterToAllAccessObject converterToAllAccessObject = new ConverterToAllAccessObject(valueTemperature, unitTemperature);

        //fahrenheit to celsius
        Double lowestPointFahrenheit = 32.0;
        Double highestPointFahrenheit = 212.0;
        Double deltaHighestToLowestFahrenheit = highestPointFahrenheit - lowestPointFahrenheit;
        Double deltaPresentToLowestPointFahrenheit = converterToAllAccessObject.getValue() - lowestPointFahrenheit;

        Double lowestPointCelsius = 0.0;
        Double highestPointCelsius = 100.0;
        Double deltaHighestToLowestCelsius = highestPointCelsius - lowestPointCelsius;

        Double presentCelsius = ((deltaPresentToLowestPointFahrenheit / deltaHighestToLowestFahrenheit) * deltaHighestToLowestCelsius) + lowestPointCelsius;

        ConverterToCelsiusTransferObject converterToCelsiusTransferObject = new ConverterToCelsiusTransferObject();
        converterToCelsiusTransferObject.setUnit("oC");
        converterToCelsiusTransferObject.setValue(presentCelsius);
        //end fahrenheit to Celsius

        //fahrenheit to fahrenheit
        ConverterToFahrenheitTransferObject converterToFahrenheitTransferObject = new ConverterToFahrenheitTransferObject();
        converterToFahrenheitTransferObject.setValue(converterToAllAccessObject.getValue());
        converterToFahrenheitTransferObject.setUnit("oF");
        //end fahrenheit to fahrenheit

        //fahrenheit to Kelvin
        Double lowestPointKelvin = 273.0;
        Double highestPointKelvin = 373.0;
        Double deltaHighestToLowestKelvin = highestPointKelvin - lowestPointKelvin;

        Double deltaPresentToLowestFahrenheit = converterToAllAccessObject.getValue() - lowestPointFahrenheit;

        Double presentKelvin = ((deltaPresentToLowestFahrenheit/deltaHighestToLowestFahrenheit)*deltaHighestToLowestKelvin)+lowestPointKelvin;

        ConverterToKelvinTransferObject converterToKelvinTransferObject = new ConverterToKelvinTransferObject();
        converterToKelvinTransferObject.setUnit("K");
        converterToKelvinTransferObject.setValue(presentKelvin);
        //end fahrenheit to kelvin

        //fahrenheit to reaumur
        Double lowestPointReaumur = 0.0;
        Double highestPointReaumur = 80.0;
        Double deltaHighestToLowestReaumur = highestPointReaumur - lowestPointReaumur;

        Double presentReaumur = ((deltaPresentToLowestPointFahrenheit/deltaHighestToLowestFahrenheit)*deltaHighestToLowestReaumur)+lowestPointReaumur;

        ConverterToReaumurTransferObject converterToReaumurTransferObject = new ConverterToReaumurTransferObject();
        converterToReaumurTransferObject.setUnit("oR");
        converterToReaumurTransferObject.setValue(presentReaumur);
        //end fahrenheit to reaumur

        //Data Transfer Object
        ConverterToAllTransferObject converterToAllTransferObject = new ConverterToAllTransferObject();
        converterToAllTransferObject.setConverterToReaumurTransferObject(converterToReaumurTransferObject);
        converterToAllTransferObject.setConverterToKelvinTransferObject(converterToKelvinTransferObject);
        converterToAllTransferObject.setConverterToCelsiusTransferObject(converterToCelsiusTransferObject);
        converterToAllTransferObject.setConverterToFahrenheitTransferObject(converterToFahrenheitTransferObject);

        logger.info("================ FINISH PROCESS CONVERT ================");

        //return
        return GenerateResponse.success("Successfully Converted Data", converterToAllTransferObject);
    }

    public ResponseEntity<Response> kelvinToAll(Double valueTemperature, String unitTemperature) throws JsonProcessingException {
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
        ConverterToAllAccessObject converterToAllAccessObject = new ConverterToAllAccessObject(valueTemperature, unitTemperature);

        //kelvin to celsius
        Double lowestPointKelvin = 273.0;
        Double highestPointKelvin = 373.0;
        Double deltaHighestToLowestKelvin = highestPointKelvin - lowestPointKelvin;
        Double deltaPresentToLowestPointKelvin = converterToAllAccessObject.getValue() - lowestPointKelvin;

        Double lowestPointCelsius = 0.0;
        Double highestPointCelsius = 100.0;
        Double deltaHighestToLowestCelsius = highestPointCelsius - lowestPointCelsius;

        Double presentCelsius = ((deltaPresentToLowestPointKelvin / deltaHighestToLowestKelvin) * deltaHighestToLowestCelsius) + lowestPointCelsius;

        ConverterToCelsiusTransferObject converterToCelsiusTransferObject = new ConverterToCelsiusTransferObject();
        converterToCelsiusTransferObject.setValue(presentCelsius);
        converterToCelsiusTransferObject.setUnit("oC");
        //end kelvin to celsius

        //kelvin to fahrenheit
        Double lowestPointFahrenheit = 32.0;
        Double highestPointFahrenheit = 212.0;
        Double deltaHighestToLowestFahrenheit = highestPointFahrenheit - lowestPointFahrenheit;

        Double presentFahrenheit = ((deltaPresentToLowestPointKelvin/deltaHighestToLowestKelvin)*deltaHighestToLowestFahrenheit)+lowestPointFahrenheit;

        ConverterToFahrenheitTransferObject converterToFahrenheitTransferObject = new ConverterToFahrenheitTransferObject();
        converterToFahrenheitTransferObject.setUnit("oF");
        converterToFahrenheitTransferObject.setValue(presentFahrenheit);
        //end kelvin to fahrenheit

        //kelvin to kelvin
        ConverterToKelvinTransferObject converterToKelvinTransferObject = new ConverterToKelvinTransferObject();
        converterToKelvinTransferObject.setUnit("K");
        converterToKelvinTransferObject.setValue(converterToAllAccessObject.getValue());
        //end kelvin to kelvin

        //kelvin to reaumur
        Double lowestPointReaumur = 0.0;
        Double highestPointReaumur = 80.0;
        Double deltaHighestToLowestReaumur = highestPointReaumur - lowestPointReaumur;

        Double presentReaumur = ((deltaPresentToLowestPointKelvin/deltaHighestToLowestKelvin)*deltaHighestToLowestReaumur)+lowestPointReaumur;
        ConverterToReaumurTransferObject converterToReaumurTransferObject = new ConverterToReaumurTransferObject();
        converterToReaumurTransferObject.setValue(presentReaumur);
        converterToReaumurTransferObject.setUnit("oR");
        //end kelvin to reaumur

        //Data Transfer Object
        ConverterToAllTransferObject converterToAllTransferObject = new ConverterToAllTransferObject();
        converterToAllTransferObject.setConverterToReaumurTransferObject(converterToReaumurTransferObject);
        converterToAllTransferObject.setConverterToKelvinTransferObject(converterToKelvinTransferObject);
        converterToAllTransferObject.setConverterToCelsiusTransferObject(converterToCelsiusTransferObject);
        converterToAllTransferObject.setConverterToFahrenheitTransferObject(converterToFahrenheitTransferObject);


        logger.info("================ FINISH PROCESS CONVERT ================");

        //return
        return GenerateResponse.success("Successfully Converted Data", converterToAllTransferObject);
    }

    public ResponseEntity<Response> reaumurToAll(Double valueTemperature, String unitTemperature) throws JsonProcessingException {
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

        //Data Access Object

        ConverterToAllAccessObject converterToAllAccessObject = new ConverterToAllAccessObject(valueTemperature, unitTemperature);

        //reaumur to celsius
        Double lowestPointReaumur = 0.0;
        Double highestPointReaumur = 80.0;
        Double deltaHighestToLowestReaumur = highestPointReaumur - lowestPointReaumur;
        Double deltaPresentToLowestPointReaumur = valueTemperature - lowestPointReaumur;

        Double lowestPointCelsius = 0.0;
        Double highestPointCelsius = 100.0;
        Double deltaHighestToLowestCelsius = highestPointCelsius - lowestPointCelsius;

        Double presentCelsius = ((deltaPresentToLowestPointReaumur / deltaHighestToLowestReaumur) * deltaHighestToLowestCelsius) + lowestPointCelsius;

        ConverterToCelsiusTransferObject converterToCelsiusTransferObject = new ConverterToCelsiusTransferObject();
        converterToCelsiusTransferObject.setValue(presentCelsius);
        converterToCelsiusTransferObject.setUnit("oC");
        //end reaumur to celsius

        //reaumur to fahrenheit
        Double lowestPointFahrenheit = 32.0;
        Double highestPointFahrenheit = 212.0;
        Double deltaHighestToLowestFahrenheit = highestPointFahrenheit - lowestPointFahrenheit;

        Double presentFahrenheit = ((deltaPresentToLowestPointReaumur/deltaHighestToLowestReaumur)*deltaHighestToLowestFahrenheit)+valueTemperature;

        ConverterToFahrenheitTransferObject converterToFahrenheitTransferObject = new ConverterToFahrenheitTransferObject();
        converterToFahrenheitTransferObject.setValue(presentFahrenheit);
        converterToFahrenheitTransferObject.setUnit("oF");
        //end reaumur to fahrenheit

        //reaumur to kelvin
        Double lowestPointKelvin = 273.0;
        Double highestPointKelvin = 373.0;
        Double deltaHighestToLowestKelvin = highestPointKelvin - lowestPointKelvin;

        Double presentKelvin = ((deltaPresentToLowestPointReaumur/deltaHighestToLowestReaumur)*deltaHighestToLowestKelvin)+lowestPointKelvin;

        ConverterToKelvinTransferObject converterToKelvinTransferObject = new ConverterToKelvinTransferObject();
        converterToKelvinTransferObject.setValue(presentKelvin);
        converterToKelvinTransferObject.setUnit("K");
        //end reaumur to kelvin

        //reaumur to reaumur
        ConverterToReaumurTransferObject converterToReaumurTransferObject = new ConverterToReaumurTransferObject();
        converterToReaumurTransferObject.setUnit("oR");
        converterToReaumurTransferObject.setValue(converterToAllAccessObject.getValue());
        //end reaumur to reaumur

        // Data Transfer Object
        ConverterToAllTransferObject converterToAllTransferObject = new ConverterToAllTransferObject();
        converterToAllTransferObject.setConverterToReaumurTransferObject(converterToReaumurTransferObject);
        converterToAllTransferObject.setConverterToKelvinTransferObject(converterToKelvinTransferObject);
        converterToAllTransferObject.setConverterToCelsiusTransferObject(converterToCelsiusTransferObject);
        converterToAllTransferObject.setConverterToFahrenheitTransferObject(converterToFahrenheitTransferObject);

        logger.info("================ FINISH PROCESS CONVERT ================");

        //return
        return GenerateResponse.success("Successfully Converted Data", converterToAllTransferObject);
    }

}
