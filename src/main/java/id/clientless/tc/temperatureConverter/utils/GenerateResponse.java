package id.clientless.tc.temperatureConverter.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import id.clientless.tc.temperatureConverter.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GenerateResponse {
    public static ResponseEntity<Response> success(String message, Object data) throws JsonProcessingException {

        return new ResponseEntity<>(new Response(message, data, 200), HttpStatus.OK);

    }

    public static ResponseEntity<Response> error(String message, Object data) throws JsonProcessingException {

        return new ResponseEntity<>(new Response(message, data, 500), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    public static ResponseEntity<Response> created(String message, Object data) throws JsonProcessingException {

        return new ResponseEntity<>(new Response(message, data, 201), HttpStatus.CREATED);

    }

    public static ResponseEntity<Response> conflict(String message, Object data) throws JsonProcessingException {

        return new ResponseEntity<>(new Response(message, data, 409), HttpStatus.CREATED);

    }


    public static ResponseEntity<Response> notFound(String message, Object data) {

        return new ResponseEntity<>(new Response(message, data, 404), HttpStatus.NOT_FOUND);

    }

    public static ResponseEntity<Response> badRequest(String message, Object data) {

        return new ResponseEntity<>(new Response(message, data, 400), HttpStatus.BAD_REQUEST);

    }

}
