package com.acme.tpc_backend.domain.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class NotificationUserKey implements Serializable {
    @Column(name = "notification_id")
    private Long notificationId;

    @Column(name = "user_id")
    private Long userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NotificationUserKey)) return false;
        NotificationUserKey that = (NotificationUserKey) o;
        return notificationId.equals(that.notificationId) && userId.equals(that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(notificationId, userId);
    }
}
