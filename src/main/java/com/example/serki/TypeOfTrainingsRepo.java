package com.example.serki;

import com.example.serki.models.TypeOfTraining;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeOfTrainingsRepo extends JpaRepository<TypeOfTraining, Long> {
}
