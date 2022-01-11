package com.acme.tpc_backend.resource;

import com.acme.tpc_backend.domain.model.AuditModel;

public class AccountResource extends AuditModel {
    private Long id;
    private UniversityResource university;
    private String accountNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UniversityResource getUniversity() {
        return university;
    }

    public void setUniversity(UniversityResource university) {
        this.university = university;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;
}