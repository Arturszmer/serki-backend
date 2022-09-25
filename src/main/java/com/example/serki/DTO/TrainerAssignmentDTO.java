package com.example.serki.DTO;

public class TrainerAssignmentDTO {

    private final String trainerName;
    private final String trainingId;

    public TrainerAssignmentDTO(String trainerName, String trainingId) {
        this.trainerName = trainerName;
        this.trainingId = trainingId;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public String getTrainingId() {
        return trainingId;
    }
}
