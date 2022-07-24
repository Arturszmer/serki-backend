package com.example.serki.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RuntimeExceptionHandler{

    @ExceptionHandler(value = {WorkshopsNotExistException.class})
    public ResponseEntity<ErrorMessage> handleNotExistException(WorkshopsNotExistException ex){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorMessage.setMessage(ex.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {NameAlreadyExistException.class})
    public ResponseEntity<ErrorMessage> handleNameExistException(NameAlreadyExistException ex){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
        errorMessage.setMessage(ex.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_ACCEPTABLE);
    }

}
