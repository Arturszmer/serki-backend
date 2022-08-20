package com.example.serki.service;

import com.example.serki.DTO.Mapper;
import com.example.serki.DTO.WorkshopsDTO;
import com.example.serki.models.Workshops;
import com.example.serki.repository.SubCatRepo;
import com.example.serki.repository.WorkshopsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WorkshopsService {

    private final Mapper mapper;
    private final WorkshopsRepo workshopsRepo;
    private final SubCatRepo subCatRepo;
    @Autowired
    public WorkshopsService(Mapper mapper, WorkshopsRepo workshopsRepo, SubCatRepo subCatRepo) {
        this.mapper = mapper;
        this.workshopsRepo = workshopsRepo;
        this.subCatRepo = subCatRepo;
    }

    public List<Workshops> workshopsList(){
        return workshopsRepo.findAll();
    }

//    public WorkshopsDTO addWorkshop (Workshops workshops){
//        WorkshopsDTO workshops1 = mapper.workshopsToDTO(workshops);
//        workshopsRepo.save(workshops1);
//        return ;
//    }

    public Optional<Workshops> showSpecificWorkshop(String name){
        return workshopsRepo.findByName(name);
    }

    public WorkshopsDTO addWorkshop(WorkshopsDTO workshopsDTO){
        Workshops workshops = mapper.workshopsDTOToWorkshops(workshopsDTO);
        Workshops save = workshopsRepo.save(workshops);
        return mapper.workshopsToDTO(save);
    }

    public void addSubCatList(){
        subCatRepo.findAll();
    }

}
