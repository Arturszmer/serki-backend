package com.example.serki.models;

import javax.persistence.*;
import java.util.Objects;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trainer trainer = (Trainer) o;
        return id == trainer.id && Objects.equals(name, trainer.name) && Objects.equals(bio, trainer.bio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, bio);
    }
}
