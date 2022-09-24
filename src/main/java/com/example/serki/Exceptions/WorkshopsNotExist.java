package com.example.serki.Exceptions;


public class WorkshopsNotExist extends RuntimeException {

    public WorkshopsNotExist() {
        super("Workshop doesn't exist");
    }

}
