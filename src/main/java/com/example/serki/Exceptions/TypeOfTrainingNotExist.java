package com.example.serki.Exceptions;

public class TypeOfTrainingNotExist extends RuntimeException {

    public TypeOfTrainingNotExist(){
        super("Type of Training doesn't exists");
    }
}
