package com.example.serki.DTO;

import java.math.BigDecimal;
import java.util.List;

public class OfferDTO {

    private final String email;
    private final String trainerName;
    private final List<PeriodDTO> periodDTO;
    private final String typeOfTrainingName;
    private final BigDecimal price;

    public OfferDTO(String email, String trainerName,
                    List<PeriodDTO> periodDTO,
                    String typeOfTrainingName,
                    BigDecimal price) {
        this.email = email;
        this.trainerName = trainerName;
        this.periodDTO = periodDTO;
        this.typeOfTrainingName = typeOfTrainingName;
        this.price = price;
    }

    public String getEmail() {
        return email;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public List<PeriodDTO> getPeriodDTO() {
        return periodDTO;
    }

    public String getTypeOfTrainingName() {
        return typeOfTrainingName;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
