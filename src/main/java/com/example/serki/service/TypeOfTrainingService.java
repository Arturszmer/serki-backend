package com.example.serki.service;


import com.example.serki.DTO.*;
import com.example.serki.Exceptions.*;
import com.example.serki.models.*;
import com.example.serki.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
public class TypeOfTrainingService {

    private final TrainerRepo trainerRepo;
    private final Mapper mapper;
    private final TypeOfTrainingsRepo typeOfTrainingsRepo;
    private final SubCatRepo subCatRepo;
    private final TrainerService trainerService;

    public TypeOfTrainingService(TrainerRepo trainerRepo,
                                 Mapper mapper,
                                 TypeOfTrainingsRepo typeOfTrainingsRepo,
                                 SubCatRepo subCatRepo, TrainerService trainerService) {
        this.trainerRepo = trainerRepo;
        this.mapper = mapper;
        this.typeOfTrainingsRepo = typeOfTrainingsRepo;
        this.subCatRepo = subCatRepo;
        this.trainerService = trainerService;
    }

    public TypeOfTrainingDTO addTypeOfTraining(TypeOfTrainingDTO typeOfTrainingDTO, String subCatName) {

        SubCathegory subCathegory = subCatRepo.findSubCathegoriesByName(subCatName)
                .orElseThrow(SubCatNotExist::new);

        isNameOfTrainingExist(typeOfTrainingDTO, subCathegory);
        typeOfTrainingDTO.assignFrontId(typeOfTrainingDTO.getName(), subCatName);
        TypeOfTraining typeOfTraining = mapper.typeOfTrainingDTOtoTypeOfTraining(typeOfTrainingDTO);
        TypeOfTraining saveToRepo = typeOfTrainingsRepo.save(typeOfTraining);
        subCathegory.assignTypeOfTraining(saveToRepo);
        subCatRepo.save(subCathegory);

        return mapper.typeOfTrainingToDTO(saveToRepo);

    }

    public void addTrainerToTraining(TrainerAssignmentDTO trainerAssignmentDTO){

        Trainer trainer = trainerRepo.findByName(trainerAssignmentDTO.getTrainerName())
                .orElseThrow(TrainerIsNotExist::new);
        TypeOfTraining typeOfTraining = typeOfTrainingsRepo.findByFrontId(trainerAssignmentDTO.getTrainingId())
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
            throw new NameAlreadyExist();
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

    public void assignUnavailableDaysByDuration(LocalDate trainingStart, double duration, String trainerName) {
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

    public void addTrainingPeriod(PeriodDTO periodDTO,
                                  String trainingId) {

            TypeOfTraining typeOfTraining = typeOfTrainingsRepo.findByFrontId(trainingId)
                    .orElseThrow(TypeOfTrainingNotExist::new);

        isPeriodExist(periodDTO, typeOfTraining);
        typeOfTraining.assignPeriod(mapper.trainingPeriodDTOtoTrainingPeriod(periodDTO));
            typeOfTrainingsRepo.save(typeOfTraining);
    }

    private void isPeriodExist(PeriodDTO periodDTO, TypeOfTraining typeOfTraining) {
        if (typeOfTraining.getTrainingPeriod()
                .stream()
                .anyMatch(trainingPeriod -> trainingPeriod.getStartTraining()
                        .equals(periodDTO.getStartTraining()))){
            throw new TrainingPeriodExist();
        }
    }

    public void addPeriodAndTrainer(PeriodAndTrainerAssignDTO periodAndTrainerAssignDTO,
                                    String trainingId) {
        addTrainingPeriod(periodAndTrainerAssignDTO.getPeriodDTO(), trainingId);
        TypeOfTraining training = typeOfTrainingsRepo.findByFrontId(trainingId).orElseThrow();
        TypeOfTrainingDTO typeOfTrainingDTO = mapper.typeOfTrainingToDTO(training);
        trainerService.addTrainer(periodAndTrainerAssignDTO.getTrainerDTO());
        trainerService.assignUnavailableDays(periodAndTrainerAssignDTO.getPeriodDTO(), periodAndTrainerAssignDTO.getTrainerDTO().getName());
        TrainerAssignmentDTO trainerAssignmentDTO = new TrainerAssignmentDTO(periodAndTrainerAssignDTO.getTrainerDTO().getName(), typeOfTrainingDTO.getFrontId());
        addTrainerToTraining(trainerAssignmentDTO);
    }
}

