package com.example.serki.service;

import com.example.serki.models.Trainer;
import com.example.serki.models.TypeOfTraining;
import com.example.serki.repository.TrainerRepo;
import com.example.serki.repository.TrainerUnavailableDaysRepo;
import com.example.serki.repository.TypeOfTrainingsRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class TypeOfTrainingServiceTest {
    @Autowired
    TypeOfTrainingsRepo typeOfTrainingsRepo;
    @Autowired
    TrainerRepo trainerRepo;
    @Autowired
    TrainerUnavailableDaysRepo trainerSchedulerepo;
    @Autowired
    TypeOfTrainingService typeOfTrainingService;
    @Autowired
    TrainerService trainerService;

    @BeforeEach
    public void setup(){
        typeOfTrainingsRepo.deleteAll();
    }

    @Test
    public void showUnavailableDaysTrainer(){
        //given
        TypeOfTraining basicJava = getTypeOfTraining();
        Trainer trainer = getTrainer();
        trainerRepo.save(trainer);
        basicJava.assign(trainer);
        typeOfTrainingsRepo.save(basicJava);
        //when
        typeOfTrainingService.assignUnavailableDays(LocalDate.parse("2022-09-30"), basicJava.getDuration(), trainer.getName());
        List<LocalDate> unavailableDays = trainerService.getUnavailableDays(trainer.getName());
        //then
        assertThat(unavailableDays.get(1)).isEqualTo("2022-10-01");

    }

    private Trainer getTrainer() {
        return new Trainer("Andrzej Nowak", "Lorem");
    }

    private TypeOfTraining getTypeOfTraining() {
        return new TypeOfTraining("Basic", 3800.00,  9.0, "popularised in the 1990s with the release");
    }
}
