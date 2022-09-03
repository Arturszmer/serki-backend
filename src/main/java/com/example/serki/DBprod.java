package com.example.serki;

import com.example.serki.models.SubCathegory;
import com.example.serki.models.TypeOfTraining;
import com.example.serki.models.Workshops;
import com.example.serki.repository.TypeOfTrainingsRepo;
import com.example.serki.repository.WorkshopsRepo;
import com.example.serki.repository.SubCatRepo;
import com.example.serki.service.SubCatService;
import com.example.serki.service.WorkshopsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

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
    @Autowired
    SubCatService subCatService;

    @Override
    public void run(String... args) {

        Workshops it = new Workshops("IT", "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s", "https://www.itprotoday.com/sites/itprotoday.com/files/styles/article_featured_retina/public/programming%20evolution.jpg?itok=WTj9-yNz");
        Workshops marketing = new Workshops("MARKETING", "It was popularised in the 1960s with the release of Letraset sheets", "https://digitalx.pl/wp-content/uploads/2020/12/content-marketing-manager.jpg");
        Workshops medic = new Workshops("MEDIC", "Lorem Ipsum passages, and more recently with desktop publishing", "https://wallpaper.dog/large/855191.jpg", Collections.emptyList());
        Workshops sales = new Workshops("SALES", "PageMaker including versions of Lorem Ipsum", "", Collections.emptyList());

        Workshops saveIt = workshopsRepo.save(it);
        Workshops saveMarketing = workshopsRepo.save(marketing);
        Workshops saveMedic = workshopsRepo.save(medic);
        Workshops saveSales = workshopsRepo.save(sales);

        SubCathegory java = new SubCathegory("Java", Collections.emptyList());
        SubCathegory csharp = new SubCathegory("C#", Collections.emptyList());
        SubCathegory js = new SubCathegory("JS", Collections.emptyList());
        SubCathegory python = new SubCathegory("Python", Collections.emptyList());
        SubCathegory googleAds = new SubCathegory("Google Ads", Collections.emptyList());

        SubCathegory javaSubCathegory = subCatRepo.save(java);
        subCatRepo.save(csharp);
        subCatRepo.save(js);
        subCatRepo.save(python);
        List<SubCathegory> listSubIt = subCatRepo.findAll();
        saveIt.setWorkshopsCategories(listSubIt.stream().toList());
        workshopsRepo.save(saveIt);

        TypeOfTraining basicJava = new TypeOfTraining("Basic", 3800.00,  32.0, "popularised in the 1960s with the release");
        TypeOfTraining advanceJava = new TypeOfTraining("Advance", 6500.00,  50.0, "PageMaker including versions of Lorem Ipsum");
        TypeOfTraining springJava = new TypeOfTraining("Spring", 5000.00,  16.0, "Spring PageMaker including versions of Lorem Ipsum");

        typeOfTrainingsRepo.save(basicJava);
        typeOfTrainingsRepo.save(advanceJava);
        typeOfTrainingsRepo.save(springJava);

        typeOfTrainingsRepo.findAll()
                .forEach(javaSubCathegory::assignTypeOfTraining);
        subCatRepo.save(javaSubCathegory);

        workshopsRepo.save(saveIt);
    }
}
