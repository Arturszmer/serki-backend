package com.example.serki.service;

import com.example.serki.models.SubCathegories;
import com.example.serki.models.TypeOfTraining;
import com.example.serki.models.Workshops;
import com.example.serki.repository.SubCatRepo;
import com.example.serki.repository.TypeOfTrainingsRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class TypeOfTrainingServiceTest {

    @Autowired
    TypeOfTrainingsRepo typeOfTrainingsRepo;

    @Autowired
    SubCatRepo subCatRepo;

    @BeforeEach
    public void setup(){
        typeOfTrainingsRepo.deleteAll();
    }

    @Test
    public void addNewTypeOfTraing(){
        //given
        TypeOfTraining basicJava = new TypeOfTraining("Basic", 3800.00,  32.0, "popularised in the 1960s with the release");

        //when
        typeOfTrainingsRepo.save(basicJava);
        //then
        List<TypeOfTraining> list1 = typeOfTrainingsRepo.findAll();
        assertThat(list1.size()).isEqualTo(1);
    }
    @Test
    public void addNewTypeOfTrainingToSubCat(){
        //given
        SubCathegories subCathegories = new SubCathegories("Java", Collections.emptyList());
        SubCathegories subCatJava = subCatRepo.save(subCathegories);
        TypeOfTraining basicJava = new TypeOfTraining("Basic", 3800.00,  32.0, "popularised in the 1960s with the release");
        typeOfTrainingsRepo.save(basicJava);
        List<TypeOfTraining> list1 = typeOfTrainingsRepo.findAll();
        //when
        subCatJava.setTypeOfTrainings(list1);
        //then
        assertThat(basicJava.equals(subCatJava.getTypeOfTrainings()));
    }
}