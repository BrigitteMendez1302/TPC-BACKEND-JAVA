package com.acme.tpc_backend.resource;
public class SaveTrainingTutorResource{
    private Long tutorId;
    private Long trainingId;

    public Long getTutorId() {
        return tutorId;
    }

    public void setTutorId(Long tutorId) {
        this.tutorId = tutorId;
    }

    public Long getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(Long trainingId) {
        this.trainingId = trainingId;
    }

    public boolean isAssisance() {
        return assisance;
    }

    public void setAssisance(boolean assisance) {
        this.assisance = assisance;
    }

    private boolean assisance;
}