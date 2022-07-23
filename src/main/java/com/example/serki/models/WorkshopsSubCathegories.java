package com.example.serki.models;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class WorkshopsSubCathegories {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    @OneToMany(mappedBy = "name", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<TypeOfTraining> typeOfTrainings;

    public WorkshopsSubCathegories(String name, List<TypeOfTraining> typeOfTrainings) {
        this.name = name;
        this.typeOfTrainings = typeOfTrainings;
    }

    public WorkshopsSubCathegories() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TypeOfTraining> getTypeOfTrainings() {
        return typeOfTrainings;
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
        WorkshopsSubCathegories that = (WorkshopsSubCathegories) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(typeOfTrainings, that.typeOfTrainings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, typeOfTrainings);
    }
}
