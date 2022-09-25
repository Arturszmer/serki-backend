package com.example.serki;

import com.example.serki.DTO.OfferDTO;
import com.example.serki.DTO.PeriodDTO;
import com.example.serki.DTO.TrainerDTO;
import com.example.serki.DTO.TypeOfTrainingDTO;
import com.example.serki.models.SubCathegory;
import com.example.serki.models.TypeOfTraining;
import com.example.serki.models.Workshops;
import com.example.serki.repository.TypeOfTrainingsRepo;
import com.example.serki.repository.WorkshopsRepo;
import com.example.serki.repository.SubCatRepo;
import com.example.serki.service.OfferMailService;
import com.example.serki.service.SubCatService;
import com.example.serki.service.WorkshopsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
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
    @Autowired
    OfferMailService offerMailService;

    @Override
    public void run(String... args) {

        Workshops it = new Workshops("IT", "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s", "https://www.itprotoday.com/sites/itprotoday.com/files/styles/article_featured_retina/public/programming%20evolution.jpg?itok=WTj9-yNz");
        Workshops marketing = new Workshops("MARKETING", "It was popularised in the 1960s with the release of Letraset sheets", "https://digitalx.pl/wp-content/uploads/2020/12/content-marketing-manager.jpg");
        Workshops medic = new Workshops("MEDIC", "Lorem Ipsum passages, and more recently with desktop publishing", "https://wallpaper.dog/large/855191.jpg", new ArrayList<>());
        Workshops sales = new Workshops("SALES", "PageMaker including versions of Lorem Ipsum", "", new ArrayList<>());

        Workshops itWorkshops = workshopsRepo.save(it);
        workshopsRepo.save(marketing);
        workshopsRepo.save(medic);
        workshopsRepo.save(sales);

        SubCathegory java = new SubCathegory("Java", new ArrayList<>());
        SubCathegory js = new SubCathegory("JS", new ArrayList<>());
        SubCathegory python = new SubCathegory("Python", new ArrayList<>());

        SubCathegory javaSubCathegory = subCatRepo.save(java);
        subCatRepo.save(js);
        subCatRepo.save(python);
        subCatRepo.findAll()
                .forEach(itWorkshops::asssignSubCategory);
        workshopsRepo.save(itWorkshops);

        TypeOfTraining basicJava = new TypeOfTraining("Basic", 3800.00,  32.0, "popularised in the 1960s with the release", "JavaBasic");
        TypeOfTraining advanceJava = new TypeOfTraining("Advance", 6500.00,  50.0, "PageMaker including versions of Lorem Ipsum", "JavaAdvance");
        TypeOfTraining springJava = new TypeOfTraining("Spring", 5000.00,  16.0, "Spring PageMaker including versions of Lorem Ipsum", "JavaSpring");

        typeOfTrainingsRepo.save(basicJava);
        typeOfTrainingsRepo.save(advanceJava);
        typeOfTrainingsRepo.save(springJava);

        typeOfTrainingsRepo.findAll()
                .forEach(javaSubCathegory::assignTypeOfTraining);
        subCatRepo.save(javaSubCathegory);

        PeriodDTO periodDTO = new PeriodDTO(LocalDate.of(2022, 9, 25),
                LocalDate.of(2022, 9, 26));

        OfferDTO offerDTO = new OfferDTO("arturszmer@gmail.com",
                List.of(new TypeOfTrainingDTO("Basic", 3500, 65, "fwafsd", "JavaBasic",
                List.of(new TrainerDTO("Andrzej", "bbbb")), periodDTO)));

        offerMailService.prepareOffer(offerDTO);
    }
}
