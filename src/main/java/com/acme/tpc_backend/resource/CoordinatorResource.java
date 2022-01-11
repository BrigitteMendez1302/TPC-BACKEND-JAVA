package com.acme.tpc_backend.resource;
public class CoordinatorResource extends UserResource{
    private FacultyResource faculty;

    public FacultyResource getFaculty() {
        return faculty;
    }

    public void setFaculty(FacultyResource faculty) {
        this.faculty = faculty;
    }
}