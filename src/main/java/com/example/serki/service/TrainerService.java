package com.example.serki.service;

import com.example.serki.DTO.Mapper;
import com.example.serki.DTO.TrainerDTO;
import com.example.serki.models.Trainer;
import com.example.serki.repository.TrainerRepo;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
}