package com.example.serki.DTO;

import java.util.Collections;
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
        return Collections.unmodifiableList(typeOfTrainings);
    }

    @Override
    public String toString() {
        return "WorkshopsSubCatDTO{" +
                "name='" + name + '\'' +
                ", typeOfTrainings=" + typeOfTrainings +
                '}';
    }
}
