package com.acme.tpc_backend.resource;

import com.acme.tpc_backend.domain.model.AuditModel;

public class TrainingTutorResource extends AuditModel {
    private TutorResource tutor;

    public TutorResource getTutor() {
        return tutor;
    }

    public void setTutor(TutorResource tutor) {
        this.tutor = tutor;
    }

    public TrainingResource getTraining() {
        return training;
    }

    public void setTraining(TrainingResource training) {
        this.training = training;
    }

    public boolean isAssisance() {
        return assisance;
    }

    public void setAssisance(boolean assisance) {
        this.assisance = assisance;
    }

    private TrainingResource training;
    private boolean assisance;
}