package com.leandersonandre.exception;

public class CarteiraNotFoundException extends RuntimeException {
    public CarteiraNotFoundException(String message) {
        super(message);
    }
}
