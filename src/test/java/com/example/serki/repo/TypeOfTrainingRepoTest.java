package com.example.serki.repo;

import com.example.serki.models.SubCathegory;
import com.example.serki.models.TypeOfTraining;
import com.example.serki.repository.SubCatRepo;
import com.example.serki.repository.TrainerRepo;
import com.example.serki.repository.TrainerUnavailableDaysRepo;
import com.example.serki.repository.TypeOfTrainingsRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
class TypeOfTrainingRepoTest {

    @Autowired
    TypeOfTrainingsRepo typeOfTrainingsRepo;
    @Autowired
    TrainerRepo trainerRepo;
    @Autowired
    TrainerUnavailableDaysRepo trainerSchedulerepo;
    @Autowired
    SubCatRepo subCatRepo;

    @Test
    public void addNewTypeOfTraining(){
        //given
        TypeOfTraining basicJava = getTypeOfTraining();

        //when
        typeOfTrainingsRepo.save(basicJava);

        //then
        List<TypeOfTraining> list1 = typeOfTrainingsRepo.findAll();
        assertThat(list1.size()).isEqualTo(1);
    }

    @Test
    public void addNewTypeOfTrainingToSubCat(){
        //given
        SubCathegory subCathegory = new SubCathegory("Java", Collections.emptyList());
        SubCathegory subCatJava = subCatRepo.save(subCathegory);
        TypeOfTraining basicJava = getTypeOfTraining();
        typeOfTrainingsRepo.save(basicJava);
        List<TypeOfTraining> typeOfTrainings = typeOfTrainingsRepo.findAll();

        //when
        subCatJava.setTypeOfTrainings(typeOfTrainings);

        //then
        assertThat(basicJava.equals(subCatJava.getTypeOfTrainings()));
    }

    private TypeOfTraining getTypeOfTraining() {
        return new TypeOfTraining("Basic", 3800.00,  32.0, "popularised in the 1990s with the release");
    }
}