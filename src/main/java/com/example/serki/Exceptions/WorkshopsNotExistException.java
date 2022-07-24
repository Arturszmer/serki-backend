package com.example.serki.Exceptions;

public class WorkshopsNotExistException extends RuntimeException {

    public WorkshopsNotExistException() {
        super("Workshop doesn't exist");
    }

}
