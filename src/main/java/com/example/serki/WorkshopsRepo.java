package com.example.serki;

import com.example.serki.models.Workshops;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkshopsRepo extends JpaRepository<Workshops, Long> {

}
