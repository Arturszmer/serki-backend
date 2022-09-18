package com.example.serki.DTO;

import java.util.Collections;
import java.util.List;

public class TypeOfTrainingDTO {

    private String name;
    private double price;
    private double duration;
    private String description;
    private String frontId;
    private List<TrainerDTO> trainers;
    private List<TrainingPeriodDTO> trainingPeriodDTOS;

    public TypeOfTrainingDTO(String name,
                             double price,
                             double duration,
                             String description,
                             String frontId,
                             List<TrainerDTO> trainers,
                             List<TrainingPeriodDTO> trainingPeriodDTOS) {
        this.name = name;
        this.price = price;
        this.duration = duration;
        this.description = description;
        this.frontId = frontId;
        this.trainers = trainers;
        this.trainingPeriodDTOS = trainingPeriodDTOS;

    }

    public TypeOfTrainingDTO() {
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

    public double getDuration() {
        return duration;
    }

    public String getDescription() {
        return description;
    }

    public String getFrontId() {
        return frontId;
    }

    public void setFrontId(String frontId) {
        this.frontId = frontId;
    }

    public List<TrainerDTO> getTrainers() {
        return Collections.unmodifiableList(trainers);
    }

    public List<TrainingPeriodDTO> getTrainingPeriodDTOS() {
        return trainingPeriodDTOS;
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
    public void assignFrontId(String typeOfTrainingName, String trainingName){
        this.frontId = typeOfTrainingName + trainingName;
    }
}
