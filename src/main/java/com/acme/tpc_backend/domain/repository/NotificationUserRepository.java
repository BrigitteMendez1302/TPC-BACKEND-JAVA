package com.acme.tpc_backend.domain.repository;

import com.acme.tpc_backend.domain.model.NotificationUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotificationUserRepository extends JpaRepository<NotificationUser, Long> {
    Optional<NotificationUser> findByNotificationIdAndUserId(Long notificationId, Long userId);
    Optional<NotificationUser> removeByNotificationIdAndUserId(Long notificationId, Long userId);
}
