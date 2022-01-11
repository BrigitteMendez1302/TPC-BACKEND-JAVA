package com.acme.tpc_backend;

import com.acme.tpc_backend.domain.model.MailMessage;
import com.acme.tpc_backend.domain.model.Notification;
import com.acme.tpc_backend.domain.repository.MailMessageRepository;
import com.acme.tpc_backend.domain.repository.NotificationRepository;
import com.acme.tpc_backend.domain.service.MailMessageService;
import com.acme.tpc_backend.domain.service.NotificationService;
import com.acme.tpc_backend.exception.ResourceNotFoundException;
import com.acme.tpc_backend.service.MailMessageServiceImpl;
import com.acme.tpc_backend.service.NotificationServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class MailMessageServiceImplTest {
    @MockBean
    private MailMessageRepository mailMessageRepository;
    @Autowired
    private MailMessageService mailMessageService;

    @TestConfiguration
    static class MailMessageServiceImplTestConfiguration {
        @Bean
        public MailMessageService mailMessageService() {
            return new MailMessageServiceImpl();
        }
    }

    @Test
    @DisplayName("when SaveMailMessage With Valid MailMessage Then Returns Success") //happy path
    public void whenSaveMailMessageWithValidMailMessageThenReturnsSuccess() {
        Long id = 1L;
        String messagex = "Hola a todos";
        MailMessage mailMessage = new MailMessage();
        mailMessage.setId(id);
        mailMessage.setMessage(messagex);
        when(mailMessageRepository.save(mailMessage))
                .thenReturn(mailMessage);
        MailMessage found = mailMessageService.createMailMessage(mailMessage);
        assertThat(found).isEqualTo(mailMessage);
    }

    @Test
    @DisplayName("when Get MailMessage By Id With Invalid Id Then Returns ResourceNotFoundException") //unhappy path
    public void whenGetCareerByIdWithInvalidIdThenReturnsResourceNotFoundException() {
        //Arrange
        Long id = 1L;
        String template = "Resource %s not found for %s with value %s";
        when(mailMessageRepository.findById(id)).thenReturn(Optional.empty());
        String exceptedMessage = String.format(template, "MailMessage", "Id", id);
        //Act
        Throwable exception = catchThrowable(() ->{
            MailMessage foundMailMessage = mailMessageService.getMailMessageById(id);
        });
        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(exceptedMessage);
    }
}
