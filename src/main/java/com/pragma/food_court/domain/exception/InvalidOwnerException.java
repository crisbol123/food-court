package com.pragma.food_court.domain.exception;

public class InvalidOwnerException extends RuntimeException {
    public InvalidOwnerException( String message) {
        super( message);
    }
    public InvalidOwnerException() {
        super();
    }
}
