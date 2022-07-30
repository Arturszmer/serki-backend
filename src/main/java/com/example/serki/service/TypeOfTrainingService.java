package com.example.serki.service;


import com.example.serki.DTO.Mapper;
import com.example.serki.DTO.TypeOfTrainingDTO;
import com.example.serki.models.SubCathegories;
import com.example.serki.models.TypeOfTraining;
import com.example.serki.repository.SubCatRepo;
import com.example.serki.repository.TypeOfTrainingsRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TypeOfTrainingService {

    private final Mapper mapper;
    private final TypeOfTrainingsRepo typeOfTrainingsRepo;
    private final SubCatRepo subCatRepo;

    public TypeOfTrainingService(Mapper mapper, TypeOfTrainingsRepo typeOfTrainingsRepo, SubCatRepo subCatRepo) {
        this.mapper = mapper;
        this.typeOfTrainingsRepo = typeOfTrainingsRepo;
        this.subCatRepo = subCatRepo;
    }

    public List<TypeOfTraining> typeOfTrainings(){
        return typeOfTrainingsRepo.findAll();
    }

    public TypeOfTrainingDTO addTypeOfTraining(TypeOfTrainingDTO typeOfTrainingDTO, String subCatName){

        SubCathegories subCathegories = subCatRepo.findSubCathegoriesByName(subCatName);

        TypeOfTraining typeOfTraining = mapper.typeOfTrainingDTOtoTypeOfTraining(typeOfTrainingDTO);
        TypeOfTraining save = typeOfTrainingsRepo.save(typeOfTraining);
        subCathegories.getTypeOfTrainings().add(save);
        subCatRepo.save(subCathegories);

        return mapper.typeOfTrainingToDTO(save);

    }
}
