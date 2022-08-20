package com.example.serki.DTO;

import com.example.serki.models.TypeOfTraining;

import java.util.List;

public class SubCatDTO {

    private String name;
    private List<TypeOfTrainingDTO> typeOfTrainings;

    public SubCatDTO(String name, List<TypeOfTrainingDTO> typeOfTrainings) {
        this.name = name;
        this.typeOfTrainings = typeOfTrainings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TypeOfTrainingDTO> getTypeOfTrainings() {
        return typeOfTrainings;
    }

    public void setTypeOfTrainings(List<TypeOfTrainingDTO> typeOfTrainings) {
        this.typeOfTrainings = typeOfTrainings;
    }

    @Override
    public String toString() {
        return "WorkshopsSubCatDTO{" +
                "name='" + name + '\'' +
                ", typeOfTrainings=" + typeOfTrainings +
                '}';
    }
}
