package com.acme.tpc_backend.domain.service;

 import com.acme.tpc_backend.domain.model.NotificationType;
 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.Pageable;
 import org.springframework.http.ResponseEntity;
 
 public interface NotificationTypeService {
 	NotificationType getNotificationTypeById(Long notificationTypeId);
 	NotificationType createNotificationType(NotificationType notificationType);
 	NotificationType updateNotificationType(Long notificationTypeId, NotificationType notificationTypeDetails);
 	ResponseEntity<?> deleteNotificationType(Long notificationTypeId);
 	Page<NotificationType> getAllNotificationTypes(Pageable pageable);
 }