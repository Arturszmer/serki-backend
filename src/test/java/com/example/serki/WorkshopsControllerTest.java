package com.example.serki;

import com.example.serki.DTO.TrainerAssignmentDTO;
import com.example.serki.DTO.TrainerDTO;
import com.example.serki.models.Trainer;
import com.example.serki.models.Workshops;
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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;


    @BeforeEach
    public void setup(){
        workshopsRepo.deleteAll();
    }

    @Test
    public void shouldShowDTOList() throws Exception {
    //given
        Workshops workshops1 = new Workshops("IT", "blebleble", Collections.emptyList());
        Workshops workshops2 = new Workshops("IT", "blebleble2", Collections.emptyList());
    //when
        workshopsRepo.save(workshops1);
        workshopsRepo.save(workshops2);
    //then
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/workshopsLayout/show"))
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        List<Workshops> workshopsList = objectMapper.readValue(contentAsString, new TypeReference<>() {
        });
        assertThat(workshopsList.size()).isEqualTo(2);
    }

    @Test
    public void addTrainer() throws Exception {
        // given
        TrainerDTO trainerDTO = new TrainerDTO("Konstanty", "Java Master");
        String jsonString = objectMapper.writeValueAsString(trainerDTO);
        // when
        this.mockMvc.perform(post("/workshopsLayout/trainers/add")
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
        String contentAsString = mockMvc.perform(get("/workshopsLayout/trainers")).andReturn().getResponse().getContentAsString();
        List<TrainerDTO> trainerDTO = Arrays.asList(objectMapper.readValue(contentAsString, TrainerDTO[].class));
        // then
        assertThat(trainerDTO.get(0).getName()).isEqualTo("Konstanty");
    }
}