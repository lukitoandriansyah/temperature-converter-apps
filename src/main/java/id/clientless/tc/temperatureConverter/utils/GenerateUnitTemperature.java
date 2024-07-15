package id.clientless.tc.temperatureConverter.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GenerateUnitTemperature {
    @Value("${unit.fullname.celsius}")
    private String unitFullnameCelsius;
    @Value("${unit.initial.celsius}")
    private String unitInitialCelsius;
    @Value("${unit.fullname.fahrenheit}")
    private String unitFullnameFahrenheit;
    @Value("${unit.initial.fahrenheit}")
    private String unitInitialFahrenheit;
    @Value("${unit.fullname.reaumur}")
    private String unitFullnameReaumur;
    @Value("${unit.initial.reaumur}")
    private String unitInitialReaumur;
    @Value("${unit.fullname.kelvin}")
    private String unitFullnameKelvin;
    @Value("${unit.initial.kelvin}")
    private String unitInitialKelvin;

    public String getUnitFullnameCelsius() {
        return unitFullnameCelsius;
    }

    public String getUnitInitialCelsius() {
        return unitInitialCelsius;
    }

    public String getUnitFullnameFahrenheit() {
        return unitFullnameFahrenheit;
    }

    public String getUnitInitialFahrenheit() {
        return unitInitialFahrenheit;
    }

    public String getUnitFullnameReaumur() {
        return unitFullnameReaumur;
    }

    public String getUnitInitialReaumur() {
        return unitInitialReaumur;
    }

    public String getUnitFullnameKelvin() {
        return unitFullnameKelvin;
    }

    public String getUnitInitialKelvin() {
        return unitInitialKelvin;
    }
}
