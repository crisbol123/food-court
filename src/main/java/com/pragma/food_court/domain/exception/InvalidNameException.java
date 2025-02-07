package com.pragma.food_court.domain.exception;

public class InvalidNameException  extends RuntimeException {
    public InvalidNameException(String message) {
        super(message);
    }
}
