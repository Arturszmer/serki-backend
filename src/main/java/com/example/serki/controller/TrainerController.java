package com.example.serki.controller;
import com.example.serki.DTO.TrainerDTO;
import com.example.serki.service.TrainerService;
import org.springframework.web.bind.annotation.*;
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
}