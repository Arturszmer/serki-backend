package com.example.serki;

import com.example.serki.models.Workshops;
import org.springframework.stereotype.Component;

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

}

