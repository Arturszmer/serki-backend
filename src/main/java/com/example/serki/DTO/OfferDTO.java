package com.example.serki.DTO;

import java.util.List;

public class OfferDTO {

    private String email;
    private List<TypeOfTrainingDTO> typeOfTrainingDTOS;

    public OfferDTO(String email,
                    List<TypeOfTrainingDTO> typeOfTrainingDTOS) {
        this.email = email;
        this.typeOfTrainingDTOS = typeOfTrainingDTOS;

    }

    public OfferDTO() {
    }

    public String getEmail() {
        return email;
    }

    public List<TypeOfTrainingDTO> getTypeOfTrainingDTOS() {
        return typeOfTrainingDTOS;
    }
}
