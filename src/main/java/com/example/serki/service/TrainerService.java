package com.example.serki.service;

import com.example.serki.DTO.Mapper;
import com.example.serki.DTO.TrainerDTO;
import com.example.serki.Exceptions.TrainerIsNotExist;
import com.example.serki.models.Trainer;
import com.example.serki.models.TrainingPeriod;
import com.example.serki.repository.TrainerRepo;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TrainerService {

    private final Mapper mapper;
    private final TrainerRepo trainerRepo;

    public TrainerService(Mapper mapper, TrainerRepo trainerRepo) {
        this.mapper = mapper;
        this.trainerRepo = trainerRepo;
    }

    public TrainerDTO addTrainer(TrainerDTO trainerDTO) {
        Trainer trainer = mapper.trainerDTOtoTrainer(trainerDTO);
        Trainer save = trainerRepo.save(trainer);
        return mapper.trainerToDTO(save);
    }

    public List<TrainerDTO> showAllTrainers() {
        return trainerRepo.findAll().stream()
                .map(mapper::trainerToDTO)
                .toList();
    }

    public List<LocalDate> getUnavailableDays(String name) {
        List<LocalDate> unavailableDays = new ArrayList<>();
        Trainer trainer = trainerRepo.findByName(name).orElseThrow();
        trainer.getUnavailableDays().forEach(days -> unavailableDays.add(days.getUnavailableDay()));
        return unavailableDays;
    }

    public void assignUnavailableDays(TrainingPeriod trainingPeriod, String trainerName) {

        Trainer trainer = trainerRepo.findByName(trainerName).orElseThrow(TrainerIsNotExist::new);
        LocalDate startTraining = trainingPeriod.getStartTraining();
        LocalDate endTraining = trainingPeriod.getEndTraining();
        long daysBetween = ChronoUnit.DAYS.between(startTraining, endTraining);
        System.out.println("XYX " + daysBetween);
        for (int i = 0; i <= daysBetween + 1; i++){

        }

    }
//    public void assignUnavailableDays(LocalDate trainingStart, double duration, String trainerName) {
//        Trainer trainer = trainerRepo.findByName(trainerName).orElseThrow();
//        int traingDays = duration % 8 > 0 ? (int) duration / 8 + 1: (int) duration / 8;
//        for (int i = 1; i <= traingDays; i++){
//            TrainerUnavailableDays trainerUnavailableDays = new TrainerUnavailableDays();
//            if (i > 1){
//                trainerUnavailableDays.setUnavailableDay(trainingStart.plusDays(i - 1));
//                trainer.assignUnavailableDays(trainerUnavailableDays);
//            } else {
//                trainerUnavailableDays.setUnavailableDay(trainingStart);
//                trainer.assignUnavailableDays(trainerUnavailableDays);
//            }
//        }
//        trainerRepo.save(trainer);
//    }
}