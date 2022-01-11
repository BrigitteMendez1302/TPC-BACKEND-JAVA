package com.acme.tpc_backend;

import com.acme.tpc_backend.domain.model.Notification;
import com.acme.tpc_backend.domain.model.NotificationUser;
import com.acme.tpc_backend.domain.model.User;
import com.acme.tpc_backend.domain.repository.NotificationUserRepository;
import com.acme.tpc_backend.domain.service.NotificationUserService;
import com.acme.tpc_backend.exception.ResourceNotFoundException;
import com.acme.tpc_backend.service.NotificationUserServiceImpl;
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
public class NotificationUserServiceImplTest {
    @MockBean
    private NotificationUserRepository notificationUserRepository;
    @Autowired
    private NotificationUserService notificationUserService;

    @TestConfiguration
    static class NotificationUserServiceImplTestConfiguration {
        @Bean
        public NotificationUserService notificationUserService() {
            return new NotificationUserServiceImpl();
        }
    }

    @Test
    @DisplayName("When Get NotificationUser By Id With Valid Id Returns NotificationUser")
    public void whenGetNotificationUserByIdWithValidIdReturnsNotificationUser() {
        Long notificationId = 1L;
        Long userId = 1L;

        Notification notification = new Notification()
                .setId(notificationId);

        User user = new User()
                .setId(userId);

        NotificationUser notificationUser = new NotificationUser()
                .setNotification(notification)
                .setUser(user);

        when(notificationUserRepository.findByNotificationIdAndUserId(notificationId, userId))
                .thenReturn(Optional.of(notificationUser));

        NotificationUser found = notificationUserService.getNotificationUserByNotificationIdAndUserId(notificationId, userId);

        assertThat(found.getNotification().getId()).isEqualTo(notificationId);
        assertThat(found.getUser().getId()).isEqualTo(userId);
    }

    @Test
    @DisplayName("When Get NotificationUser By Id With Valid Id Returns ResourceNotFoundException")
    public void whenGetNotificationUserByIdWithInvalidIdReturnsResourceNotFoundException() {
        Long notificationId = 1L;
        Long userId = 1L;
        String template = "Resource %s not found for %s with value %s";

        when(notificationUserRepository.findByNotificationIdAndUserId(notificationId, userId))
                .thenReturn(Optional.empty());

        String exceptedMessage = String.format(template, "User", "Id", userId);

        Throwable exception = catchThrowable(() -> {
            NotificationUser found = notificationUserService.getNotificationUserByNotificationIdAndUserId(notificationId, userId);
        });

        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(exceptedMessage);
    }

    @Test
    @DisplayName("When Create NotificationUser By Id With Valid Id Returns NotificationUser")
    public void whenCreateNotificationUserReturnsNotificationUser() {
        Long notificationId = 1L;
        Long userId = 1L;

        Notification notification = new Notification()
                .setId(notificationId);

        User user = new User()
                .setId(userId);

        NotificationUser notificationUser = new NotificationUser()
                .setNotification(notification)
                .setUser(user);

        when(notificationUserRepository.save(notificationUser)).thenReturn(notificationUser);

        NotificationUser found = notificationUserService.createNotificationUser(notificationUser);

        assertThat(found).isEqualTo(notificationUser);
    }

    @Test
    @DisplayName("When Update NotificationUser By Id With Valid Id Returns NotificationUser")
    public void whenUpdateNotificationUserReturnsNotificationUser() {
        Long notificationId = 1L;
        Long userId = 1L;

        Notification notification = new Notification()
                .setId(notificationId);

        User user = new User()
                .setId(userId);

        NotificationUser notificationUser = new NotificationUser()
                .setNotification(notification)
                .setUser(user);

        //expected
        Notification newNotification = new Notification();
        User newUser = new User();

        NotificationUser expected = new NotificationUser()
                .setNotification(newNotification)
                .setUser(newUser);

        when(notificationUserRepository.findByNotificationIdAndUserId(notificationId, userId))
                .thenReturn(Optional.of(notificationUser));

        NotificationUser found = notificationUserService.getNotificationUserByNotificationIdAndUserId(notificationId, userId);

        found.setNotification(expected.getNotification());
        found.setUser(expected.getUser());

        assertThat(found.getNotification()).isEqualTo(expected.getNotification());
        assertThat(found.getUser()).isEqualTo(expected.getUser());
    }

    @Test
    @DisplayName("When Delete NotificationUser By Id With Valid Id Returns ResponseEntity")
    public void WhenDeleteNotificationUserReturnsResponseEntity() {
        Long notificationId = 1L;
        Long userId = 1L;

        Notification notification = new Notification()
                .setId(notificationId);

        User user = new User()
                .setId(userId);

        NotificationUser notificationUser = new NotificationUser()
                .setNotification(notification)
                .setUser(user);

        when(notificationUserRepository.findByNotificationIdAndUserId(notificationId, userId))
                .thenReturn(Optional.of(notificationUser));

        ResponseEntity<?> deleted = notificationUserService.deleteNotificationUser(notificationId, userId);

        assertThat(deleted).isInstanceOf(ResponseEntity.class);
    }
}