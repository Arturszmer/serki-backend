package com.example.serki.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String bio;
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "fk_workshop2")
    private List<TrainerUnavailableDays> unavailableDays = new ArrayList<>();

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

    public List<TrainerUnavailableDays> getUnavailableDays() {
        return Collections.unmodifiableList(unavailableDays);
    }

    public void assignUnavailableDays(TrainerUnavailableDays trainerUnavailableDays){
        this.unavailableDays.add(trainerUnavailableDays);
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "name='" + name + '\'' +
                ", specialization='" + bio + '\'' +
                '}';
    }
}
