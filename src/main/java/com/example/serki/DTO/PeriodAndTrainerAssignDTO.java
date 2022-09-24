package com.example.serki.DTO;

public class PeriodAndTrainerAssignDTO {

    private final PeriodDTO periodDTO;
    private final TrainerDTO trainerDTO;

    public PeriodAndTrainerAssignDTO(PeriodDTO periodDTO, TrainerDTO trainerDTO) {
        this.periodDTO = periodDTO;
        this.trainerDTO = trainerDTO;
    }

    public PeriodDTO getPeriodDTO() {
        return periodDTO;
    }

    public TrainerDTO getTrainerDTO() {
        return trainerDTO;
    }

}
