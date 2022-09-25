package com.example.serki.DTO;

import java.util.Collections;
import java.util.List;

public class TypeOfTrainingDTO {

    private final String name;
    private final double price;
    private final double duration;
    private final String description;
    private String frontId;
    private final List<TrainerDTO> trainers;
    private final PeriodDTO periodDTOS;

    public TypeOfTrainingDTO(String name,
                             double price,
                             double duration,
                             String description,
                             String frontId,
                             List<TrainerDTO> trainers,
                             PeriodDTO periodDTOS) {
        this.name = name;
        this.price = price;
        this.duration = duration;
        this.description = description;
        this.frontId = frontId;
        this.trainers = trainers;
        this.periodDTOS = periodDTOS;

    }

    public String getName() {
        return name;
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

    public List<TrainerDTO> getTrainers() {
        return Collections.unmodifiableList(trainers);
    }

    public PeriodDTO getPeriodDTOS() {
        return periodDTOS;
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
