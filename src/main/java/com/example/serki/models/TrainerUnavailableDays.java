package com.example.serki.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class TrainerUnavailableDays {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private LocalDate unavailableDay;

    public TrainerUnavailableDays(LocalDate unavailableDay) {
        this.unavailableDay = unavailableDay;
    }

    public TrainerUnavailableDays() {
    }

    public LocalDate getUnavailableDay() {
        return unavailableDay;
    }

    public void setUnavailableDay(LocalDate unavailableDay) {
        this.unavailableDay = unavailableDay;
    }
}
