package com.example.serki;

import com.example.serki.models.WorkshopsSubCathegories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkshopsSubCathegoriesRepo extends JpaRepository<WorkshopsSubCathegories, Long> {
}
