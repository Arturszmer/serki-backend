package com.example.serki.service;

import com.example.serki.DTO.Mapper;
import com.example.serki.models.Trainer;
import com.example.serki.models.TypeOfTraining;
import com.example.serki.repository.TrainerRepo;
import com.example.serki.repository.TypeOfTrainingsRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class TrainerServiceTest {

    @Autowired
    TrainerRepo trainerRepo;

    @Autowired
    TypeOfTrainingsRepo typeOfTrainingsRepo;

    @BeforeEach
    public void setup(){
        trainerRepo.deleteAll();
    }

    @Test
    public void addTrainerToRepo(){
        // given
        Trainer trainerKonstanty = new Trainer("Konstanty","java master");

        // when
        trainerRepo.save(trainerKonstanty);
        List<Trainer> list1 = trainerRepo.findAll();

        // then
        assertThat(list1.size()).isEqualTo(1);
    }
}