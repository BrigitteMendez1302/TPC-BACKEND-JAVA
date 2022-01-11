package com.acme.tpc_backend;

import com.acme.tpc_backend.domain.model.NotificationType;
import com.acme.tpc_backend.domain.repository.NotificationTypeRepository;
import com.acme.tpc_backend.domain.service.NotificationTypeService;
import com.acme.tpc_backend.exception.ResourceNotFoundException;
import com.acme.tpc_backend.service.NotificationTypeServiceImpl;
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
public class NotificationTypeServiceImplTest {
    @MockBean
    private NotificationTypeRepository notificationTypeRepository;
    @Autowired
    private NotificationTypeService notificationTypeService;

    @TestConfiguration
    static class NotificationTypeServiceImplTestConfiguration {
        @Bean
        public NotificationTypeService notificationTypeService() {
            return new NotificationTypeServiceImpl();
        }
    }

    @Test
    @DisplayName("When Get NotificationType By Id With Valid Id Returns NotificationType")
    public void whenGetNotificationTypeByIdWithValidIdReturnsNotificationType() {
        Long id = 1L;
        String description = "Recordatorio";

        NotificationType notificationType = new NotificationType()
                .setId(id)
                .setDescription(description);

        when(notificationTypeRepository.findById(id))
                .thenReturn(Optional.of(notificationType));

        NotificationType found = notificationTypeService.getNotificationTypeById(id);

        assertThat(found.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("When Get NotificationType By Id With Valid Id Returns ResourceNotFoundException")
    public void whenGetNotificationTypeByIdWithInvalidIdReturnsResourceNotFoundException() {
        Long id = 1L;
        String template = "Resource %s not found for %s with value %s";

        when(notificationTypeRepository.findById(id))
                .thenReturn(Optional.empty());

        String exceptedMessage = String.format(template, "NotificationType", "Id", id);

        Throwable exception = catchThrowable(() -> {
            NotificationType found = notificationTypeService.getNotificationTypeById(id);
        });

        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(exceptedMessage);
    }

    @Test
    @DisplayName("When Create NotificationType By Id With Valid Id Returns NotificationType")
    public void whenCreateNotificationTypeReturnsNotificationType() {
        Long id = 1L;
        String description = "Recordatorio";

        NotificationType notificationType = new NotificationType()
                .setId(id)
                .setDescription(description);

        when(notificationTypeRepository.save(notificationType)).thenReturn(notificationType);

        NotificationType found = notificationTypeService.createNotificationType(notificationType);

        assertThat(found).isEqualTo(notificationType);
    }

    @Test
    @DisplayName("When Update NotificationType By Id With Valid Id Returns NotificationType")
    public void whenUpdateNotificationTypeReturnsNotificationType() {
        Long id = 1L;
        String description = "Recordatorio";

        NotificationType notificationType = new NotificationType()
                .setId(id)
                .setDescription(description);

        String newDescription = "Recordatorio de que la sesión esta a 15 minutos de empezar";

        NotificationType expected = new NotificationType()
                .setDescription(newDescription);

        when(notificationTypeRepository.findById(id))
                .thenReturn(Optional.of(notificationType));

        NotificationType found = notificationTypeService.getNotificationTypeById(id);

        found.setDescription(expected.getDescription());

        assertThat(found.getDescription()).isEqualTo(expected.getDescription());
    }

    @Test
    @DisplayName("When Delete NotificationType By Id With Valid Id Returns ResponseEntity")
    public void WhenDeleteNotificationTypeReturnsResponseEntity() {
        Long id = 1L;
        String description = "Recordatorio";

        NotificationType notificationType = new NotificationType()
                .setId(id)
                .setDescription(description);

        when(notificationTypeRepository.findById(id))
                .thenReturn(Optional.of(notificationType));

        ResponseEntity<?> deleted = notificationTypeService.deleteNotificationType(id);

        assertThat(deleted).isInstanceOf(ResponseEntity.class);
    }
}
