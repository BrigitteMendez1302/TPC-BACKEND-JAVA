package com.acme.tpc_backend.resource;
public class StudentResource extends UserResource{
    public int getCycleNumber() {
        return cycleNumber;
    }

    public void setCycleNumber(int cycleNumber) {
        this.cycleNumber = cycleNumber;
    }

    public CareerResource getCareer() {
        return career;
    }

    public void setCareer(CareerResource career) {
        this.career = career;
    }

    private int cycleNumber;
    private CareerResource career;
}