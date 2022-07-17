package com.example.serki.models;

import javax.persistence.*;

@Entity
public class TypeOfTraining {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private double price;
    private double duration;
    private String description;

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

    @Override
    public String toString() {
        return "TypeOfTraining{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", duration=" + duration +
                ", description='" + description + '\'' +
                '}';
    }
}
