package com.acme.tpc_backend.service;

import com.acme.tpc_backend.domain.model.Notification;
import com.acme.tpc_backend.domain.model.NotificationType;
import com.acme.tpc_backend.domain.repository.NotificationRepository;
import com.acme.tpc_backend.domain.service.NotificationService;
import com.acme.tpc_backend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {
    
    @Autowired
    private NotificationRepository notificationRepository;
    
    @Override
    public Notification getNotificationById(Long notificationId) {
        return notificationRepository.findById(notificationId)
                .orElseThrow(()-> new ResourceNotFoundException("Notification", "Id", notificationId));
    }

    @Override
    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public Notification updateNotification(Long notificationId, Notification notificationDetails) {
        return notificationRepository.findById(notificationId)
                .map(notification -> {
                    notification.setNotificationType(notificationDetails.getNotificationType());
                    notification.setContent(notificationDetails.getContent());
                    notification.setLink(notificationDetails.getLink());
                    notification.setSendDate(notificationDetails.getSendDate());
                    return notificationRepository.save(notification);
                }).orElseThrow(()-> new ResourceNotFoundException("Notification", "Id", notificationId));
    }

    @Override
    public ResponseEntity<?> deleteNotification(Long notificationId) {
        return notificationRepository.findById(notificationId)
                .map(notification -> {
                    notificationRepository.delete(notification);
                    return ResponseEntity.ok().build();
                }).orElseThrow(()->new ResourceNotFoundException("Notification", "Id", notificationId));
    }

    @Override
    public Page<Notification> getAllNotifications(Pageable pageable) {
        return notificationRepository.findAll(pageable);
    }
}
