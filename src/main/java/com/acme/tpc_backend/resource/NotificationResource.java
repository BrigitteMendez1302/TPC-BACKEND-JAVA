package com.acme.tpc_backend.resource;

import com.acme.tpc_backend.domain.model.AuditModel;

import java.util.Date;

public class NotificationResource extends AuditModel {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NotificationTypeResource getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationTypeResource notificationType) {
        this.notificationType = notificationType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    private Long id;
    private NotificationTypeResource notificationType;
    private String content;
    private String link;
    private Date sendDate;
}