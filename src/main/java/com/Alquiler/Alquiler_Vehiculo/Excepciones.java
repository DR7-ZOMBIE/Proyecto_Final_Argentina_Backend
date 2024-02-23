package com.Alquiler.Alquiler_Vehiculo;

import org.springframework.http.HttpStatus;

public class Excepciones extends RuntimeException {
    private HttpStatus status = HttpStatus.BAD_REQUEST; // Estado por defecto

    public Excepciones(String message) {
        super(message);
    }

    public Excepciones(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}