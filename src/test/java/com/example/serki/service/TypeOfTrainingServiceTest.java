package com.example.serki.service;

import com.example.serki.repository.TypeOfTrainingsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class TypeOfTrainingServiceTest {

    @Autowired
    TypeOfTrainingsRepo typeOfTrainingsRepo;
}