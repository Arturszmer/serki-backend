package com.example.serki.controller;

import com.example.serki.DTO.Mapper;
import com.example.serki.DTO.WorkshopsDTO;
import com.example.serki.DTO.SubCatDTO;
import com.example.serki.Exceptions.NameAlreadyExistException;
import com.example.serki.Exceptions.WorkshopsNotExistException;
import com.example.serki.service.SubCatService;
import com.example.serki.service.WorkshopsService;
import jdk.jfr.Category;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("workshopsLayout")
public class WorkshopsController {
    private final WorkshopsService workshopsService;
    private final SubCatService subCatService;
    private final Mapper mapper;

    public WorkshopsController(WorkshopsService workshopsService, SubCatService subCatService, Mapper mapper) {
        this.workshopsService = workshopsService;
        this.subCatService = subCatService;
        this.mapper = mapper;
    }

    @GetMapping("/show")
    @ResponseBody
    public List<WorkshopsDTO> getWorkShops(){
        return workshopsService.workshopsList().stream()
                .map(mapper::workshopsToDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/add")
    @ResponseBody
        public WorkshopsDTO create(@RequestBody WorkshopsDTO workshopsDTO){
        WorkshopsDTO workshopsDTO1 = workshopsService.addWorkshop(workshopsDTO);
        return workshopsDTO1;
    }



}
