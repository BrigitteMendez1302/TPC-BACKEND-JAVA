package com.acme.tpc_backend.resource;
public class SaveStudentResource extends SaveUserResource{
    private int cycleNumber;
    private Long careerId;

    public int getCycleNumber() {
        return cycleNumber;
    }

    public void setCycleNumber(int cycleNumber) {
        this.cycleNumber = cycleNumber;
    }

    public Long getCareerId() {
        return careerId;
    }

    public void setCareerId(Long careerId) {
        this.careerId = careerId;
    }
}