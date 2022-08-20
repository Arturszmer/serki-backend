package com.example.serki.Exceptions;

public class SubCatNotExist extends RuntimeException{

    public SubCatNotExist() {
        super("Sub Cathegory of workshops doesn't exist");
    }
}
