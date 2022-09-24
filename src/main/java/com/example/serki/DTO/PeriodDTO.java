package com.example.serki.DTO;

import java.time.LocalDate;

public class PeriodDTO {

    private final LocalDate startTraining;
    private final LocalDate endTraining;

    public PeriodDTO(LocalDate startTraining, LocalDate endTraining) {
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
