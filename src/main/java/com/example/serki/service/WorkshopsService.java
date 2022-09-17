package com.example.serki.service;

import com.example.serki.DTO.Mapper;
import com.example.serki.DTO.SubCatDTO;
import com.example.serki.DTO.WorkshopsDTO;
import com.example.serki.models.Workshops;
import com.example.serki.repository.SubCatRepo;
import com.example.serki.repository.WorkshopsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class WorkshopsService {

    private final Mapper mapper;
    private final WorkshopsRepo workshopsRepo;

    @Autowired
    public WorkshopsService(Mapper mapper, WorkshopsRepo workshopsRepo) {
        this.mapper = mapper;
        this.workshopsRepo = workshopsRepo;
    }

    public List<WorkshopsDTO> workshopsList(){
        return workshopsRepo.findAll().stream()
                .map(mapper::workshopsToDTO)
                .collect(toList());
    }

    public List<SubCatDTO> getSubCatDTOS(String workshopName) {
        return workshopsList().stream()
                .filter(f -> f.getName().equals(workshopName))
                .findFirst()
                .map(WorkshopsDTO::getList)
                .get();
    }

    public WorkshopsDTO addWorkshop(WorkshopsDTO workshopsDTO){
        Workshops workshops = mapper.workshopsDTOToWorkshops(workshopsDTO);
        Workshops save = workshopsRepo.save(workshops);
        return mapper.workshopsToDTO(save);
    }
}