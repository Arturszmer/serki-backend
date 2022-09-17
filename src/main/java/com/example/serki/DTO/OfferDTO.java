package com.example.serki.DTO;

import java.time.LocalDate;
import java.util.List;

public class OfferDTO {

    String email;
    List <String> training_id;
    LocalDate startTraining;
    LocalDate endTraining;
    TrainerDTO trainerDTO;

}
