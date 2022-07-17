package com.example.serki;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WorkshopsService {


    private final WorkshopsRepo workshopsRepo;
    @Autowired
    public WorkshopsService(WorkshopsRepo workshopsRepo) {
        this.workshopsRepo = workshopsRepo;
    }
}
