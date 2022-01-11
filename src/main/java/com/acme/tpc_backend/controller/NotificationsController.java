package com.acme.tpc_backend.controller;

import com.acme.tpc_backend.domain.model.Notification;
import com.acme.tpc_backend.domain.model.NotificationType;
import com.acme.tpc_backend.domain.service.NotificationService;
import com.acme.tpc_backend.domain.service.NotificationTypeService;
import com.acme.tpc_backend.resource.NotificationResource;
import com.acme.tpc_backend.resource.NotificationTypeResource;
import com.acme.tpc_backend.resource.SaveNotificationResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.aspectj.weaver.ast.Not;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class NotificationsController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private NotificationTypeService notificationTypeService;

    @Operation(
            summary = "Get Notifications",
            description = "Get All Notifications by Pages",
            tags = {"notifications"})
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "All Notifications returned",
                    content = @Content(mediaType = "application/json"))})
    @GetMapping("/notifications")
    public Page<NotificationResource> getAllNotificationTypes(Pageable pageable) {
        Page<Notification> notificationTypesPage = notificationService.getAllNotifications(pageable);
        List<NotificationResource> resources = notificationTypesPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(
            summary = "Get Notification By Id",
            description = "Gets a notification by a given Id",
            tags = {"notifications"})
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Notification returned",
                    content = @Content(mediaType = "application/json"))})
    @GetMapping("/notifications/{notificationId}")
    public NotificationResource getNotificationById(@PathVariable Long notificationId) {
        return convertToResource(notificationService.getNotificationById(notificationId));
    }

    @Operation(
            summary = "Create Notification",
            description = "Allows to create a Notification",
            tags = {"notifications"})
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Notification created",
                    content = @Content(mediaType = "application/json"))})
    @PostMapping("/notifications")
    public NotificationResource createNotification(@Valid @RequestBody SaveNotificationResource resource) {
        Notification notification = convertToEntity(resource);
        return convertToResource(notificationService.createNotification(notification));
    }

    @Operation(
            summary = "Update Notification",
            description = "Allows to update a Notification",
            tags = {"notifications"})
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Notification updated",
                    content = @Content(mediaType = "application/json"))})
    @PutMapping("/notifications/{notificationId}")
    public NotificationResource updateNotification(@PathVariable Long notificationId, @Valid @RequestBody SaveNotificationResource resource) {
        Notification notification = convertToEntity(resource);
        return convertToResource(notificationService.updateNotification(notificationId, notification));
    }

    @Operation(
            summary = "Delete Notification",
            description = "Allows to delete a Notification",
            tags = {"notifications"})
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Notification deleted",
                    content = @Content(mediaType = "application/json"))})
    @DeleteMapping("/notifications/{notificationId}")
    public ResponseEntity<?> deleteNotification(@PathVariable Long notificationId) {
        return notificationService.deleteNotification(notificationId);
    }

    private Notification convertToEntity(SaveNotificationResource resource) {
        Notification notification = new Notification();
        notification.setContent(resource.getContent());
        notification.setNotificationType(notificationTypeService.getNotificationTypeById(resource.getNotificationTypeId()));
        notification.setLink(resource.getLink());
        notification.setSendDate(resource.getSendDate());
        return notification;
    }

    private NotificationResource convertToResource(Notification entity) {
        return mapper.map(entity, NotificationResource.class);
    }
}