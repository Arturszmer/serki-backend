package com.example.serki.DTO;

public class TrainerAssignmentDTO {

    private final String trainerName;
    private final String typeOfTrainingName;

    public TrainerAssignmentDTO(String trainerName, String typeOfTrainingName) {
        this.trainerName = trainerName;
        this.typeOfTrainingName = typeOfTrainingName;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public String getTypeOfTrainingName() {
        return typeOfTrainingName;
    }
}
