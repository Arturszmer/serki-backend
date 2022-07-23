package com.example.serki;

import com.example.serki.models.Workshops;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
class WorkshopsServiceTest {

    @Autowired
    WorkshopsRepo workshopsRepo;

    @BeforeEach
    public void setup(){
        workshopsRepo.deleteAll();
    }

    @Test
    public void addNewWorkshop(){
    //given
        Workshops workshops = new Workshops("IT", "blebleble", Collections.emptyList());

    //when
        workshopsRepo.save(workshops);
    //then
        List<Workshops> list = workshopsRepo.findAll();
        assertThat(list.size()).isEqualTo(1);
    }

    @Test
    public void showAllWorkshops(){
    //given
        Workshops workshops1 = new Workshops("IT", "blebleble", Collections.emptyList());
        Workshops workshops2 = new Workshops("IT", "blebleble2", Collections.emptyList());

        //when
        workshopsRepo.save(workshops1);
        workshopsRepo.save(workshops2);
        List<Workshops> list = workshopsRepo.findAll();
    //then
        assertThat(list.size()).isEqualTo(1);
    }

    @Test
    public void showSpecificWorkshopByName(){
    //given
        Workshops workshops1 = new Workshops("MARKETING", "blebleble", Collections.emptyList());
        Workshops workshops2 = new Workshops("IT", "blebleble2", Collections.emptyList());
    //when
        workshopsRepo.save(workshops1);
        workshopsRepo.save(workshops2);
        Optional<Workshops> workshop = workshopsRepo.findByName("MARKETING");

        //then
        assertThat(workshop.get()).isEqualTo(workshops1);
    }



}