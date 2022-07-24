package com.example.serki;

import com.example.serki.models.SubCathegories;
import com.example.serki.models.Workshops;
import com.example.serki.repository.TypeOfTrainingsRepo;
import com.example.serki.repository.WorkshopsRepo;
import com.example.serki.repository.SubCatRepo;
import com.example.serki.service.WorkshopsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Profile("prod")
public class DBprod implements CommandLineRunner {

    @Autowired
    WorkshopsRepo workshopsRepo;
    @Autowired
    SubCatRepo subCatRepo;
    @Autowired
    TypeOfTrainingsRepo typeOfTrainingsRepo;
    @Autowired
    WorkshopsService workshopsService;

    @Override
    public void run(String... args) throws Exception {

        Workshops it = new Workshops("IT", "sifjaiwjdsidj", "image", Collections.emptyList());
        Workshops marketing = new Workshops("MARKETING", "gesasc", "image", Collections.emptyList());
        Workshops medic = new Workshops("MEDIC", "hhhhhh223", "image", Collections.emptyList());
        Workshops sales = new Workshops("SALES", "sdw", "image", Collections.emptyList());

        Workshops saveIt = workshopsRepo.save(it);
        Workshops saveMarketing = workshopsRepo.save(marketing);
        Workshops saveMedic = workshopsRepo.save(medic);
        Workshops saveSales = workshopsRepo.save(sales);

        SubCathegories java = new SubCathegories("Java", Collections.emptyList());
        SubCathegories csharp = new SubCathegories("C#", Collections.emptyList());
        SubCathegories js = new SubCathegories("JS", Collections.emptyList());
        SubCathegories python = new SubCathegories("Python", Collections.emptyList());

        subCatRepo.save(java);
        subCatRepo.save(csharp);
        subCatRepo.save(js);
        subCatRepo.save(python);

        //        saveIt.setWorkshopsCathegories(Collections.singletonList(new SubCathegories("C#", Collections.emptyList())));

    }
}
