package com.acme.tpc_backend;

import com.acme.tpc_backend.domain.model.Notification;
import com.acme.tpc_backend.domain.repository.NotificationRepository;
import com.acme.tpc_backend.domain.service.NotificationService;
import com.acme.tpc_backend.exception.ResourceNotFoundException;
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
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class NotificationServiceImplTest {
    @MockBean
    private NotificationRepository notificationRepository;
    @Autowired
    private NotificationService notificationService;

    @TestConfiguration
    static class NotificationServiceImplTestConfiguration {
        @Bean
        public NotificationService notificationService() {
            return new NotificationServiceImpl();
        }
    }

    @Test
    @DisplayName("When Get Notification By Id With Valid Id Returns Notification")
    public void whenGetNotificationByIdWithValidIdReturnsNotification() {
        Long id = 1L;
        String content = "Nueva notificación";

        Notification notification = new Notification()
                .setId(id)
                .setContent(content);

        when(notificationRepository.findById(id))
                .thenReturn(Optional.of(notification));

        Notification found = notificationService.getNotificationById(id);

        assertThat(found.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("When Get Notification By Id With Valid Id Returns ResourceNotFoundException")
    public void whenGetNotificationByIdWithInvalidIdReturnsResourceNotFoundException() {
        Long id = 1L;
        String template = "Resource %s not found for %s with value %s";

        when(notificationRepository.findById(id))
                .thenReturn(Optional.empty());

        String exceptedMessage = String.format(template, "Notification", "Id", id);

        Throwable exception = catchThrowable(() -> {
            Notification found = notificationService.getNotificationById(id);
        });

        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(exceptedMessage);
    }

    @Test
    @DisplayName("When Create Notification By Id With Valid Id Returns Notification")
    public void whenCreateNotificationReturnsNotification() {
        Long id = 1L;
        String content = "Nueva notificación";

        Notification notification = new Notification()
                .setId(id)
                .setContent(content);

        when(notificationRepository.save(notification)).thenReturn(notification);

        Notification found = notificationService.createNotification(notification);

        assertThat(found).isEqualTo(notification);
    }

    @Test
    @DisplayName("When Update Notification By Id With Valid Id Returns Notification")
    public void whenUpdateNotificationReturnsNotification() {
        Long id = 1L;
        String content = "Nueva notificación";

        Notification notification = new Notification()
                .setId(id)
                .setContent(content);

        String newContent = "Nueva notificación de que la sesión esta a 15 minutos de empezar";

        Notification expected = new Notification()
                .setContent(newContent);

        when(notificationRepository.findById(id))
                .thenReturn(Optional.of(notification));

        Notification found = notificationService.getNotificationById(id);

        found.setContent(expected.getContent());

        assertThat(found.getContent()).isEqualTo(expected.getContent());
    }

    @Test
    @DisplayName("When Delete Notification By Id With Valid Id Returns ResponseEntity")
    public void WhenDeleteNotificationReturnsResponseEntity() {
        Long id = 1L;
        String content = "Nueva notificación";

        Notification notification = new Notification()
                .setId(id)
                .setContent(content);

        when(notificationRepository.findById(id))
                .thenReturn(Optional.of(notification));

        ResponseEntity<?> deleted = notificationService.deleteNotification(id);

        assertThat(deleted).isInstanceOf(ResponseEntity.class);
    }
}