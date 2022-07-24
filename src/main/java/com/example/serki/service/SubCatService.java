package com.example.serki.service;

import com.example.serki.DTO.Mapper;
import com.example.serki.DTO.SubCatDTO;
import com.example.serki.models.SubCathegories;
import com.example.serki.repository.SubCatRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SubCatService {

    private final Mapper mapper;
    private final SubCatRepo subCatRepo;

    public SubCatService(Mapper mapper, SubCatRepo subCatRepo) {
        this.mapper = mapper;
        this.subCatRepo = subCatRepo;
    }

    public SubCatDTO addWorkshopSubCat(SubCatDTO subCatDTO){
        SubCathegories subCathegories = mapper.workshopsSubCatDTOtoWorkshopsSubCat(subCatDTO);
        SubCathegories save = subCatRepo.save(subCathegories);
        return mapper.workshopsSubCatToDTO(save);
    }



}
