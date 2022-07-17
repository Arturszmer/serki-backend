package com.example.serki;

import com.example.serki.models.Workshops;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkshopsRepo extends JpaRepository<Workshops, Long> {

    Optional<Workshops> findByName(String name);
    String showDescription(String name);
}
