package com.acme.tpc_backend.resource;

import com.acme.tpc_backend.domain.model.AuditModel;

public class CareerResource extends AuditModel {
    private Long id;

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

    private String name;

    public FacultyResource getFaculty() {
        return faculty;
    }

    public void setFaculty(FacultyResource facultyResource) {
        this.faculty = facultyResource;
    }

    private FacultyResource faculty;
}