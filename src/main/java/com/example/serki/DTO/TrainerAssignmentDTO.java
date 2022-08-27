package com.example.serki.DTO;

public class TrainerAssignmentDTO {

    private String trainerName;
    private String typeOfTrainingName;

    public TrainerAssignmentDTO(String trainerName, String typeOfTrainingName) {
        this.trainerName = trainerName;
        this.typeOfTrainingName = typeOfTrainingName;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public String getTypeOfTrainingName() {
        return typeOfTrainingName;
    }

    public void setTypeOfTrainingName(String typeOfTrainingName) {
        this.typeOfTrainingName = typeOfTrainingName;
    }
}
