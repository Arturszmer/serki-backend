package com.example.serki.models;

import javax.persistence.*;

@Entity
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String bio;

    public Trainer(String name, String bio) {
        this.name = name;
        this.bio = bio;
    }

    public Trainer(String name) {
        this.name = name;
    }

    public Trainer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String specialization) {
        this.bio = specialization;
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "name='" + name + '\'' +
                ", specialization='" + bio + '\'' +
                '}';
    }
}
