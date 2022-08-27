package com.example.serki.Exceptions;

public class TrainerIsAssigned extends RuntimeException{

    public TrainerIsAssigned(){
        super("Trainer is assigned");
    }
}
