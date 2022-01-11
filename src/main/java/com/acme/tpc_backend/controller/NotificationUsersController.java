package com.acme.tpc_backend.controller;

import com.acme.tpc_backend.domain.model.NotificationUser;
import com.acme.tpc_backend.domain.service.NotificationService;
import com.acme.tpc_backend.domain.service.NotificationUserService;
import com.acme.tpc_backend.domain.service.UserService;
import com.acme.tpc_backend.resource.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class NotificationUsersController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private NotificationUserService notificationUserService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserService userService;

    @Operation(
            summary = "Get NotificationUser By Id",
            description = "Gets a notificationUser by a given Id",
            tags = {"notification-users"})
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "NotificationUser returned",
                    content = @Content(mediaType = "application/json"))})
    @GetMapping("/users/{userId}/notifications/{notificationId}")
    public NotificationUserResource etNotificationUserByNotificationIdAndUserId(@PathVariable Long notificationId, @PathVariable Long userId) {
        return convertToResource(notificationUserService.getNotificationUserByNotificationIdAndUserId(notificationId, userId));
    }

    @Operation(
            summary = "Create NotificationUser",
            description = "Allows to create a NotificationUser",
            tags = {"notification-users"})
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "NotificationUser created",
                    content = @Content(mediaType = "application/json"))})
    @PostMapping("/users/{userId}/notifications")
    public NotificationUserResource createNotificationUser(@Valid @RequestBody SaveNotificationUserResource resource) {
        NotificationUser notificationUser = convertToEntity(resource);
        return convertToResource(notificationUserService.createNotificationUser(notificationUser));
    }

    @Operation(
            summary = "Update NotificationUser",
            description = "Allows to update a NotificationUser",
            tags = {"notification-users"})
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "NotificationUser updated",
                    content = @Content(mediaType = "application/json"))})
    @PutMapping("/notificationusers/{notificationUserId}")
    public NotificationUserResource updateNotificationUser(@PathVariable Long notificationUserId, @Valid @RequestBody SaveNotificationUserResource resource){
        NotificationUser notificationUser = convertToEntity(resource);
        return convertToResource(notificationUserService.updateNotificationUser(notificationUserId, notificationUser));
    }

    @Operation(
            summary = "Delete NotificationUser",
            description = "Allows to delete a NotificationUser",
            tags = {"notification-users"})
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "NotificationUser deleted",
                    content = @Content(mediaType = "application/json"))})
    @DeleteMapping("/users/{userId}/notification/{notificationId}")
    public ResponseEntity<?> deleteNotificationUser(@PathVariable Long notificationId, @PathVariable Long userId) {
        return notificationUserService.deleteNotificationUser(notificationId, userId);
    }

    private NotificationUser convertToEntity(SaveNotificationUserResource resource) {
        NotificationUser notificationUser = new NotificationUser();
        notificationUser.setNotification(notificationService.getNotificationById(resource.getNotificationId()));
        notificationUser.setUser(userService.getUserById(resource.getUserId()));
        return notificationUser;
    }

    private NotificationUserResource convertToResource(NotificationUser entity) {
        return mapper.map(entity, NotificationUserResource.class);
    }
}
