package com.acme.tpc_backend.service;

import com.acme.tpc_backend.domain.model.MailMessage;
import com.acme.tpc_backend.domain.repository.MailMessageRepository;
import com.acme.tpc_backend.domain.service.MailMessageService;
import com.acme.tpc_backend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MailMessageServiceImpl implements MailMessageService {
    @Autowired
    private MailMessageRepository mailMessageRepository;

    @Override
    public MailMessage getMailMessageById(Long mailMessageId) {
        return mailMessageRepository.findById(mailMessageId)
                .orElseThrow(() -> new ResourceNotFoundException("MailMessage", "Id", mailMessageId));
    }

    @Override
    public MailMessage createMailMessage(MailMessage mailMessage) {
        return mailMessageRepository.save(mailMessage);
    }

    @Override
    public MailMessage updateMailMessage(Long MailMessageId, MailMessage mailMessageDetails) {
        return mailMessageRepository.findById(MailMessageId)
                .map(mailMessage -> {
                    mailMessage.setMessage(mailMessageDetails.getMessage());
                    return mailMessageRepository.save(mailMessage);
                }).orElseThrow(
                        ()-> new ResourceNotFoundException("MailMessage", "Id", MailMessageId));
    }

    @Override
    public ResponseEntity<?> deleteMailMessage(Long mailMessageId) {
        return mailMessageRepository.findById(mailMessageId)
                .map(mailMessage -> {
                    mailMessageRepository.delete(mailMessage);
                    return ResponseEntity.ok().build();
                }).orElseThrow(
                        ()-> new ResourceNotFoundException("MailMessage", "Id", mailMessageId));
    }
}
