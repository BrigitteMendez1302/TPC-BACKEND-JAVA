package com.acme.tpc_backend.resource;

import com.acme.tpc_backend.domain.model.AuditModel;

public class SuggestionResource extends AuditModel {
    private Long id;
    private String message;
    private UserResource user;

    public Long getId() {
        return id;
    }

    public SuggestionResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public SuggestionResource setMessage(String message) {
        this.message = message;
        return this;
    }

    public UserResource getUser() {
        return user;
    }

    public SuggestionResource setUser(UserResource user) {
        this.user = user;
        return this;
    }


}