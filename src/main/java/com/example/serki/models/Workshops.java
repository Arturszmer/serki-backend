package com.example.serki.models;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Workshops  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String description;
    private String imgUrl;
    //dodać mapped by i zobaczyć jakie tabele się wygenerują (na beldungu jest opis tej operacji)
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<SubCathegory> workshopsCathegories;

    public Workshops() {
    }

    public Workshops(String name, String description, String imgUrl) {
        this.name = name;
        this.description = description;
        this.imgUrl = imgUrl;
    }

    public Workshops(String name, String description, String imgUrl, List<SubCathegory> workshopsCathegories) {
        this.name = name;
        this.description = description;
        this.imgUrl = imgUrl;
        this.workshopsCathegories = workshopsCathegories;
    }

    public Workshops(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Workshops(String name, String description, List<SubCathegory> workshopsCathegories) {
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

    public List<SubCathegory> getWorkshopsCathegories() {
        return workshopsCathegories;
    }

    public void setWorkshopsCathegories(List<SubCathegory> workshopsCathegories) {
        this.workshopsCathegories = workshopsCathegories;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "Workshops{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", workshopsCathegories=" + workshopsCathegories +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Workshops workshops = (Workshops) o;
        return id == workshops.id && Objects.equals(name, workshops.name) && Objects.equals(description, workshops.description) && Objects.equals(imgUrl, workshops.imgUrl) && Objects.equals(workshopsCathegories, workshops.workshopsCathegories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, imgUrl, workshopsCathegories);
    }
}
