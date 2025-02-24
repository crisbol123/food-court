package com.pragma.food_court.domain.exception;

public class InvalidRestaurantException extends RuntimeException {
    public InvalidRestaurantException(String message) {
        super(message);
    }
}
