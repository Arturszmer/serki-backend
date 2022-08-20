package com.example.serki.repository;

import com.example.serki.models.SubCathegory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubCatRepo extends JpaRepository<SubCathegory, Long> {

    Optional<SubCathegory> findSubCathegoriesByName(String name);
}
