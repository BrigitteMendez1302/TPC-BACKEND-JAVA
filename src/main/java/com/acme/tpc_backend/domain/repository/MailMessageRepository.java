package com.acme.tpc_backend.domain.repository;

import com.acme.tpc_backend.domain.model.MailMessage; 
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MailMessageRepository extends JpaRepository<MailMessage, Long> {

}
