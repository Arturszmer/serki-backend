package com.example.serki.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Collections;
import java.util.List;

public class WorkshopsDTO {
    private final String name;
    private final String description;
    private final String imgUrl;
    private final List<SubCatDTO> list;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public WorkshopsDTO(String name, String description, String imgUrl, List<SubCatDTO> list) {
        this.name = name;
        this.description = description;
        this.imgUrl = imgUrl;
        this.list = list;
    }

    public List<SubCatDTO> getList() {
        return Collections.unmodifiableList(list);
    }

    public String getName() {
        return name;
      }

    public String getDescription() {
        return description;
    }

    public String getImgUrl() {
        return imgUrl;
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
