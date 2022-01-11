package com.acme.tpc_backend.controller;

import com.acme.tpc_backend.domain.model.NotificationType;
import com.acme.tpc_backend.domain.service.NotificationTypeService;
import com.acme.tpc_backend.resource.NotificationTypeResource;
import com.acme.tpc_backend.resource.SaveNotificationTypeResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
public class NotificationTypesController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private NotificationTypeService notificationTypeService;

    @Operation(
            summary = "Get NotificationTypes",
            description = "Get All NotificationTypes by Pages",
            tags = {"notification-types"})
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "All NotificationTypes returned",
                    content = @Content(mediaType = "application/json"))})
    @GetMapping("/notificationtypes")
    public Page<NotificationTypeResource> getAllNotificationTypes(Pageable pageable) {
        Page<NotificationType> notificationTypesPage = notificationTypeService.getAllNotificationTypes(pageable);
        List<NotificationTypeResource> resources = notificationTypesPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(
            summary = "Get NotificationType by Id",
            description = "Gets a NotificationType by given Id",
            tags = {"notification-types"})
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "NotificationType returned",
                    content = @Content(mediaType = "application/json"))})
    @GetMapping("/notificationtypes/{notificationtypeId}")
    public NotificationTypeResource getNotificationTypeById(@PathVariable Long notificationtypeId) {
        return convertToResource(notificationTypeService.getNotificationTypeById(notificationtypeId));
    }

    @Operation(
            summary = "Create NotificationType",
            description = "Allows to create a NotificationType",
            tags = {"notification-types"})
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "NotificationType created",
                    content = @Content(mediaType = "application/json"))})
    @PostMapping("/notificationtypes")
    public NotificationTypeResource createNotificationType(@Valid @RequestBody SaveNotificationTypeResource resource) {
        NotificationType notificationType = convertToEntity(resource);
        return convertToResource(notificationTypeService.createNotificationType(notificationType));
    }

    @Operation(
            summary = "Update NotificationType",
            description = "Allows to update a NotificationType",
            tags = {"notification-types"})
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "NotificationType updated",
                    content = @Content(mediaType = "application/json"))})
    @PutMapping("/notificationtypes/{notificationtypeId}")
    public NotificationTypeResource updateNotificationType(@PathVariable Long notificationtypeId, @Valid @RequestBody SaveNotificationTypeResource resource) {
        NotificationType notificationType = convertToEntity(resource);
        return convertToResource(notificationTypeService.updateNotificationType(notificationtypeId, notificationType));
    }

    @Operation(
            summary = "Delete NotificationType",
            description = "Allows to delete a NotificationType",
            tags = {"notification-types"})
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "NotificationType deleted",
                    content = @Content(mediaType = "application/json"))})
    @DeleteMapping("/notificationtypes/{notificationtypeId}")
    public ResponseEntity<?> deleteNotificationType(@PathVariable Long notificationtypeId) {
        return notificationTypeService.deleteNotificationType(notificationtypeId);
    }

    private NotificationType convertToEntity(SaveNotificationTypeResource resource) {
        NotificationType notificationType = new NotificationType();
        notificationType.setDescription(resource.getDescription());
        return notificationType;
    }

    private NotificationTypeResource convertToResource(NotificationType entity) {
        return mapper.map(entity, NotificationTypeResource.class);
    }
}
