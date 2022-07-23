package com.example.serki.DTO;

import com.example.serki.DTO.WorkshopsDTO;
import com.example.serki.models.TypeOfTraining;
import com.example.serki.models.Workshops;
import com.example.serki.models.WorkshopsSubCathegories;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapper {

    public WorkshopsDTO workshopsToDTO(Workshops workshops){
        String name = workshops.getName();
        String descriptions = workshops.getDescription();
        return new WorkshopsDTO(name, descriptions);
    }
    public Workshops workshopsDTOToWorkshops(WorkshopsDTO workshopsDTO){
            return new Workshops(workshopsDTO.getName(), workshopsDTO.getDescription());
    }

    public WorkshopsSubCatDTO workshopsSubCatToDTO(WorkshopsSubCathegories workshopsSubCathegories){
        String name = workshopsSubCathegories.getName();
        List<TypeOfTraining> typeOfTrainings = workshopsSubCathegories.getTypeOfTrainings();
        return new WorkshopsSubCatDTO(name, typeOfTrainings);
    }

    public WorkshopsSubCathegories workshopsSubCatDTOtoWorkshopsSubCat(WorkshopsSubCatDTO workshopsSubCatDTO){
        return new WorkshopsSubCathegories(workshopsSubCatDTO.getName(), workshopsSubCatDTO.getTypeOfTrainings());
    }


}

