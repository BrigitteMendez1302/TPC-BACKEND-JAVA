package com.acme.tpc_backend.domain.repository;

import com.acme.tpc_backend.domain.model.NotificationType; 
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotificationTypeRepository extends JpaRepository<NotificationType, Long> {

}
