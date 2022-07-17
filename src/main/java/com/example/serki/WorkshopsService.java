package com.example.serki;

import com.example.serki.models.Workshops;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class WorkshopsService {


    private final WorkshopsRepo workshopsRepo;
    @Autowired
    public WorkshopsService(WorkshopsRepo workshopsRepo) {
        this.workshopsRepo = workshopsRepo;
    }

//    public WorkshopsDTO addWorkshop (WorkshopsDTO workshops){
//        WorkshopsDTO workshopsDTO
//        return workshopsRepo.save(workshops);
//    }

    public Optional<Workshops> showSpecificWorkshop(String name){
        return workshopsRepo.findByName(name);
    }

}
