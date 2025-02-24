package com.pragma.food_court.domain.exception;

public class ClientHasAOrderException extends RuntimeException {
    public ClientHasAOrderException(String message) {
        super(message);
    }
}
