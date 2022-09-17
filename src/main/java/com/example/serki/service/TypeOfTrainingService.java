package com.example.serki.service;


import com.example.serki.DTO.*;
import com.example.serki.Exceptions.*;
import com.example.serki.models.*;
import com.example.serki.repository.*;
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
    private final TrainingPeriodRepo trainingPeriodRepo;

    public TypeOfTrainingService(TrainerRepo trainerRepo,
                                 Mapper mapper,
                                 TypeOfTrainingsRepo typeOfTrainingsRepo,
                                 SubCatRepo subCatRepo, TrainingPeriodRepo trainingPeriodRepo) {
        this.trainerRepo = trainerRepo;
        this.mapper = mapper;
        this.typeOfTrainingsRepo = typeOfTrainingsRepo;
        this.subCatRepo = subCatRepo;
        this.trainingPeriodRepo = trainingPeriodRepo;
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

        isTrainerAssigned(trainerAssignmentDTO, typeOfTraining);
        assignTrainer(trainer, typeOfTraining);
        typeOfTrainingsRepo.save(typeOfTraining);
    }

    private void assignTrainer(Trainer trainer, TypeOfTraining typeOfTraining) {
        typeOfTraining.assignTrainer(trainer);
    }

    private void isNameOfTrainingExist(TypeOfTrainingDTO typeOfTrainingDTO, SubCathegory subCathegory) {
        if (subCathegory.getTypeOfTrainings().stream()
                .anyMatch(subcat -> subcat.getName()
                        .equals(typeOfTrainingDTO.getName()))) {
            throw new NameAlreadyExistException();
        }
    }

    private void isTrainerAssigned(TrainerAssignmentDTO trainerAssignmentDTO, TypeOfTraining typeOfTraining) {
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

    public void addTrainingPeriod(TrainingPeriodDTO trainingPeriodDTO,
                                  String trainingName) {

            TypeOfTraining typeOfTraining = typeOfTrainingsRepo.findByName(trainingName)
                    .orElseThrow(TypeOfTrainingNotExist::new);

        isPeriodExist(trainingPeriodDTO, typeOfTraining);
        typeOfTraining.assignPeriod(mapper.trainingPeriodDTOtoTrainingPeriod(trainingPeriodDTO));
            typeOfTrainingsRepo.save(typeOfTraining);
    }

    private void isPeriodExist(TrainingPeriodDTO trainingPeriodDTO, TypeOfTraining typeOfTraining) {
        if (typeOfTraining.getTrainingPeriod()
                .stream()
                .anyMatch(trainingPeriod -> trainingPeriod.getStartTraining()
                        .equals(trainingPeriodDTO.getStartTraining()))){
            throw new TrainingPeriodExist();
        }
    }
}

