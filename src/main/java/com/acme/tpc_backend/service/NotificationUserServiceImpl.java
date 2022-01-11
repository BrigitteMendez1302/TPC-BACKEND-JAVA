package com.acme.tpc_backend.service;

import com.acme.tpc_backend.domain.model.Notification;
import com.acme.tpc_backend.domain.model.NotificationUser;
import com.acme.tpc_backend.domain.model.User;
import com.acme.tpc_backend.domain.repository.NotificationRepository;
import com.acme.tpc_backend.domain.repository.NotificationUserRepository;
import com.acme.tpc_backend.domain.repository.UserRepository;
import com.acme.tpc_backend.domain.service.NotificationUserService;
import com.acme.tpc_backend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class NotificationUserServiceImpl implements NotificationUserService {

    @Autowired
    private NotificationUserRepository notificationUserRepository;

    @Override
    public NotificationUser getNotificationUserByNotificationIdAndUserId(Long notificationId, Long userId) {
        return notificationUserRepository.findByNotificationIdAndUserId(notificationId, userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
    }

    @Override
    public NotificationUser createNotificationUser(NotificationUser notificationUser) {
        return notificationUserRepository.save(notificationUser);
    }

    @Override
    public NotificationUser updateNotificationUser(Long notificationUserId, NotificationUser notificationUserDetails) {
        return notificationUserRepository.findById(notificationUserId)
                .map(notificationUser ->{
                    notificationUser.setNotification(notificationUserDetails.getNotification());
                    notificationUser.setUser(notificationUserDetails.getUser());
                    return notificationUserRepository.save(notificationUser);
                }).orElseThrow(()-> new ResourceNotFoundException("NotificationUser", "Id", notificationUserId));
    }

    @Override
    public ResponseEntity<?> deleteNotificationUser(Long notificationId, Long userId) {
        return notificationUserRepository.findByNotificationIdAndUserId(notificationId, userId)
                .map(notificationUser ->{
                    notificationUserRepository.removeByNotificationIdAndUserId(notificationId, userId);
                    return ResponseEntity.ok().build();
                }).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
    }
}