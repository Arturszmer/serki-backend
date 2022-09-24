package com.example.serki;

import com.example.serki.DTO.PeriodDTO;
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

import java.time.LocalDate;
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
        this.mockMvc.perform(post("/workshops/trainerAssignment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonString))
                .andExpect(status().isOk());

        // then
        assertThat(trainersAssignedToTraining(trainingName))
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
        this.mockMvc.perform(post("/workshops/trainerAssignment")
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
        this.mockMvc.perform(post("/workshops/trainerAssignment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonString))
                .andExpect(status().isNotFound());
    }
    @Test
    public void trainerIsAssigned() throws Exception {
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
        this.mockMvc.perform(post("/workshops/trainerAssignment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonString));
        // when
        this.mockMvc.perform(post("/workshops/trainerAssignment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonString))
                .andExpect(status().isBadRequest());
        // then
        assertThat(trainersAssignedToTraining(trainingName))
                .containsExactlyInAnyOrder("Artur");
    }

    @Test
    public void assignUnavailableDays() throws Exception {
        // given
        String trainerName = "Andrzej";
        aTrainerWithName(trainerName);

        // and
        PeriodDTO periodDTO = new PeriodDTO(LocalDate.of(2022, 9, 23),
                LocalDate.of(2022, 9, 24));
        String jsonString = objectMapper.writeValueAsString(periodDTO);

        // when
        this.mockMvc.perform(post("/trainers/unavailableDaysAssign/Andrzej")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString))
                .andExpect(status().isOk());

        // then
        Optional<Trainer> trainer = trainerRepo.findByName("Andrzej");
        assertThat(trainer.map(m -> m.getUnavailableDays().size()).get()).isEqualTo(2);

    }

    private void aTrainingWithName(String trainingName) {
        TypeOfTraining typeOfTraining = new TypeOfTraining(trainingName, 3800.00,  32.0, "popularised in the 1960s with the release", "JavaBasic");
        typeOfTrainingsRepo.save(typeOfTraining);
    }
    private void aTrainerWithName(String trainerName) {
        Trainer trainer = new Trainer(trainerName, "nie taki młody przyszłościowy programista");
        trainerRepo.save(trainer);
    }

    private List<String> trainersAssignedToTraining(String trainingName) {
        return typeOfTrainingsRepo.findByName(trainingName).stream()
                .flatMap(t -> t.getTrainer()
                        .stream()
                        .map(Trainer::getName))
                .toList();
    }

}
