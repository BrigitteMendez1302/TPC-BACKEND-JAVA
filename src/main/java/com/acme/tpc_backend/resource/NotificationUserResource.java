package com.acme.tpc_backend.resource;

import com.acme.tpc_backend.domain.model.AuditModel;

public class NotificationUserResource extends AuditModel {
    public NotificationResource getNotification() {
        return notification;
    }

    public void setNotification(NotificationResource notification) {
        this.notification = notification;
    }

    public UserResource getUser() {
        return user;
    }

    public void setUser(UserResource user) {
        this.user = user;
    }

    private NotificationResource notification;
    private UserResource user;
}
