package com.acme.tpc_backend.resource;
public class TrainingResource extends MeetingResource{
    public CoordinatorResource getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(CoordinatorResource coordinator) {
        this.coordinator = coordinator;
    }

    private CoordinatorResource coordinator;
}