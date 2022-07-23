package com.example.serki.DTO;

import com.example.serki.models.TypeOfTraining;

import java.util.List;

public class WorkshopsSubCatDTO {

    private String name;
    private List<TypeOfTraining> typeOfTrainings;

    public WorkshopsSubCatDTO(String name, List<TypeOfTraining> typeOfTrainings) {
        this.name = name;
        this.typeOfTrainings = typeOfTrainings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TypeOfTraining> getTypeOfTrainings() {
        return typeOfTrainings;
    }

    public void setTypeOfTrainings(List<TypeOfTraining> typeOfTrainings) {
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
