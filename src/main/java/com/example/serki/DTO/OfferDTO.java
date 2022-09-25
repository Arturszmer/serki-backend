package com.example.serki.DTO;

import java.util.List;

public class OfferDTO {

    private final String email;
    private final List<TypeOfTrainingDTO> typeOfTrainingDTOS;

    public OfferDTO(String email,
                    List<TypeOfTrainingDTO> typeOfTrainingDTOS) {
        this.email = email;
        this.typeOfTrainingDTOS = typeOfTrainingDTOS;

    }

    public String getEmail() {
        return email;
    }

    public List<TypeOfTrainingDTO> getTypeOfTrainingDTOS() {
        return typeOfTrainingDTOS;
    }
}
