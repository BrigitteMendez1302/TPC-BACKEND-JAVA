package com.acme.tpc_backend.domain.service;
 import com.acme.tpc_backend.domain.model.Notification;
 import com.acme.tpc_backend.domain.model.NotificationType;
 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.Pageable;
 import org.springframework.http.ResponseEntity;
 
 public interface NotificationService {
 	Notification getNotificationById(Long notificationId);
 	Notification createNotification(Notification notification);
 	Notification updateNotification(Long notificationId, Notification notificationDetails);
 	ResponseEntity<?> deleteNotification(Long notificationId);
 	Page<Notification> getAllNotifications(Pageable pageable);
 }