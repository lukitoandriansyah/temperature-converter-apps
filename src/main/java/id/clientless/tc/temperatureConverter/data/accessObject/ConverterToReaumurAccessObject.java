package id.clientless.tc.temperatureConverter.data.accessObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConverterToReaumurAccessObject {
    private Double value;
    private String unit;
}
