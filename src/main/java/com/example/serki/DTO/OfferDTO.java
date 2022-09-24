package com.example.serki.DTO;

import java.util.List;

public class OfferDTO {

    private final String trainerName;
    private final List<PeriodDTO> periodDTO;
    private final String workshopName;
    private final String subCatName;
    private final String typeOfTrainingName;
    private final double price;

    public OfferDTO(String trainerName,
                    List<PeriodDTO> periodDTO,
                    String workshopName,
                    String subCatName,
                    String typeOfTrainingName,
                    double price) {
        this.trainerName = trainerName;
        this.periodDTO = periodDTO;
        this.workshopName = workshopName;
        this.subCatName = subCatName;
        this.typeOfTrainingName = typeOfTrainingName;
        this.price = price;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public List<PeriodDTO> getPeriodDTO() {
        return periodDTO;
    }

    public String getWorkshopName() {
        return workshopName;
    }

    public String getSubCatName() {
        return subCatName;
    }

    public String getTypeOfTrainingName() {
        return typeOfTrainingName;
    }

    public double getPrice() {
        return price;
    }
}
