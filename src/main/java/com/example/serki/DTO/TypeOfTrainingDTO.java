package com.example.serki.DTO;

import java.util.Collections;
import java.util.List;

public class TypeOfTrainingDTO {

    private String name;
    private double price;
    private double duration;
    private String description;
    private List<TrainerDTO> trainers;

    public TypeOfTrainingDTO(String name, double price, double duration, String description, List<TrainerDTO> trainers) {
        this.name = name;
        this.price = price;
        this.duration = duration;
        this.description = description;
        this.trainers = trainers;
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

    public List<TrainerDTO> getTrainers() {
        return Collections.unmodifiableList(trainers);
    }

    public void setTrainers(List<TrainerDTO> trainers) {
        this.trainers = trainers;
    }

    @Override
    public String toString() {
        return "TypeOfTrainingDTO{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", duration=" + duration +
                ", description='" + description + '\'' +
                '}';
    }
}
