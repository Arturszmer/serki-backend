package com.example.serki;

import com.example.serki.models.Workshops;
import com.example.serki.models.SubCathegories;
import com.example.serki.repository.WorkshopsRepo;
import com.example.serki.repository.SubCatRepo;
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
    @Autowired
    SubCatRepo subCatRepo;

    @BeforeEach
    public void setup(){
        workshopsRepo.deleteAll();
    }

    @Test
    public void addNewWorkshop(){
    //given
        SubCathegories subCathegories = new SubCathegories("Java", Collections.emptyList());
        subCatRepo.save(subCathegories);
        List<SubCathegories> list = subCatRepo.findAll();
        Workshops workshops = new Workshops("IT", "blebleble", "image", list);

    //when
        workshopsRepo.save(workshops);
    //then
        List<Workshops> list1 = workshopsRepo.findAll();
        assertThat(list1.size()).isEqualTo(1);
    }

    @Test
    public void showAllWorkshops(){
    //given
        Workshops workshops1 = new Workshops("IT", "blebleble", Collections.emptyList());
        Workshops workshops2 = new Workshops("MARKETING", "blebleble2", Collections.emptyList());
        //when
        Workshops save1 = workshopsRepo.save(workshops1);
        save1.setWorkshopsCathegories(Collections.singletonList(new SubCathegories("Java", Collections.emptyList())));

    //then
        assertThat(save1.getWorkshopsCathegories()).isEqualTo(Collections.singletonList(new SubCathegories("Java", Collections.emptyList())));
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

    @Test
    public void findByImageName(){
    //given
        Workshops workshops1 = new Workshops("MARKETING", "blebleble", "image1", Collections.emptyList());
        Workshops workshops2 = new Workshops("IT", "blebleble2", "image2", Collections.emptyList());
    //when
        workshopsRepo.save(workshops1);
        workshopsRepo.save(workshops2);
    //then
        Optional<Workshops> workshop = workshopsRepo.findByImgUrl("image1");
        assertThat(workshop.get()).isEqualTo(workshops1);

    }




}