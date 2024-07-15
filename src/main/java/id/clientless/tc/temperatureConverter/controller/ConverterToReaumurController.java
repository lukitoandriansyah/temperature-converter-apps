package id.clientless.tc.temperatureConverter.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import id.clientless.tc.temperatureConverter.model.Response;
import id.clientless.tc.temperatureConverter.service.ConverterToReaumurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temperature_converter")
public class ConverterToReaumurController {

    private final ConverterToReaumurService converterToReaumurService;

    public ConverterToReaumurController(ConverterToReaumurService converterToReaumurService) {
        this.converterToReaumurService = converterToReaumurService;
    }


    @GetMapping("/celsius-to-reaumur")
    public ResponseEntity<Response> converterCelsiusToReaumur(@RequestParam String unitTemperature, @RequestParam Double valueTemperature) throws JsonProcessingException {
        return converterToReaumurService.celsiusToReaumur(valueTemperature, unitTemperature);
    }

    @GetMapping("/fahrenheit-to-reaumur")
    public ResponseEntity<Response> converterFahrenheitToReaumur(@RequestParam String unitTemperature, @RequestParam Double valueTemperature) throws JsonProcessingException {
        return converterToReaumurService.fahrenheitToReaumur(valueTemperature, unitTemperature);
    }

    @GetMapping("/kelvin-to-reaumur")
    public ResponseEntity<Response> converterKelvinToReaumur(@RequestParam String unitTemperature, @RequestParam Double valueTemperature) throws JsonProcessingException {
        return converterToReaumurService.kelvinToReaumur(valueTemperature, unitTemperature);
    }

    @GetMapping("/reaumur-to-reaumur")
    public ResponseEntity<Response> converterReaumurToReaumur(@RequestParam String unitTemperature, @RequestParam Double valueTemperature) throws JsonProcessingException {
        return converterToReaumurService.reaumurToReaumur(valueTemperature, unitTemperature);
    }

}
