package id.clientless.tc.temperatureConverter.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import id.clientless.tc.temperatureConverter.model.Response;
import id.clientless.tc.temperatureConverter.service.ConverterToAllService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temperature_converter")
public class ConverterToAllController {

    private final ConverterToAllService converterToAllService;

    public ConverterToAllController(ConverterToAllService converterToAllService) {
        this.converterToAllService = converterToAllService;
    }


    @GetMapping("/celsius-to-all")
    public ResponseEntity<Response> converterCelsiusToAll(@RequestParam String unitTemperature, @RequestParam Double valueTemperature) throws JsonProcessingException {
        return converterToAllService.celsiusToAll(valueTemperature, unitTemperature);
    }

    @GetMapping("/fahrenheit-to-all")
    public ResponseEntity<Response> converterFahrenheitToAll(@RequestParam String unitTemperature, @RequestParam Double valueTemperature) throws JsonProcessingException {
        return converterToAllService.fahrenheitToAll(valueTemperature, unitTemperature);
    }

    @GetMapping("/kelvin-to-all")
    public ResponseEntity<Response> converterKelvinToAll(@RequestParam String unitTemperature, @RequestParam Double valueTemperature) throws JsonProcessingException {
        return converterToAllService.kelvinToAll(valueTemperature, unitTemperature);
    }

    @GetMapping("/reaumur-to-all")
    public ResponseEntity<Response> converterReaumurToAll(@RequestParam String unitTemperature, @RequestParam Double valueTemperature) throws JsonProcessingException {
        return converterToAllService.reaumurToAll(valueTemperature, unitTemperature);
    }

}
