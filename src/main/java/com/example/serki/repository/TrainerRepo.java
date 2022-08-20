package com.example.serki.repository;


import com.example.serki.models.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainerRepo extends JpaRepository <Trainer, Long> {

    Optional<Trainer> findByName(String name);
}
