package com.example.serki.models;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
public class SubCathegory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<TypeOfTraining> typeOfTrainings;

    public SubCathegory(String name, List<TypeOfTraining> typeOfTrainings) {
        this.name = name;
        this.typeOfTrainings = typeOfTrainings;
    }

    public SubCathegory() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TypeOfTraining> getTypeOfTrainings() {
        return Collections.unmodifiableList(typeOfTrainings);
    }

    public void setTypeOfTrainings(List<TypeOfTraining> typeOfTrainings) {
        this.typeOfTrainings = typeOfTrainings;
    }

    @Override
    public String toString() {
        return "WorkshopsSubCathegories{" +
                "name='" + name + '\'' +
                ", typeOfTrainings=" + typeOfTrainings +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubCathegory that = (SubCathegory) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(typeOfTrainings, that.typeOfTrainings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, typeOfTrainings);
    }

    public void assignTypeOfTraining(TypeOfTraining typeOfTraining){
        this.typeOfTrainings.add(typeOfTraining);
    }
}
