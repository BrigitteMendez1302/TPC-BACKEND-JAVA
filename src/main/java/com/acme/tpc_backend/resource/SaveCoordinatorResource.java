package com.acme.tpc_backend.resource;
public class SaveCoordinatorResource extends SaveUserResource{
    public Long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }

    private Long facultyId;
}