package com.example.serki.repository;

import com.example.serki.models.SubCathegories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCatRepo extends JpaRepository<SubCathegories, Long> {

    SubCathegories findSubCathegoriesByName(String name);
}
