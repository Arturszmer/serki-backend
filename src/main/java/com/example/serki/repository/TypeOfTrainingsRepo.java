package com.example.serki.repository;

import com.example.serki.models.TypeOfTraining;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.util.Optional;

@Repository
public interface TypeOfTrainingsRepo extends JpaRepository<TypeOfTraining, Long> {

}
