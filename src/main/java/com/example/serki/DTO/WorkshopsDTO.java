package com.example.serki.DTO;

import com.example.serki.models.WorkshopsSubCathegories;

import java.util.List;

public class WorkshopsDTO {
    private String name;
    private String description;
    private String imgUrl;
    private List<WorkshopsSubCatDTO> list;

    public WorkshopsDTO(String name, String description, String imgUrl, List<WorkshopsSubCatDTO> list) {
        this.name = name;
        this.description = description;
        this.imgUrl = imgUrl;
        this.list = list;
    }

    public List<WorkshopsSubCatDTO> getList() {
        return list;
    }

    public void setList(List<WorkshopsSubCatDTO> list) {
        this.list = list;
    }

    public WorkshopsDTO(String name, String description, String imgUrl) {
        this.name = name;
        this.description = description;
        this.imgUrl = imgUrl;
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

    @Override
    public String toString() {
        return "WorkshopsDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
