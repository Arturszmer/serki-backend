package com.example.serki.models;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class TypeOfTraining {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private double price;
    private double duration;
    private String description;
    @ManyToMany
    private List<Trainer> trainer;

    public TypeOfTraining(String name, double price, double duration, String description, List<Trainer> trainer) {
        this.name = name;
        this.price = price;
        this.duration = duration;
        this.description = description;
        this.trainer = trainer;
    }

    public TypeOfTraining(String name, double price, double duration, String description) {
        this.name = name;
        this.price = price;
        this.duration = duration;
        this.description = description;
    }


    public TypeOfTraining() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Trainer> getTrainer() {
        return trainer;
    }

    public void setTrainer(List<Trainer> trainer) {
        this.trainer = trainer;
    }

    @Override
    public String toString() {
        return "TypeOfTraining{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", duration=" + duration +
                ", description='" + description + '\'' +
                ", trainer=" + trainer +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeOfTraining that = (TypeOfTraining) o;
        return id == that.id && Double.compare(that.price, price) == 0 && Double.compare(that.duration, duration) == 0 && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(trainer, that.trainer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, duration, description, trainer);
    }
}
