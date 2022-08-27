package com.example.serki.service;


import com.example.serki.DTO.*;
import com.example.serki.Exceptions.*;
import com.example.serki.models.SubCathegory;
import com.example.serki.models.Trainer;
import com.example.serki.models.TypeOfTraining;
import com.example.serki.repository.SubCatRepo;
import com.example.serki.repository.TypeOfTrainingsRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TypeOfTrainingService {

    private final Mapper mapper;
    private final TypeOfTrainingsRepo typeOfTrainingsRepo;
    private final SubCatRepo subCatRepo;

    public TypeOfTrainingService(TrainerRepo trainerRepo,
                                 Mapper mapper,
                                 TypeOfTrainingsRepo typeOfTrainingsRepo,
                                 SubCatRepo subCatRepo) {
        this.trainerRepo = trainerRepo;
        this.mapper = mapper;
        this.typeOfTrainingsRepo = typeOfTrainingsRepo;
        this.subCatRepo = subCatRepo;
    }

    public List<TypeOfTrainingDTO> typeOfTrainings(){
        return typeOfTrainingsRepo.findAll()
                .stream()
                .map(mapper::typeOfTrainingToDTO)
                .collect(Collectors.toList());
    }

    public TypeOfTrainingDTO addTypeOfTraining(TypeOfTrainingDTO typeOfTrainingDTO, String subCatName){

        SubCathegory subCathegory = subCatRepo.findSubCathegoriesByName(subCatName)
                                                .orElseThrow(SubCatNotExist::new);

        if(subCathegory.getTypeOfTrainings().stream()
                .anyMatch(subcat -> subcat.getName()
                        .equals(typeOfTrainingDTO.getName()))){
            throw new NameAlreadyExistException();
        }
        TypeOfTraining typeOfTraining = mapper.typeOfTrainingDTOtoTypeOfTraining(typeOfTrainingDTO);
        TypeOfTraining saveToRepo = typeOfTrainingsRepo.save(typeOfTraining);
        subCathegory.getTypeOfTrainings().add(saveToRepo);
        subCatRepo.save(subCathegory);

        return mapper.typeOfTrainingToDTO(saveToRepo);

    }
}
