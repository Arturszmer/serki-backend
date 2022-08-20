package com.example.serki.service;


import com.example.serki.DTO.Mapper;
import com.example.serki.DTO.TypeOfTrainingDTO;
import com.example.serki.Exceptions.NameAlreadyExistException;
import com.example.serki.Exceptions.SubCatNotExist;
import com.example.serki.models.SubCathegory;
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

        if(subCatRepo.findSubCathegoriesByName(subCatName).isEmpty()){
            throw new SubCatNotExist();
        }

        SubCathegory subCathegory = subCatRepo.findSubCathegoriesByName(subCatName).orElseThrow();

        if(subCathegory.getTypeOfTrainings().stream()
                .anyMatch(subcat -> subcat.getName()
                        .equals(typeOfTrainingDTO.getName()))){
            throw new NameAlreadyExistException();
        }
        TypeOfTraining typeOfTraining = mapper.typeOfTrainingDTOtoTypeOfTraining(typeOfTrainingDTO);
        TypeOfTraining saveJ = typeOfTrainingsRepo.save(typeOfTraining);
        subCathegory.getTypeOfTrainings().add(saveJ);
        subCatRepo.save(subCathegory);

        return mapper.typeOfTrainingToDTO(saveJ);

    }
}
