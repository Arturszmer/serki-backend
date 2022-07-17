package com.example.serki.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Workshops  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String description;
    @OneToMany
    private List<WorkshopsSubCathegories> workshopsCathegories;

    public Workshops() {
    }

    public Workshops(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Workshops(String name, String description, List<WorkshopsSubCathegories> workshopsCathegories) {
        this.name = name;
        this.description = description;
        this.workshopsCathegories = workshopsCathegories;
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

    public List<WorkshopsSubCathegories> getWorkshopsCathegories() {
        return workshopsCathegories;
    }

    public void setWorkshopsCathegories(List<WorkshopsSubCathegories> workshopsCathegories) {
        this.workshopsCathegories = workshopsCathegories;
    }

    @Override
    public String toString() {
        return "Workshops{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", workshopsCathegories=" + workshopsCathegories +
                '}';
    }
}
