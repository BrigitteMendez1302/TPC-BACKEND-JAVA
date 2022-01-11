package com.acme.tpc_backend.domain.repository;

import com.acme.tpc_backend.domain.model.Notification; 
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
