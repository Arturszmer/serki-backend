package com.example.serki.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class TrainingPeriod {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private LocalDate startTraining;
    private LocalDate endTraining;

    public TrainingPeriod(LocalDate startTraining, LocalDate endTraining) {
        this.startTraining = startTraining;
        this.endTraining = endTraining;
    }

    public TrainingPeriod() {
    }

    public LocalDate getStartTraining() {
        return startTraining;
    }

    public LocalDate getEndTraining() {
        return endTraining;
    }
}
