package com.example.serki.service;


import com.example.serki.DTO.Mapper;
import com.example.serki.DTO.TypeOfTrainingDTO;
import com.example.serki.models.TypeOfTraining;
import com.example.serki.repository.TypeOfTrainingsRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TypeOfTrainingService {

    private final Mapper mapper;
    private final TypeOfTrainingsRepo typeOfTrainingsRepo;

    public TypeOfTrainingService(Mapper mapper, TypeOfTrainingsRepo typeOfTrainingsRepo) {
        this.mapper = mapper;
        this.typeOfTrainingsRepo = typeOfTrainingsRepo;
    }

    public List<TypeOfTraining> typeOfTrainings(){
        return typeOfTrainingsRepo.findAll();
    }

    public Optional<TypeOfTraining> showSpecificTypeOfTraining(String name){
        return typeOfTrainingsRepo.findByName(name);
    }

    public TypeOfTrainingDTO addTypeOfTraining(TypeOfTrainingDTO typeOfTrainingDTO){
        TypeOfTraining typeOfTraining = mapper.typeOfTrainingDTOtoTypeOfTraining(typeOfTrainingDTO);
        TypeOfTraining save = typeOfTrainingsRepo.save(typeOfTraining);
        return mapper.typeOfTrainingToDTO(save);

    }
}
