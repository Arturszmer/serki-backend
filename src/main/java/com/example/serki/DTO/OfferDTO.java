package com.example.serki.DTO;

import com.example.serki.models.TypeOfTraining;

import java.util.List;

public class OfferDTO {

    private final String email;
    private final List<TypeOfTrainingDTO> training;

    public OfferDTO(String email,
                    List<TypeOfTrainingDTO> training) {
        this.email = email;
        this.training = training;
    }

    public String getEmail() {
        return email;
    }

    public List<TypeOfTrainingDTO> getTraining() {
        return training;
    }
}
