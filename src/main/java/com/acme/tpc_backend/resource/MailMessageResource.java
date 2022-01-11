package com.acme.tpc_backend.resource;

import com.acme.tpc_backend.domain.model.AuditModel;

public class MailMessageResource extends AuditModel {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CoordinatorResource getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(CoordinatorResource coordinator) {
        this.coordinator = coordinator;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDocumentLink() {
        return documentLink;
    }

    public void setDocumentLink(String documentLink) {
        this.documentLink = documentLink;
    }

    private CoordinatorResource coordinator;
    private String message;
    private String documentLink;
}