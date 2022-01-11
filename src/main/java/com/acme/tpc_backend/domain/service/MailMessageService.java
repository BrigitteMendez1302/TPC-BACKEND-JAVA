package com.acme.tpc_backend.domain.service;
 import com.acme.tpc_backend.domain.model.MailMessage;
 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.Pageable;
 import org.springframework.http.ResponseEntity;
 
 public interface MailMessageService {
 	MailMessage getMailMessageById(Long mailMessageId);
 	MailMessage createMailMessage(MailMessage mailMessage);
 	MailMessage updateMailMessage(Long MailMessageId, MailMessage mailMessageDetails);
 	ResponseEntity<?> deleteMailMessage(Long mailMessageId);
 }