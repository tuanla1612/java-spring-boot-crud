package com.example.demo.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> responseBuilder(String message, HttpStatus httpStatus, Object responseObject ) {
        Map<String, Object> reponse = new HashMap<>();
        reponse.put("message", message);
        reponse.put("httpStatus", httpStatus);
        reponse.put("data", responseObject);

        return new ResponseEntity<>(reponse, httpStatus);
    }
}
