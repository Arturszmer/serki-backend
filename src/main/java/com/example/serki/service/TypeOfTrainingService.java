package com.example.serki.service;


import com.example.serki.DTO.*;
import com.example.serki.Exceptions.*;
import com.example.serki.models.SubCathegory;
import com.example.serki.models.Trainer;
import com.example.serki.models.TrainerUnavailableDays;
import com.example.serki.models.TypeOfTraining;
import com.example.serki.repository.SubCatRepo;
import com.example.serki.repository.TrainerRepo;
import com.example.serki.repository.TrainerUnavailableDaysRepo;
import com.example.serki.repository.TypeOfTrainingsRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TypeOfTrainingService {

    private final TrainerRepo trainerRepo;
    private final Mapper mapper;
    private final TypeOfTrainingsRepo typeOfTrainingsRepo;
    private final SubCatRepo subCatRepo;
    private final TrainerUnavailableDaysRepo trainerUnavailableDaysRepo;

    public TypeOfTrainingService(TrainerRepo trainerRepo,
                                 Mapper mapper,
                                 TypeOfTrainingsRepo typeOfTrainingsRepo,
                                 SubCatRepo subCatRepo, TrainerUnavailableDaysRepo trainerUnavailableDaysRepo) {
        this.trainerRepo = trainerRepo;
        this.mapper = mapper;
        this.typeOfTrainingsRepo = typeOfTrainingsRepo;
        this.subCatRepo = subCatRepo;
        this.trainerUnavailableDaysRepo = trainerUnavailableDaysRepo;
    }

    public List<TypeOfTrainingDTO> typeOfTrainings() {
        return typeOfTrainingsRepo.findAll()
                .stream()
                .map(mapper::typeOfTrainingToDTO)
                .collect(Collectors.toList());
    }

    public TypeOfTrainingDTO addTypeOfTraining(TypeOfTrainingDTO typeOfTrainingDTO, String subCatName) {

        SubCathegory subCathegory = subCatRepo.findSubCathegoriesByName(subCatName)
                .orElseThrow(SubCatNotExist::new);

        isNameOfTrainingExist(typeOfTrainingDTO, subCathegory);
        TypeOfTraining typeOfTraining = mapper.typeOfTrainingDTOtoTypeOfTraining(typeOfTrainingDTO);
        TypeOfTraining saveToRepo = typeOfTrainingsRepo.save(typeOfTraining);
        subCathegory.getTypeOfTrainings().add(saveToRepo);
        subCatRepo.save(subCathegory);

        return mapper.typeOfTrainingToDTO(saveToRepo);

    }

    public void addTrainerToTraining(TrainerAssignmentDTO trainerAssignmentDTO){

        Trainer trainer = trainerRepo.findByName(trainerAssignmentDTO.getTrainerName())
                .orElseThrow(TrainerIsNotExist::new);
        TypeOfTraining typeOfTraining = typeOfTrainingsRepo.findByName(trainerAssignmentDTO.getTypeOfTrainingName())
                .orElseThrow(TypeOfTrainingNotExist::new);

        isTrainerAsigned(trainerAssignmentDTO, typeOfTraining);
        assignTrainer(trainer, typeOfTraining);
        typeOfTrainingsRepo.save(typeOfTraining);
    }

    private void assignTrainer(Trainer trainer, TypeOfTraining typeOfTraining) {
        typeOfTraining.assign(trainer);
    }

    private void isNameOfTrainingExist(TypeOfTrainingDTO typeOfTrainingDTO, SubCathegory subCathegory) {
        if (subCathegory.getTypeOfTrainings().stream()
                .anyMatch(subcat -> subcat.getName()
                        .equals(typeOfTrainingDTO.getName()))) {
            throw new NameAlreadyExistException();
        }
    }

    private void isTrainerAsigned(TrainerAssignmentDTO trainerAssignmentDTO, TypeOfTraining typeOfTraining) {
        if (typeOfTraining.getTrainer()
                .stream()
                .anyMatch(trainerName -> trainerName.getName()
                        .equals(trainerAssignmentDTO.getTrainerName()))){
            throw new TrainerIsAssigned();
        }
    }

    public void assignUnavailableDays(LocalDate trainingStart, double duration, String trainerName) {
        Trainer trainer = trainerRepo.findByName(trainerName).orElseThrow();
        int traingDays = duration % 8 > 0 ? (int) duration / 8 + 1: (int) duration / 8;
        for (int i = 1; i <= traingDays; i++){
            TrainerUnavailableDays trainerUnavailableDays = new TrainerUnavailableDays();
            if (i > 1){
                trainerUnavailableDays.setUnavailableDay(trainingStart.plusDays(i - 1));
                trainer.assignUnavailableDays(trainerUnavailableDays);
            } else {
                trainerUnavailableDays.setUnavailableDay(trainingStart);
                trainer.assignUnavailableDays(trainerUnavailableDays);
            }
        }
        trainerRepo.save(trainer);
    }
}

