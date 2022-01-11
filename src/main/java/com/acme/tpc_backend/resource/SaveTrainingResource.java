package com.acme.tpc_backend.resource;
public class SaveTrainingResource extends SaveMeetingResource{
    private Long coordinatorId;

    public Long getCoordinatorId() {
        return coordinatorId;
    }

    public void setCoordinatorId(Long coordinatorId) {
        this.coordinatorId = coordinatorId;
    }
}