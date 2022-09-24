package com.example.serki.controller;
import com.example.serki.DTO.TrainerDTO;
import com.example.serki.DTO.PeriodDTO;
import com.example.serki.service.TrainerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/trainers")
class TrainerController {
    private final TrainerService trainerService;
    TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @PostMapping
    TrainerDTO addTrainer(@RequestBody TrainerDTO trainerDTO) {
        return trainerService.addTrainer(trainerDTO);
    }

    @GetMapping
    List<TrainerDTO> showTrainers() {
        return trainerService.showAllTrainers();
    }

    @GetMapping("/unavailableDays/{trainerName}")
    List<LocalDate> showTrainerUnavailableDay(@PathVariable String trainerName){
       return trainerService.getUnavailableDays(trainerName);
    }

    @PostMapping("/unavailableDaysAssign/{trainerName}")
    public ResponseEntity<Void> assignUnavailableDaysToTrainer(@RequestBody PeriodDTO periodDTO,
                                                               @PathVariable String trainerName){
        trainerService.assignUnavailableDays(periodDTO, trainerName);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}