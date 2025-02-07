package com.pragma.food_court.configuration.exception_handler;


import com.pragma.food_court.adapters.driven.jpa.mysql.exception.ElementAlreadyExistsException;
import com.pragma.food_court.adapters.driven.jpa.mysql.exception.ElementNotFoundException;
import com.pragma.food_court.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.pragma.food_court.configuration.Constants;
import com.pragma.food_court.domain.exception.InvalidOwnerException;
import com.pragma.food_court.domain.exception.InvalidRestaurantException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(InvalidRestaurantException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidRestaurantException( InvalidRestaurantException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
             exception.getMessage(), HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

@ExceptionHandler(InvalidOwnerException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidOwnerException() {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                Constants.INVALID_OWNER_EXCEPTION_MESSAGE, HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }


    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNoDataFoundException() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ExceptionResponse(
                Constants.NO_DATA_FOUND_EXCEPTION_MESSAGE, HttpStatus.NO_CONTENT.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(ElementAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleELementAlreadyExistsException( ElementAlreadyExistsException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                String.format(Constants.ELEMENT_ALREADY_EXISTS_EXCEPTION_MESSAGE, exception.getMessage()), HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(ElementNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleElementNotFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(
                Constants.ELEMENT_NOT_FOUND_EXCEPTION_MESSAGE, HttpStatus.NOT_FOUND.toString(), LocalDateTime.now()));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


}
