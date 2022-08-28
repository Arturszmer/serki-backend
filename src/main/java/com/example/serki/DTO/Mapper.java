package com.example.serki.DTO;

import com.example.serki.models.Trainer;
import com.example.serki.models.TypeOfTraining;
import com.example.serki.models.Workshops;
import com.example.serki.models.SubCathegory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapper {

    public WorkshopsDTO workshopsToDTO(Workshops workshops){
        String name = workshops.getName();
        String descriptions = workshops.getDescription();
        String imgUrl = workshops.getImgUrl();
        List<SubCatDTO> list = workshops.getWorkshopsCategories()
                .stream()
                .map(this::subCatToDTO)
                .collect(Collectors.toList());
        return new WorkshopsDTO(name, descriptions, imgUrl, list);
    }
    public Workshops workshopsDTOToWorkshops(WorkshopsDTO workshopsDTO){
            return new Workshops(workshopsDTO.getName(), workshopsDTO.getDescription(), workshopsDTO.getImgUrl(), workshopsDTO.getList()
                    .stream()
                    .map(this::subCatDTOtoSubCat)
                    .collect(Collectors.toList()));
    }

    public SubCatDTO subCatToDTO(SubCathegory subCathegory){
        String name = subCathegory.getName();
        List<TypeOfTrainingDTO> typeOfTrainings = subCathegory.getTypeOfTrainings()
                .stream()
                .map(this::typeOfTrainingToDTO)
                .collect(Collectors.toList());
        return new SubCatDTO(name, typeOfTrainings);
    }

    public SubCathegory subCatDTOtoSubCat(SubCatDTO subCatDTO){
        return new SubCathegory(subCatDTO.getName(), subCatDTO.getTypeOfTrainings()
                .stream()
                .map(this::typeOfTrainingDTOtoTypeOfTraining)
                .collect(Collectors.toList()));
    }


    public TypeOfTrainingDTO typeOfTrainingToDTO(TypeOfTraining typeOfTraining){
        String name = typeOfTraining.getName();
        double price = typeOfTraining.getPrice();
        double duration = typeOfTraining.getDuration();
        String description = typeOfTraining.getDescription();
        List<TrainerDTO> trainers = typeOfTraining.getTrainer()
                .stream()
                .map(this::trainerToDTO)
                .collect(Collectors.toList());
        return new TypeOfTrainingDTO(name, price, duration, description, trainers);
    }
    public TypeOfTraining typeOfTrainingDTOtoTypeOfTraining(TypeOfTrainingDTO typeOfTrainingDTO){
        return new TypeOfTraining(typeOfTrainingDTO.getName(),
                typeOfTrainingDTO.getPrice(),
                typeOfTrainingDTO.getDuration(),
                typeOfTrainingDTO.getDescription(),
                typeOfTrainingDTO.getTrainers()
                        .stream()
                        .map(this::trainerDTOtoTrainer)
                        .toList());
    }

    public TrainerDTO trainerToDTO(Trainer trainer){
        String name = trainer.getName();
        String bio = trainer.getBio();
        return new TrainerDTO(name, bio);
    }

    public Trainer trainerDTOtoTrainer(TrainerDTO trainerDTO){
        return new Trainer(trainerDTO.getName(), trainerDTO.getBio());
    }
}

