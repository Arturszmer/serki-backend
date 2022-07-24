package com.example.serki.DTO;

import com.example.serki.models.TypeOfTraining;
import com.example.serki.models.Workshops;
import com.example.serki.models.SubCathegories;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapper {

    public WorkshopsDTO workshopsToDTO(Workshops workshops){
        String name = workshops.getName();
        String descriptions = workshops.getDescription();
        String imgUrl = workshops.getImgUrl();
        List<SubCatDTO> list = workshops.getWorkshopsCathegories()
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

    public SubCatDTO workshopsSubCatToDTO(SubCathegories subCathegories){
        String name = subCathegories.getName();
        List<TypeOfTraining> typeOfTrainings = subCathegories.getTypeOfTrainings();
        return new SubCatDTO(name, typeOfTrainings);
    }

    public SubCathegories workshopsSubCatDTOtoWorkshopsSubCat(SubCatDTO subCatDTO){
        return new SubCathegories(subCatDTO.getName(), subCatDTO.getTypeOfTrainings());
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

