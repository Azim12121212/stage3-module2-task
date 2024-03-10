package com.mjc.school.service.dto;

import java.util.Set;

public class TagDtoResponse {
    private Long id;
    private String name;
    private Set<NewsDtoResponse> newsDtoResponseSet;

    public TagDtoResponse() {
    }

    public TagDtoResponse(String name) {
        this.name = name;
    }

    public TagDtoResponse(Long id, String name, Set<NewsDtoResponse> newsDtoResponseSet) {
        this.id = id;
        this.name = name;
        this.newsDtoResponseSet = newsDtoResponseSet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<NewsDtoResponse> getNewsDtoResponseSet() {
        return newsDtoResponseSet;
    }

    public void setNewsDtoResponseSet(Set<NewsDtoResponse> newsDtoResponseSet) {
        this.newsDtoResponseSet = newsDtoResponseSet;
    }

    @Override
    public String toString() {
        return "TagDtoResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}