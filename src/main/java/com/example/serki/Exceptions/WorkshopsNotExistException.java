package com.example.serki.Exceptions;

import com.example.serki.repository.WorkshopsRepo;

public class WorkshopsNotExistException extends RuntimeException {

    public WorkshopsNotExistException() {
        super("Workshop doesn't exist");
    }

}
