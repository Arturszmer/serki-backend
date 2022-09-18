package com.example.serki.DTO;

public class PeriodAndTrainerAssignDTO {

    private TrainingPeriodDTO trainingPeriodDTO;
    private TrainerDTO trainerDTO;

    public PeriodAndTrainerAssignDTO(TrainingPeriodDTO trainingPeriodDTO, TrainerDTO trainerDTO) {
        this.trainingPeriodDTO = trainingPeriodDTO;
        this.trainerDTO = trainerDTO;
    }

    public TrainingPeriodDTO getTrainingPeriodDTO() {
        return trainingPeriodDTO;
    }

    public void setTrainingPeriodDTO(TrainingPeriodDTO trainingPeriodDTO) {
        this.trainingPeriodDTO = trainingPeriodDTO;
    }

    public TrainerDTO getTrainerDTO() {
        return trainerDTO;
    }

    public void setTrainerDTO(TrainerDTO trainerDTO) {
        this.trainerDTO = trainerDTO;
    }
}
