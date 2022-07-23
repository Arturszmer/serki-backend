package com.example.serki.DTO;

import com.example.serki.models.WorkshopsSubCathegories;

import java.util.List;

public class WorkshopsDTO {
    private String name;
    private String description;
    private String imgUrl;
    private List<WorkshopsSubCathegories> listOfSpecificWorkshops;


    public WorkshopsDTO(String name, String description, String imgUrl, List<WorkshopsSubCathegories> listOfSpecificWorkshops) {
        this.name = name;
        this.description = description;
        this.imgUrl = imgUrl;
        this.listOfSpecificWorkshops = listOfSpecificWorkshops;
    }

    public WorkshopsDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<WorkshopsSubCathegories> getListOfSpecificWorkshops() {
        return listOfSpecificWorkshops;
    }

    public void setListOfSpecificWorkshops(List<WorkshopsSubCathegories> listOfSpecificWorkshops) {
        this.listOfSpecificWorkshops = listOfSpecificWorkshops;
    }

    @Override
    public String toString() {
        return "WorkshopsDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", listOfSpecificWorkshops=" + listOfSpecificWorkshops +
                '}';
    }
}
