package com.example.serki;

import com.example.serki.DTO.PeriodAndTrainerAssignDTO;
import com.example.serki.DTO.PeriodDTO;
import com.example.serki.DTO.TrainerDTO;
import com.example.serki.DTO.TypeOfTrainingDTO;
import com.example.serki.models.SubCathegory;
import com.example.serki.models.Trainer;
import com.example.serki.models.TypeOfTraining;
import com.example.serki.models.Workshops;
import com.example.serki.repository.SubCatRepo;
import com.example.serki.repository.TrainerRepo;
import com.example.serki.repository.TypeOfTrainingsRepo;
import com.example.serki.repository.WorkshopsRepo;
import com.example.serki.service.WorkshopsService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Transactional
@AutoConfigureMockMvc
@SpringBootTest
class WorkshopsControllerTest {

    @Autowired
    WorkshopsService workshopsServiceMock;
    @Autowired
    TrainerRepo trainerRepo;
    @Autowired
    WorkshopsRepo workshopsRepo;
    @Autowired
    SubCatRepo subCatRepo;
    @Autowired
    TypeOfTrainingsRepo typeOfTrainingsRepo;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;


    @BeforeEach
    public void setup(){
        workshopsRepo.deleteAll();
    }

    @Test
    public void shouldShowWorkshopsList() throws Exception {
    //given
        addTypeOfTraining();
        //then
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/workshops"))
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        List<Workshops> workshopsList = objectMapper.readValue(contentAsString, new TypeReference<>() {
        });
        assertThat(workshopsList.size()).isEqualTo(1);
    }

    @Test
    public void addTrainer() throws Exception {
        // given
        TrainerDTO trainerDTO = new TrainerDTO("Konstanty", "Java Master");
        String jsonString = objectMapper.writeValueAsString(trainerDTO);
        // when
        this.mockMvc.perform(post("/trainers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonString))
                .andExpect(status().isOk());
        // then
        assertThat(trainerRepo.findByName("Konstanty")).isNotNull();
    }

    @Test
    public void showTrainer() throws Exception {
        // given
        Trainer trainer = new Trainer("Konstanty", "Java Master");
        trainerRepo.save(trainer);
        // when
        String contentAsString = mockMvc.perform(get("/trainers")).andReturn().getResponse().getContentAsString();
        List<TrainerDTO> trainerDTO = Arrays.asList(objectMapper.readValue(contentAsString, TrainerDTO[].class));
        // then
        assertThat(trainerDTO.get(0).getName()).isEqualTo("Konstanty");
    }
    @Test
    public void showTypeOfTrainings() throws Exception{
        // given
        Workshops workshops1 = new Workshops("IT", "blebleble", new ArrayList<>());
        workshopsRepo.save(workshops1);
        // and
        SubCathegory java = new SubCathegory("Java", new ArrayList<>());
        subCatRepo.save(java);
        subCatRepo.findAll().forEach(workshops1::asssignSubCategory);
        // and
        TypeOfTraining basicJava = new TypeOfTraining("Basic", 3800.00,  32.0, "popularised in the 1960s with the release", "JavaBasic");
        typeOfTrainingsRepo.save(basicJava);
        typeOfTrainingsRepo.findAll()
                .forEach(java::assignTypeOfTraining);
        // when
        String contentAsString = mockMvc.perform(get("/workshop/IT/subCat/Java/typesOfTrainings"))
                .andReturn()
                .getResponse()
                .getContentAsString();
        List<TypeOfTrainingDTO> typeOfTrainingDTOS = Arrays.asList(objectMapper.readValue(contentAsString, TypeOfTrainingDTO[].class));
        // then
        assertThat(typeOfTrainingDTOS.get(0).getName()).isEqualTo("Basic");
    }

    @Test
    public void assignPeriodToTraining() throws Exception {
        // given
        addTypeOfTraining();
        // and
        PeriodDTO periodDTO = new PeriodDTO(LocalDate.of(2022, 9, 23),
                LocalDate.of(2022, 9, 24));
        String jsonString = objectMapper.writeValueAsString(periodDTO);
        // when
        this.mockMvc.perform(post("/workshops/trainingPeriodAssignment/JavaBasic")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonString))
                .andExpect(status().isOk());
            
        // then
        Optional<TypeOfTraining> javaBasic = typeOfTrainingsRepo.findByFrontId("JavaBasic");
        assertThat(javaBasic.map(m -> m.getTrainingPeriod().size()).get()).isEqualTo(1);
    }

    @Test
    public void assignPeriodAndTrainerToTraining() throws Exception {
        // given
        addTypeOfTraining();
        PeriodAndTrainerAssignDTO periodAndTrainerAssignDTO = new PeriodAndTrainerAssignDTO(
                new PeriodDTO(LocalDate.of(2022, 9, 23), LocalDate.of(2022, 9, 24)),
                new TrainerDTO("Andrzej", "ble ble"));
        String jsonString = objectMapper.writeValueAsString(periodAndTrainerAssignDTO);
        // when
        this.mockMvc.perform(post("/workshops/IT/subCat/Java/typesOfTraining/JavaBasic/assignPeriodAndTrainer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonString))
                .andExpect(status().isOk());
        // then
        Optional<Trainer> trainer = trainerRepo.findByName("Andrzej");
        assertThat(trainer.map(m -> m.getUnavailableDays().size()).get()).isEqualTo(2);
    }

    private void addTypeOfTraining() {
        Workshops workshops1 = new Workshops("IT", "blebleble", Collections.emptyList());
        workshopsRepo.save(workshops1);
        SubCathegory java = new SubCathegory("Java", new ArrayList<>());
        subCatRepo.save(java);
        TypeOfTraining basicJava = new TypeOfTraining("Basic", 3800.00,  32.0, "popularised in the 1960s with the release", "JavaBasic");
        typeOfTrainingsRepo.save(basicJava);
    }
}
