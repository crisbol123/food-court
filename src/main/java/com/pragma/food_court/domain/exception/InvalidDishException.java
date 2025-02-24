package com.pragma.food_court.domain.exception;

public class InvalidDishException extends RuntimeException {
    public InvalidDishException(String message) {
        super(message);
    }
}
