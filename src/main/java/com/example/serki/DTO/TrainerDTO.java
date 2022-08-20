package com.example.serki.DTO;

public class TrainerDTO {

    private String name;
    private String bio;

    public TrainerDTO(
            String name,
            String bio){
        this.name=name;
        this.bio=bio;
    }


    public String getName(){ return name;}
    public String setName(){return name;}

    public String getBio(){ return bio;}
    public String setBio(){return bio;}

}
