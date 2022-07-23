package com.example.serki.service;

import com.example.serki.DTO.Mapper;
import com.example.serki.DTO.WorkshopsDTO;
import com.example.serki.DTO.WorkshopsSubCatDTO;
import com.example.serki.models.WorkshopsSubCathegories;
import com.example.serki.repository.WorkshopsSubCathegoriesRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WorkshopSubCatService {

    private final Mapper mapper;
    private final WorkshopsSubCathegoriesRepo workshopsSubCathegoriesRepo;

    public WorkshopSubCatService(Mapper mapper, WorkshopsSubCathegoriesRepo workshopsSubCathegoriesRepo) {
        this.mapper = mapper;
        this.workshopsSubCathegoriesRepo = workshopsSubCathegoriesRepo;
    }

    public WorkshopsSubCatDTO addWorkshopSubCat(WorkshopsSubCatDTO workshopsSubCatDTO){
        WorkshopsSubCathegories workshopsSubCathegories = mapper.workshopsSubCatDTOtoWorkshopsSubCat(workshopsSubCatDTO);
        WorkshopsSubCathegories save = workshopsSubCathegoriesRepo.save(workshopsSubCathegories);
        return mapper.workshopsSubCatToDTO(save);
    }



}
