package id.clientless.tc.temperatureConverter.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import id.clientless.tc.temperatureConverter.model.Response;
import id.clientless.tc.temperatureConverter.service.ConverterToCelsiusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temperature_converter")
public class ConverterToCelsiusController {

    private final ConverterToCelsiusService converterToCelsiusService;

    public ConverterToCelsiusController(ConverterToCelsiusService converterToCelsiusService) {
        this.converterToCelsiusService = converterToCelsiusService;
    }


    @GetMapping("/celsius-to-celsius")
    public ResponseEntity<Response> converterCelsiusToCelsius(@RequestParam String unitTemperature, @RequestParam Double valueTemperature) throws JsonProcessingException {
        return converterToCelsiusService.celsiusToCelsius(valueTemperature, unitTemperature);
    }

    @GetMapping("/fahrenheit-to-celsius")
    public ResponseEntity<Response> converterFahrenheitToCelsius(@RequestParam String unitTemperature, @RequestParam Double valueTemperature) throws JsonProcessingException {
        return converterToCelsiusService.fahrenheitToCelsius(valueTemperature, unitTemperature);
    }

    @GetMapping("/kelvin-to-celsius")
    public ResponseEntity<Response> converterKelvinToCelsius(@RequestParam String unitTemperature, @RequestParam Double valueTemperature) throws JsonProcessingException {
        return converterToCelsiusService.kelvinToCelsius(valueTemperature, unitTemperature);
    }

    @GetMapping("/reaumur-to-celsius")
    public ResponseEntity<Response> converterReaumurToCelsius(@RequestParam String unitTemperature, @RequestParam Double valueTemperature) throws JsonProcessingException {
        return converterToCelsiusService.reaumurToCelsius(valueTemperature, unitTemperature);
    }

}
