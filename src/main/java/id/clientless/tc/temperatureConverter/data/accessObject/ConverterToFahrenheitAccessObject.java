package id.clientless.tc.temperatureConverter.data.accessObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConverterToFahrenheitAccessObject {
    private Double value;
    private String unit;
}
