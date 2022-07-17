package com.example.serki;

import com.example.serki.models.Workshops;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("workshopsLayout")
public class WorkshopsController {
    WorkshopsService workshopsService;

    public WorkshopsController(WorkshopsService workshopsService) {
        this.workshopsService = workshopsService;
    }

//    @PostMapping("/add")
//    public ResponseEntity<WorkshopsDTO> addWorkshop (@RequestBody WorkshopsDTO workshops){
//        Workshops workshops1 = workshopsService.addWorkshop(workshops);
//        return new ResponseEntity<>(workshops1, HttpStatus.OK);
//    }

}
