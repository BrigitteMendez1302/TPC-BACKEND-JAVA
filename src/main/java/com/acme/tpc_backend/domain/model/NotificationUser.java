package com.acme.tpc_backend.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "notification_users")
public class NotificationUser extends AuditModel{
    public Notification getNotification() {
        return notification;
    }

    public NotificationUser setNotification(Notification notification) {
        this.notification = notification;
        return this;
    }

    public User getUser() {
        return user;
    }

    public NotificationUser setUser(User user) {
        this.user = user;
        return this;
    }

    @EmbeddedId
    private NotificationUserKey id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @MapsId("notificationId")
    @JoinColumn(name = "notification_id", nullable = false)
    private Notification notification;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
