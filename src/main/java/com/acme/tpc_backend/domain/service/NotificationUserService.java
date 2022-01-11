package com.acme.tpc_backend.domain.service;

import com.acme.tpc_backend.domain.model.Notification;
import com.acme.tpc_backend.domain.model.NotificationUser;
import org.springframework.http.ResponseEntity;

public interface NotificationUserService {
    NotificationUser getNotificationUserByNotificationIdAndUserId(Long notificationId, Long userId);
    NotificationUser createNotificationUser(NotificationUser notificationUser);
    NotificationUser updateNotificationUser(Long notificationUserId, NotificationUser notificationUserDetails);
    ResponseEntity<?> deleteNotificationUser(Long notificationId, Long userId);
}
