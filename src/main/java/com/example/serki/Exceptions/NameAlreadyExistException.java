package com.example.serki.Exceptions;

public class NameAlreadyExistException extends RuntimeException {

    public NameAlreadyExistException() {
        super("Name already exist");
    }
}
