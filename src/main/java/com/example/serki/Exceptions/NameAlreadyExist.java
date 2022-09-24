package com.example.serki.Exceptions;

public class NameAlreadyExist extends RuntimeException {
    public NameAlreadyExist() {
        super("Name already exist");
    }
}