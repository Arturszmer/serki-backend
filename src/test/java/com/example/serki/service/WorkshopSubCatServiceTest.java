package com.example.serki.service;

import com.example.serki.models.WorkshopsSubCathegories;
import com.example.serki.repository.WorkshopsSubCathegoriesRepo;
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
class WorkshopSubCatServiceTest {

    @Autowired
    WorkshopsSubCathegoriesRepo workshopsSubCatRepo;

    @BeforeEach
    public void delete(){
        workshopsSubCatRepo.deleteAll();
    }

    @Test
    public void AddNewWorkshopSubCathegory(){
    //given
        WorkshopsSubCathegories workshopsSubCathegories = new WorkshopsSubCathegories("Java", Collections.emptyList());

    //when
        workshopsSubCatRepo.save(workshopsSubCathegories);
    //then
        List<WorkshopsSubCathegories> list = workshopsSubCatRepo.findAll();
        assertThat(list.size()).isEqualTo(1);
    }

}