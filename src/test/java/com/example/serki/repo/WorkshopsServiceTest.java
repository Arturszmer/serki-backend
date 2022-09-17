package com.example.serki.repo;

import com.example.serki.models.Workshops;
import com.example.serki.models.SubCathegory;
import com.example.serki.repository.WorkshopsRepo;
import com.example.serki.repository.SubCatRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        SubCathegory subCathegory = new SubCathegory("Java", Collections.emptyList());
        subCatRepo.save(subCathegory);
        List<SubCathegory> list = subCatRepo.findAll();
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
        Workshops workshops1 = new Workshops("IT", "blebleble", new ArrayList<>());

        //when
        Workshops save1 = workshopsRepo.save(workshops1);
        save1.asssignSubCategory(new SubCathegory("Java", new ArrayList<>()));

        //then
        assertThat(save1.getWorkshopsCategories().get(0)).isEqualTo(new SubCathegory("Java", new ArrayList<>()));
    }

    @Test
    public void showSpecificWorkshopByName(){
        //given
        Workshops workshops1 = new Workshops("MARKETING", "blebleble", new ArrayList<>());
        Workshops workshops2 = new Workshops("IT", "blebleble2", new ArrayList<>());

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
        Workshops workshops1 = new Workshops("MARKETING", "blebleble", "image1", new ArrayList<>());
        Workshops workshops2 = new Workshops("IT", "blebleble2", "image2", new ArrayList<>());

        //when
        workshopsRepo.save(workshops1);
        workshopsRepo.save(workshops2);

        //then
        Optional<Workshops> workshop = workshopsRepo.findByImgUrl("image1");
        assertThat(workshop.get()).isEqualTo(workshops1);

    }
    @Test
    public void addSubCatToWorkshops(){
        //given
        Workshops workshops2 = new Workshops("IT", "blebleble2", "image2", new ArrayList<>());
        SubCathegory subCathegory = new SubCathegory("Java", new ArrayList<>());
         //when
        workshopsRepo.save(workshops2);
        subCatRepo.save(subCathegory);
        subCatRepo.findAll().forEach(workshops2::asssignSubCategory);
        //then
        Optional<Workshops> workshop = workshopsRepo.findByName("IT");
        assertThat(workshop.get()).isEqualTo(workshops2);

    }






}
