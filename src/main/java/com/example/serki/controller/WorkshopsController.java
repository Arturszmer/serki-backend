package com.example.serki.controller;
import com.example.serki.DTO.*;
import com.example.serki.Exceptions.NameAlreadyExistException;
import com.example.serki.Exceptions.SubCatNotExist;
import com.example.serki.Exceptions.WorkshopsNotExistException;
import com.example.serki.service.SubCatService;
import com.example.serki.service.TypeOfTrainingService;
import com.example.serki.service.WorkshopsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/")
public class WorkshopsController {
    private final WorkshopsService workshopsService;
    private final SubCatService subCatService;
    private final TypeOfTrainingService typeOfTrainingService;
    public WorkshopsController(
            WorkshopsService workshopsService,
            SubCatService subCatService,
            TypeOfTrainingService typeOfTrainingService) {
        this.workshopsService = workshopsService;
        this.subCatService = subCatService;
        this.typeOfTrainingService = typeOfTrainingService;
    }

    @GetMapping("/workshops")
    @ResponseBody
    public List<WorkshopsDTO> getWorkShops(){
        return workshopsService.workshopsList();
    }

    @PostMapping("/workshops")
    @ResponseBody
    public WorkshopsDTO createWorkshop(@RequestBody WorkshopsDTO workshopsDTO){
        WorkshopsDTO created = workshopsService.addWorkshop(workshopsDTO);
        return created;
    }

    @GetMapping("workshops/{workshopName}/subCat")
    @ResponseBody
    public List<SubCatDTO> getWorkshopsSubCat(@PathVariable String workshopName) {
        return workshopsService.getSubCatDTOS(workshopName);
    }

    @PostMapping("/workshop/{workshopName}/subCat")
    public SubCatDTO createSubWorkshops(@RequestBody SubCatDTO subCatDTO,
                                        @PathVariable String workshopName)
            throws NameAlreadyExistException, WorkshopsNotExistException {
        return subCatService.addWorkshopSubCat(subCatDTO, workshopName);
    }



    @GetMapping("/workshop/{workshopName}/subCat/{subCatName}/typesOfTrainings")
    @ResponseBody
    public List<TypeOfTrainingDTO> getTypeOfSpecificTrainings(@PathVariable String subCatName, @PathVariable String workshopName){
        return subCatService.getTypeOfTrainingDTOS(subCatName, workshopName);
    }

    @PostMapping("/workshop/{workshopName}/subCat/{subCatName}/typesOfTrainings")
    public TypeOfTrainingDTO createTypeOfTraining(@RequestBody TypeOfTrainingDTO typeOfTrainingDTO,
                                                  @PathVariable String subCatName)
            throws NameAlreadyExistException, SubCatNotExist {
        return typeOfTrainingService.addTypeOfTraining(typeOfTrainingDTO, subCatName);
    }
    @PostMapping("workshops/trainerAssignment")
    public ResponseEntity<Void> addTrainerToTraining(@RequestBody TrainerAssignmentDTO trainerAssignmentDTO) {
        typeOfTrainingService.addTrainerToTraining(trainerAssignmentDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}