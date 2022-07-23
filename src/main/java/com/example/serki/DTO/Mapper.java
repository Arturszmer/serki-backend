package com.example.serki.DTO;

import com.example.serki.models.TypeOfTraining;
import com.example.serki.models.Workshops;
import com.example.serki.models.WorkshopsSubCathegories;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapper {

    public WorkshopsDTO workshopsToDTO(Workshops workshops){
        String name = workshops.getName();
        String descriptions = workshops.getDescription();
        String imgUrl = workshops.getImgUrl();
        List<WorkshopsSubCatDTO> list = workshops.getWorkshopsCathegories()
                .stream()
                .map(this::workshopsSubCatToDTO)
                .collect(Collectors.toList());
        return new WorkshopsDTO(name, descriptions, imgUrl, list);
    }
    public Workshops workshopsDTOToWorkshops(WorkshopsDTO workshopsDTO){
            return new Workshops(workshopsDTO.getName(), workshopsDTO.getDescription(), workshopsDTO.getImgUrl(), workshopsDTO.getList()
                    .stream()
                    .map(this::workshopsSubCatDTOtoWorkshopsSubCat)
                    .collect(Collectors.toList()));
    }

    public WorkshopsSubCatDTO workshopsSubCatToDTO(WorkshopsSubCathegories workshopsSubCathegories){
        String name = workshopsSubCathegories.getName();
        List<TypeOfTraining> typeOfTrainings = workshopsSubCathegories.getTypeOfTrainings();
        return new WorkshopsSubCatDTO(name, typeOfTrainings);
    }

    public WorkshopsSubCathegories workshopsSubCatDTOtoWorkshopsSubCat(WorkshopsSubCatDTO workshopsSubCatDTO){
        return new WorkshopsSubCathegories(workshopsSubCatDTO.getName(), workshopsSubCatDTO.getTypeOfTrainings());
    }


    public TypeOfTrainingDTO typeOfTrainingToDTO(TypeOfTraining typeOfTraining){
        String name = typeOfTraining.getName();
        double price = typeOfTraining.getPrice();
        double duration = typeOfTraining.getDuration();
        String description = typeOfTraining.getDescription();
        return new TypeOfTrainingDTO(name, price, duration, description);
    }
    public TypeOfTraining typeOfTrainingDTOtoTypeOfTraining(TypeOfTrainingDTO typeOfTrainingDTO){
        return new TypeOfTraining(typeOfTrainingDTO.getName(),
                typeOfTrainingDTO.getPrice(),
                typeOfTrainingDTO.getDuration(),
                typeOfTrainingDTO.getDescription());
    }

}

