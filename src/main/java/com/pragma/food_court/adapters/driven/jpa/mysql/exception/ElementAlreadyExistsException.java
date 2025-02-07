package com.pragma.food_court.adapters.driven.jpa.mysql.exception;

public class ElementAlreadyExistsException extends RuntimeException {
    public ElementAlreadyExistsException( String message) {
        super( message);
    }
}
