package com.acme.tpc_backend.resource;
public class TutorResource extends UserResource{
    public FacultyResource getFaculty() {
        return faculty;
    }

    public void setFaculty(FacultyResource faculty) {
        this.faculty = faculty;
    }

    private FacultyResource faculty;
}