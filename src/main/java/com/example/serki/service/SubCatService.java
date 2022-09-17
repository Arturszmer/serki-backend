package com.example.serki.service;

import com.example.serki.DTO.Mapper;
import com.example.serki.DTO.SubCatDTO;
import com.example.serki.DTO.TypeOfTrainingDTO;
import com.example.serki.Exceptions.NameAlreadyExistException;
import com.example.serki.Exceptions.WorkshopsNotExistException;
import com.example.serki.models.SubCathegory;
import com.example.serki.models.TypeOfTraining;
import com.example.serki.models.Workshops;
import com.example.serki.repository.SubCatRepo;
import com.example.serki.repository.WorkshopsRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SubCatService {

    private final Mapper mapper;
    private final SubCatRepo subCatRepo;
    private final WorkshopsRepo workshopsRepo;

    public SubCatService(Mapper mapper, SubCatRepo subCatRepo, WorkshopsRepo workshopsRepo) {
        this.mapper = mapper;
        this.subCatRepo = subCatRepo;
        this.workshopsRepo = workshopsRepo;
    }

    public List<SubCatDTO> workshopsSubCathegoriesList(){
        return subCatRepo.findAll().stream()
                .map(mapper::subCatToDTO)
                .collect(Collectors.toList());
    }

    public List<TypeOfTrainingDTO> getTypeOfTrainingDTOS(String subCatName, String workshopName) {
        List<TypeOfTraining> typeOfTrainingList = workshopsRepo.findByName(workshopName)
                .map(Workshops::getWorkshopsCategories)
                .flatMap(subCathegories -> subCathegories.stream()
                        .filter(f -> f.getName().equals(subCatName))
                        .findFirst())
                .map(SubCathegory::getTypeOfTrainings)
                .orElse(Collections.emptyList());
        return typeOfTrainingList.stream()
                .map(mapper::typeOfTrainingToDTO)
                .toList();

    }

    public SubCatDTO addWorkshopSubCat(SubCatDTO subCatDTO, String workshopName){

        if (workshopsRepo.findByName(workshopName).isEmpty()){
            throw new WorkshopsNotExistException();
        }
        Workshops workshops = workshopsRepo.findByName(workshopName).orElseThrow();
        if (workshops.getWorkshopsCategories().stream()
                .anyMatch(subCathegories -> subCathegories.getName()
                        .equalsIgnoreCase(subCatDTO.getName()))){
            throw new NameAlreadyExistException();
        }
        SubCathegory save1 = subCatRepo.save(mapper.subCatDTOtoSubCat(subCatDTO));
        workshops.getWorkshopsCategories().add(save1);
        workshopsRepo.save(workshops);

        return mapper.subCatToDTO(save1);
    }



}
