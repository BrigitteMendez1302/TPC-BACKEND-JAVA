package com.acme.tpc_backend.controller;

import com.acme.tpc_backend.domain.model.MailMessage;
import com.acme.tpc_backend.domain.service.CoordinatorService;
import com.acme.tpc_backend.domain.service.MailMessageService;
import com.acme.tpc_backend.resource.MailMessageResource;
import com.acme.tpc_backend.resource.SaveMailMessageResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;
import javax.validation.Valid;
@RestController
@RequestMapping("/api")
public class MailMessagesController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private MailMessageService mailMessageService;

    @Autowired
    private CoordinatorService coordinatorService;

    @Operation(summary = "Get MailMessage By id", description = "Gets an emailMessage by a given Id", tags = {"messages"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "One MailMessage returned (the one that matches)", content = @Content(mediaType = "application/json"))})
    @GetMapping("/messages/{messageId}")
    public MailMessageResource getMailMessageById(@PathVariable Long mailMessageId) {
        return convertToResource(mailMessageService.getMailMessageById(mailMessageId));
    }

    @Operation(summary = "Create MailMessage", description = "Create an emailMessage", tags = {"messages"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Create MailMessage", content = @Content(mediaType = "application/json"))})
    @PostMapping("/messages")
    public MailMessageResource createMailMessage(@Valid @RequestBody SaveMailMessageResource resource) {
        return convertToResource(mailMessageService.createMailMessage(convertToEntity(resource)));
    }

    @Operation(summary = "Update MailMessage", description = "Update an emailMessage", tags = {"messages"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Update MailMessage", content = @Content(mediaType = "application/json"))})
    @PutMapping("/messages/{messageId}")
    public MailMessageResource updateMailMessage(@PathVariable Long mailMessageId, @Valid @RequestBody SaveMailMessageResource resource) {
        return convertToResource(mailMessageService.updateMailMessage(mailMessageId, convertToEntity(resource)));
    }

    private MailMessage convertToEntity(SaveMailMessageResource resource) {
        MailMessage mailMessage = new MailMessage();
        mailMessage.setMessage(resource.getMessage());
        mailMessage.setCoordinator(coordinatorService.getCoordinatorById(resource.getCoordinatorId()));
        mailMessage.setDocumentLink(resource.getDocumentLink());
       return mailMessage;
    }

    private MailMessageResource convertToResource(MailMessage entity) {
        return mapper.map(entity, MailMessageResource.class);
    }
}
