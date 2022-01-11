package com.acme.tpc_backend.resource;

public class SaveFacultyResource{
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public SaveFacultyResource setDescription(String description) {
        this.description = description;
        return this;
    }

    private String name;
    private String description;
}