package com.example.serki.Exceptions;

public class SubCatNotExist extends RuntimeException{

    public SubCatNotExist() {
        super("Sub Category of workshops doesn't exist");
    }
}
