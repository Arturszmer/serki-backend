package com.example.serki.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RuntimeExceptionHandler {

    @ExceptionHandler(value = {WorkshopsNotExist.class})
    public ResponseEntity<ErrorMessage> handleNotExistException(WorkshopsNotExist ex) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorMessage.setMessage(ex.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {NameAlreadyExist.class})
    public ResponseEntity<ErrorMessage> handleNameExistException(NameAlreadyExist ex) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
        errorMessage.setMessage(ex.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = {SubCatNotExist.class})
    public ResponseEntity<ErrorMessage> handleNameExistException(SubCatNotExist ex) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorMessage.setMessage(ex.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {TrainerIsAssigned.class})
    public ResponseEntity<ErrorMessage> handleTrainerIsAssigned(TrainerIsAssigned ex) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorMessage.setMessage(ex.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {TrainerIsNotExist.class})
    public ResponseEntity<ErrorMessage> handleTrainerIsNotExist(TrainerIsNotExist ex) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorMessage.setMessage(ex.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {TypeOfTrainingNotExist.class})
    public ResponseEntity<ErrorMessage> handleTypeOfTrainingNotExist(TypeOfTrainingNotExist ex) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorMessage.setMessage(ex.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {TrainingPeriodExist.class})
    public ResponseEntity<ErrorMessage> handleTrainingPeriodExist(TrainingPeriodExist ex) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
        errorMessage.setMessage(ex.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_ACCEPTABLE);
    }
}