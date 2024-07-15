package id.clientless.tc.temperatureConverter.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import id.clientless.tc.temperatureConverter.model.Response;
import id.clientless.tc.temperatureConverter.service.ConverterToKelvinService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temperature_converter")
public class ConverterToKelvinController {

    private final ConverterToKelvinService converterToKelvinService;

    public ConverterToKelvinController(ConverterToKelvinService converterToKelvinService) {
        this.converterToKelvinService = converterToKelvinService;
    }


    @GetMapping("/celsius-to-kelvin")
    public ResponseEntity<Response> converterCelsiusToKelvin(@RequestParam String unitTemperature, @RequestParam Double valueTemperature) throws JsonProcessingException {
        return converterToKelvinService.celsiusToKelvin(valueTemperature, unitTemperature);
    }

    @GetMapping("/fahrenheit-to-kelvin")
    public ResponseEntity<Response> converterFahrenheitToKelvin(@RequestParam String unitTemperature, @RequestParam Double valueTemperature) throws JsonProcessingException {
        return converterToKelvinService.fahrenheitToKelvin(valueTemperature, unitTemperature);
    }

    @GetMapping("/kelvin-to-kelvin")
    public ResponseEntity<Response> converterKelvinToKelvin(@RequestParam String unitTemperature, @RequestParam Double valueTemperature) throws JsonProcessingException {
        return converterToKelvinService.kelvinToKelvin(valueTemperature, unitTemperature);
    }

    @GetMapping("/reaumur-to-kelvin")
    public ResponseEntity<Response> converterReaumurToKelvin(@RequestParam String unitTemperature, @RequestParam Double valueTemperature) throws JsonProcessingException {
        return converterToKelvinService.reaumurToKelvin(valueTemperature, unitTemperature);
    }

}
