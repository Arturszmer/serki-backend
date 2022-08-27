package com.example.serki.controller;

import com.example.serki.DTO.*;
import com.example.serki.Exceptions.NameAlreadyExistException;
import com.example.serki.Exceptions.SubCatNotExist;
import com.example.serki.Exceptions.WorkshopsNotExistException;
import com.example.serki.service.SubCatService;
import com.example.serki.service.TrainerService;
import com.example.serki.service.TypeOfTrainingService;
import com.example.serki.service.WorkshopsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("workshopsLayout")
public class WorkshopsController {
    private final WorkshopsService workshopsService;
    private final SubCatService subCatService;
    private final TypeOfTrainingService typeOfTrainingService;
    private final TrainerService trainerService;
    private final Mapper mapper;

    public WorkshopsController(
            WorkshopsService workshopsService,
            SubCatService subCatService,
            TypeOfTrainingService typeOfTrainingService,
            TrainerService trainerService,
            Mapper mapper) {
        this.workshopsService = workshopsService;
        this.subCatService = subCatService;
        this.typeOfTrainingService = typeOfTrainingService;
        this.trainerService = trainerService;
        this.mapper = mapper;
    }

    @GetMapping("/show")
    @ResponseBody
    public List<WorkshopsDTO> getWorkShops(){
        return workshopsService.workshopsList();
    }

    @PostMapping("/add")
    @ResponseBody
    public WorkshopsDTO createWorkshop(@RequestBody WorkshopsDTO workshopsDTO){
        WorkshopsDTO created = workshopsService.addWorkshop(workshopsDTO);
        created.setList(getAllWorkshopsSubCat());
        return created;
    }

    @GetMapping("/workshopsSubCat")
    @ResponseBody
    public List<SubCatDTO> getAllWorkshopsSubCat(){
        return subCatService.workshopsSubCathegoriesList();
    }
    @GetMapping("/workshopsSubCat/{workshopName}")
    @ResponseBody
    public List<SubCatDTO> getWorkshopsSubCat(@PathVariable String workshopName) {
        return workshopsService.getSubCatDTOS(workshopName);
    }

    @PostMapping("/workshopsSubCat/add/{workshopName}")
    public SubCatDTO createSubWorkshops(@RequestBody SubCatDTO subCatDTO, @PathVariable String workshopName) throws NameAlreadyExistException, WorkshopsNotExistException {
        return subCatService.addWorkshopSubCat(subCatDTO, workshopName);
    }

    @GetMapping("/typeOfTrainings/show")
    @ResponseBody
    public List<TypeOfTrainingDTO> getTypeOfTrainings(){
        return typeOfTrainingService.typeOfTrainings();
    }

    @GetMapping("/workshopsSubCat/typeOfTraining/{subCatName}")
    @ResponseBody
    public List<TypeOfTrainingDTO> getTypeOfSpecificTrainings(@PathVariable String subCatName){
        return subCatService.getTypeOfTrainingDTOS(subCatName);
    }

    @PostMapping("typeOfTraining/add/{subCatName}")
    public TypeOfTrainingDTO createTypeOfTraining(@RequestBody TypeOfTrainingDTO typeOfTrainingDTO, @PathVariable String subCatName) throws NameAlreadyExistException, SubCatNotExist {
        return typeOfTrainingService.addTypeOfTraining(typeOfTrainingDTO, subCatName);
    }

    @PostMapping("trainers/add")
    public TrainerDTO addTrainer(@RequestBody TrainerDTO trainerDTO){
        return trainerService.addTrainer(trainerDTO);
    }

    @GetMapping("trainers")
    public List<TrainerDTO> showTrainers(){
        return trainerService.showAllTrainers();
    }
}
