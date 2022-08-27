package com.example.serki;

import com.example.serki.DTO.TrainerAssignmentDTO;
import com.example.serki.models.Trainer;
import com.example.serki.models.TypeOfTraining;
import com.example.serki.repository.TrainerRepo;
import com.example.serki.repository.TypeOfTrainingsRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest
public class TrainersControllerTest {

    @Autowired
    TrainerRepo trainerRepo;
    @Autowired
    TypeOfTrainingsRepo typeOfTrainingsRepo;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    private void aTrainingWithName(String trainingName) {
        TypeOfTraining typeOfTraining = new TypeOfTraining(trainingName,10,10,"fff");
        typeOfTrainingsRepo.save(typeOfTraining);
    }
    private void aTrainerWithName(String trainerName) {
        Trainer trainer = new Trainer(trainerName, "nie taki młody przyszłościowy programista");
        trainerRepo.save(trainer);
    }
    @Test
    public void assignTrainerToTraining() throws Exception {
        // given
        String trainerName = "Artur";
        aTrainerWithName(trainerName);
        // and
        String trainingName = "Js";
        aTrainingWithName(trainingName);
        // and
        TrainerAssignmentDTO trainerAssignmentDTO = new TrainerAssignmentDTO(trainerName, trainingName);
        String jsonString = objectMapper.writeValueAsString(trainerAssignmentDTO);
        // when
        this.mockMvc.perform(post("/workshopsLayout/trainer/assignment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonString))
                .andExpect(status().isOk());
        Optional<TypeOfTraining> specificTraining = typeOfTrainingsRepo
                .findByName(trainerAssignmentDTO
                        .getTypeOfTrainingName());
        // then
        assertThat(specificTraining.stream()
                .flatMap(t -> t.getTrainer()
                        .stream()
                        .map(Trainer::getName))
                .toList())
                .containsExactlyInAnyOrder("Artur");
    }
    @Test
    public void TrainerIsNotExist() throws Exception {
        // given
        String trainingName = "Js";
        aTrainingWithName(trainingName);
        // and
        TrainerAssignmentDTO trainerAssignmentDTO = new TrainerAssignmentDTO("Artur", trainingName);
        String jsonString = objectMapper.writeValueAsString(trainerAssignmentDTO);
        // expect
        this.mockMvc.perform(post("/workshopsLayout/trainer/assignment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonString))
                .andExpect(status().isNotFound());
    }
    @Test
    public void TrainingIsNotExist() throws Exception {
        // given
        String trainerName = "Artur";
        aTrainerWithName(trainerName);
        // and
        TrainerAssignmentDTO trainerAssignmentDTO = new TrainerAssignmentDTO(trainerName, "Barbakan");
        String jsonString = objectMapper.writeValueAsString(trainerAssignmentDTO);
        // expect
        this.mockMvc.perform(post("/workshopsLayout/trainer/assignment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonString))
                .andExpect(status().isNotFound());
    }
    @Test
    public void trainerIsAsigned() throws Exception {
        // given
        String trainerName = "Artur";
        aTrainerWithName(trainerName);
        // and
        String trainingName = "Js";
        aTrainingWithName(trainingName);
        // and
        TrainerAssignmentDTO trainerAssignmentDTO = new TrainerAssignmentDTO(trainerName, trainingName);
        String jsonString = objectMapper.writeValueAsString(trainerAssignmentDTO);
        // and
        this.mockMvc.perform(post("/workshopsLayout/trainer/assignment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonString));
        // when
        this.mockMvc.perform(post("/workshopsLayout/trainer/assignment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonString))
                .andExpect(status().isBadRequest());
        Optional<TypeOfTraining> specificTraining = typeOfTrainingsRepo
                .findByName(trainerAssignmentDTO
                        .getTypeOfTrainingName());
        // then
        assertThat(specificTraining.stream()
                .flatMap(t -> t.getTrainer()
                        .stream()
                        .map(Trainer::getName))
                .toList())
                .containsExactlyInAnyOrder("Artur");
    }
}
