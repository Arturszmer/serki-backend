package com.example.serki;

import com.example.serki.DTO.WorkshopsDTO;
import com.example.serki.models.TypeOfTraining;
import com.example.serki.models.Workshops;
import com.example.serki.models.WorkshopsSubCathegories;
import com.example.serki.repository.TypeOfTrainingsRepo;
import com.example.serki.repository.WorkshopsRepo;
import com.example.serki.repository.WorkshopsSubCathegoriesRepo;
import com.example.serki.service.TypeOfTrainingService;
import com.example.serki.service.WorkshopSubCatService;
import com.example.serki.service.WorkshopsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@Profile("prod")
public class DBprod implements CommandLineRunner {

    @Autowired
    WorkshopsRepo workshopsRepo;
    @Autowired
    WorkshopsSubCathegoriesRepo workshopSubCatRepo;
    @Autowired
    TypeOfTrainingsRepo typeOfTrainingsRepo;

    @Override
    public void run(String... args) throws Exception {

        Workshops it = new Workshops("IT", "sifjaiwjdsidj", "image", Collections.emptyList());
        Workshops marketing = new Workshops("MARKETING", "sifjaiwjdsidj", "image", Collections.emptyList());
        Workshops medic = new Workshops("MEDIC", "sifjaiwjdsidj", "image", Collections.emptyList());
        Workshops sales = new Workshops("SALES", "sifjaiwjdsidj", "image", Collections.emptyList());

        workshopsRepo.save(it);
        workshopsRepo.save(marketing);
        workshopsRepo.save(medic);
        workshopsRepo.save(sales);

    }
}
