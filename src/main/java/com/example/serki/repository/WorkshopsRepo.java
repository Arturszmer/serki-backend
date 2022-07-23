package com.example.serki.repository;

import com.example.serki.models.Workshops;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkshopsRepo extends JpaRepository<Workshops, Long> {

    Optional<Workshops> findByName(String name);

    Optional<Workshops> findByImgUrl(String imgUrl);


}
