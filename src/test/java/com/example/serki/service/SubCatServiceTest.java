package com.example.serki.service;

import com.example.serki.models.SubCathegories;
import com.example.serki.repository.SubCatRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class SubCatServiceTest {

    @Autowired
    SubCatRepo workshopsSubCatRepo;

    @BeforeEach
    public void delete(){
        workshopsSubCatRepo.deleteAll();
    }

    @Test
    public void addNewWorkshopSubCathegory(){
    //given
        SubCathegories subCathegories = new SubCathegories("Java", Collections.emptyList());

    //when
        workshopsSubCatRepo.save(subCathegories);
    //then
        List<SubCathegories> list = workshopsSubCatRepo.findAll();
        assertThat(list.size()).isEqualTo(1);
    }

}