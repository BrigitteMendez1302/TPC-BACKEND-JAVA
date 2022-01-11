package com.acme.tpc_backend.service;

import com.acme.tpc_backend.domain.model.NotificationType;
import com.acme.tpc_backend.domain.repository.NotificationTypeRepository;
import com.acme.tpc_backend.domain.service.NotificationTypeService;
import com.acme.tpc_backend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class NotificationTypeServiceImpl implements NotificationTypeService {
    @Autowired
    private NotificationTypeRepository notificationTypeRepository;

    @Override
    public NotificationType getNotificationTypeById(Long notificationTypeId) {
        return notificationTypeRepository.findById(notificationTypeId)
                .orElseThrow(()-> new ResourceNotFoundException("NotificationType", "Id", notificationTypeId));
    }

    @Override
    public NotificationType createNotificationType(NotificationType notificationType) {
        return notificationTypeRepository.save(notificationType);
    }

    @Override
    public NotificationType updateNotificationType(Long notificationTypeId, NotificationType notificationTypeDetails) {
        return notificationTypeRepository.findById(notificationTypeId)
                .map(notificationType -> {
                    notificationType.setDescription(notificationTypeDetails.getDescription());
                    return notificationTypeRepository.save(notificationType);
                }).orElseThrow(()-> new ResourceNotFoundException("NotificationType", "Id", notificationTypeId));
    }

    @Override
    public ResponseEntity<?> deleteNotificationType(Long notificationTypeId) {
        return notificationTypeRepository.findById(notificationTypeId)
                .map(notificationType -> {
                    notificationTypeRepository.delete(notificationType);
                    return ResponseEntity.ok().build();
                }).orElseThrow(()->new ResourceNotFoundException("NotificationType", "Id", notificationTypeId));
    }

    @Override
    public Page<NotificationType> getAllNotificationTypes(Pageable pageable) {
        return notificationTypeRepository.findAll(pageable);
    }
}
