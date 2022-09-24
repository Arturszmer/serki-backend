package com.example.serki.Exceptions;

public class TrainingPeriodExist extends RuntimeException {
    public TrainingPeriodExist(){
        super("Training period exists");
    }
}
