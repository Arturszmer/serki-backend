package com.example.serki.controller;

import com.example.serki.DTO.Mapper;
import com.example.serki.DTO.TypeOfTrainingDTO;
import com.example.serki.DTO.WorkshopsDTO;
import com.example.serki.DTO.SubCatDTO;
import com.example.serki.Exceptions.NameAlreadyExistException;
import com.example.serki.Exceptions.SubCatNotExist;
import com.example.serki.Exceptions.WorkshopsNotExistException;
import com.example.serki.service.SubCatService;
import com.example.serki.service.TypeOfTrainingService;
import com.example.serki.service.WorkshopsService;
import org.springframework.web.bind.annotation.*;

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
    private final Mapper mapper;

    public WorkshopsController(WorkshopsService workshopsService, SubCatService subCatService, TypeOfTrainingService typeOfTrainingService, Mapper mapper) {
        this.workshopsService = workshopsService;
        this.subCatService = subCatService;
        this.typeOfTrainingService = typeOfTrainingService;
        this.mapper = mapper;
    }

    @GetMapping("/show")
    @ResponseBody
    public List<WorkshopsDTO> getWorkShops(){
        return workshopsService.workshopsList().stream()
                .map(mapper::workshopsToDTO)
                .collect(Collectors.toList());
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
        return subCatService.workshopsSubCathegoriesList().stream()
                .map(mapper::subCatToDTO)
                .collect(Collectors.toList());
    }
    @GetMapping("/workshopsSubCat/{workshopName}")
    @ResponseBody
    public List<SubCatDTO> getWorkshopsSubCat(@PathVariable String workshopName){
        Optional<WorkshopsDTO> specificWorkshops = workshopsService.workshopsList().stream().map(mapper::workshopsToDTO)
                .filter(f -> f.getName().equals(workshopName))
                .findFirst();
        return specificWorkshops.get().getList();
    }

    @PostMapping("/workshopsSubCat/add/{workshopName}")
    public SubCatDTO createSubWorkshops(@RequestBody SubCatDTO subCatDTO, @PathVariable String workshopName) throws NameAlreadyExistException, WorkshopsNotExistException {
        return subCatService.addWorkshopSubCat(subCatDTO, workshopName);
    }

    @GetMapping("/typeOfTrainings/show")
    @ResponseBody
    public List<TypeOfTrainingDTO> getTypeOfTrainings(){
        return typeOfTrainingService.typeOfTrainings().stream()
                .map(mapper::typeOfTrainingToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/workshopsSubCat/typeOfTraining/{subCatName}")
    @ResponseBody
    public List<TypeOfTrainingDTO> getTypeOfSpecificTrainings(@PathVariable String subCatName){
        Optional<SubCatDTO> specificTypeOfTraining = subCatService.workshopsSubCathegoriesList()
                .stream()
                .map(mapper::subCatToDTO)
                .filter(f -> f.getName().equals(subCatName))
                .findFirst();
        return specificTypeOfTraining.get().getTypeOfTrainings();

    }

    @PostMapping("typeOfTraining/add/{subCatName}")
    public TypeOfTrainingDTO createTypeOfTraining(@RequestBody TypeOfTrainingDTO typeOfTrainingDTO, @PathVariable String subCatName) throws NameAlreadyExistException, SubCatNotExist {
        return typeOfTrainingService.addTypeOfTraining(typeOfTrainingDTO, subCatName);
    }



}
