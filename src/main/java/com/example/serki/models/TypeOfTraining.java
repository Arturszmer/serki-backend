package com.example.serki.models;

import javax.persistence.*;
import java.util.*;

@Entity
public class TypeOfTraining {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private double price;
    private double duration;
    private String description;
    private String frontId;
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "fk_workshop3")
    private List<TrainingPeriod> trainingPeriod;
    @ManyToMany
    private List<Trainer> trainer;

    public TypeOfTraining(String name,
                          double price,
                          double duration,
                          String description,
                          String frontId,
                          List<Trainer> trainer,
                          List<TrainingPeriod> trainingPeriod) {
        this.name = name;
        this.price = price;
        this.duration = duration;
        this.description = description;
        this.frontId = frontId;
        this.trainer = trainer;
        this.trainingPeriod = trainingPeriod;
    }

    public TypeOfTraining(String name, double price, double duration, String description, String frontId) {
        this.name = name;
        this.price = price;
        this.duration = duration;
        this.description = description;
        this.frontId = frontId;
        this.trainer = new ArrayList<>();
        this.trainingPeriod = new ArrayList<>();
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

    public String getFrontId() {
        return frontId;
    }

    public void setFrontId(String frontId) {
        this.frontId = frontId;
    }

    public List<TrainingPeriod> getTrainingPeriod() {
        return Collections.unmodifiableList(trainingPeriod);
    }

    public List<Trainer> getTrainer() {
        return Collections.unmodifiableList(trainer);
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

    public void assignTrainer(Trainer trainer) {
        this.trainer.add(trainer);
    }
    public void assignPeriod(TrainingPeriod trainingPeriod){
        this.trainingPeriod.add(trainingPeriod);
    }
}
