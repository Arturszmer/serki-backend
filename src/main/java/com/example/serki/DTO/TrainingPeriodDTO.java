package com.example.serki.DTO;

import java.time.LocalDate;

public class TrainingPeriodDTO {

    private LocalDate startTraining;
    private LocalDate endTraining;

    public TrainingPeriodDTO(LocalDate startTraining, LocalDate endTraining) {
        this.startTraining = startTraining;
        this.endTraining = endTraining;
    }

    public LocalDate getStartTraining() {
        return startTraining;
    }

    public LocalDate getEndTraining() {
        return endTraining;
    }
}
