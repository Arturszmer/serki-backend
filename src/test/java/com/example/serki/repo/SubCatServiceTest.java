package com.example.serki.repo;

import com.example.serki.models.SubCathegory;
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
        SubCathegory subCathegory = new SubCathegory("Java", Collections.emptyList());

    //when
        workshopsSubCatRepo.save(subCathegory);

    //then
        List<SubCathegory> list = workshopsSubCatRepo.findAll();
        assertThat(list.size()).isEqualTo(1);
    }

}