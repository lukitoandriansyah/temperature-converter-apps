package id.clientless.tc.temperatureConverter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response implements Serializable {

    public Response(String message, Object data, int code) {
        this.message = message;
        this.data = data;
        this.code = code;
    }

    private String message = null;
    private Object data = null;
    private int code = 500;

    @Override
    public String toString() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(this);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error converting object to JSON";
        }
    }


}
