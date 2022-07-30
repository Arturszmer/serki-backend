package com.example.serki;

import com.example.serki.models.Workshops;
import com.example.serki.repository.WorkshopsRepo;
import com.example.serki.service.WorkshopsService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class WorkshopsControllerTest {

    @Autowired
    WorkshopsService workshopsService;
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
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/workshopsLayout/show")).andDo(MockMvcResultHandlers.print()).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        List<Workshops> workshopsList = objectMapper.readValue(contentAsString, new TypeReference<List<Workshops>>() {
        });
        assertThat(workshopsList.size()).isEqualTo(2);
    }

}