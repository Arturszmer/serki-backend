package com.example.serki;

import com.example.serki.DTO.OfferDTO;
import com.example.serki.DTO.PeriodDTO;
import com.example.serki.mail.Mails;
import com.example.serki.models.*;
import com.example.serki.repository.*;
import com.example.serki.service.OfferMailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class OfferControllerTest {

    @Autowired
    OfferMailService emailService;
    @Autowired
    TrainerRepo trainerRepo;
    @Autowired
    WorkshopsRepo workshopsRepo;
    @Autowired
    SubCatRepo subCatRepo;
    @Autowired
    TypeOfTrainingsRepo typeOfTrainingsRepo;
    @Autowired
    TrainingPeriodRepo trainingPeriodRepo;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    Mails mails;


    @Test
    public void sendEmail() throws Exception {
        // given
        String trainerName = "Andrzej";
        addTypeOfTraining(trainerName);
        List<PeriodDTO> javaBasicPeriod = List.of(new PeriodDTO(LocalDate.of(2022, 9, 15),
                LocalDate.of(2022, 9, 22)));
        OfferDTO offerDTO = new OfferDTO("arturszmer@gmail.com", trainerName,
                javaBasicPeriod,
                "Basic",
                new BigDecimal(3800));
        String jsonString = objectMapper.writeValueAsString(offerDTO);

        // when
        this.mockMvc.perform(post("/offer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString)).andExpect(status().isOk());

        // then
        assertThat(mails.containsMessageWith("Basic")).isTrue();

    }

    private void addTypeOfTraining(String trainerName) {

        Trainer trainer = new Trainer(trainerName, "The best trainer");
        trainerRepo.save(trainer);
        TrainingPeriod trainingPeriod = new TrainingPeriod(LocalDate.of(2022, 9, 15), LocalDate.of(2022, 9, 22));
        trainingPeriodRepo.save(trainingPeriod);
        TypeOfTraining basicJava = new TypeOfTraining("Basic", 3800.00,  32.0, "desc", "JavaBasic",
                List.of(trainer), List.of(trainingPeriod));
        typeOfTrainingsRepo.save(basicJava);
        SubCathegory java = new SubCathegory("Java", List.of(basicJava));
        subCatRepo.save(java);
        Workshops workshops1 = new Workshops("IT", "blebleble", List.of(java));
        workshopsRepo.save(workshops1);

    }

}
