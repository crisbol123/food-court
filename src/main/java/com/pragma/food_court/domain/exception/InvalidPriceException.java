package com.pragma.food_court.domain.exception;

public class InvalidPriceException extends RuntimeException {
    public InvalidPriceException(String message) {
        super(message);
    }
}
