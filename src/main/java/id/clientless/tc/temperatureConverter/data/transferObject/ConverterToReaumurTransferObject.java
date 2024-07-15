package id.clientless.tc.temperatureConverter.data.transferObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConverterToReaumurTransferObject {
    private Double value;
    private String unit;
}
