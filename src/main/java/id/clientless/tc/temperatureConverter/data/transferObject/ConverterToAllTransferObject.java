package id.clientless.tc.temperatureConverter.data.transferObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConverterToAllTransferObject {
    private ConverterToCelsiusTransferObject converterToCelsiusTransferObject;
    private ConverterToReaumurTransferObject converterToReaumurTransferObject;
    private ConverterToFahrenheitTransferObject converterToFahrenheitTransferObject;
    private ConverterToKelvinTransferObject converterToKelvinTransferObject;
}
