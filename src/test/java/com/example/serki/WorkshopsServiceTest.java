package com.example.serki;

import com.example.serki.models.Workshops;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
class WorkshopsServiceTest {

    @Autowired
    WorkshopsRepo workshopsRepo;

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
    public void nameOfTest(){
    //given

    //when

    //then
    }


}