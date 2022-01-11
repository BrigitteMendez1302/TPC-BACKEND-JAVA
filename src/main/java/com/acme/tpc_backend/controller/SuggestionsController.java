package com.acme.tpc_backend.controller;

import com.acme.tpc_backend.domain.model.Suggestion;
import com.acme.tpc_backend.domain.service.SuggestionService;
import com.acme.tpc_backend.domain.service.UserService;
import com.acme.tpc_backend.resource.SaveSuggestionResource;
import com.acme.tpc_backend.resource.SuggestionResource;
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
public class SuggestionsController {
    @Autowired
    private SuggestionService suggestionService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper mapper;


    @Operation(summary = "Get All Suggestions", description = "get all suggestions",
            tags = {"suggestions"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "you've got all suggestions",
                    content = @Content(mediaType = "application/json"))})
    @GetMapping("/suggestions")
    public Page<SuggestionResource> getAllSuggestions(Pageable pageable) {
        Page<Suggestion> suggestionPage = suggestionService.getAllSuggestions(pageable);
        List<SuggestionResource> resources = suggestionPage.getContent().stream().map(
                this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }


    @Operation(summary = "Get All Suggestions by User", description = "Get All Suggestions by User",
            tags = {"suggestions"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "User got all these suggestions", content = @Content(mediaType = "application/json"))})
    @GetMapping("/users/{userId}/suggestions")
    public Page<SuggestionResource> getAllSuggestionsByUserId(@PathVariable Long userId, Pageable pageable){
        Page<Suggestion> SuggestionPage = suggestionService.getAllSuggestionsByUserId(userId, pageable);
        List<SuggestionResource> resources = SuggestionPage.getContent().stream().map(
                this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }


    @Operation(summary = "Get a suggestions by User", description = "Get a specific suggestions by user",
            tags = {"suggestions"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "One coordinator created (the one entered)", content = @Content(mediaType = "application/json"))})
    @GetMapping("/users/{userId}/suggestions/{suggestionId}")
    public SuggestionResource getSuggestionByIdAndUserId(@PathVariable Long userId,
                                                         @PathVariable Long suggestionId)
    {
        return convertToResource(suggestionService.getSuggestionByIdAndUserId(suggestionId,userId));
    }


    @Operation(summary = "Create Suggestion", description = "create a Suggestion",
            tags = {"suggestions"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "create a new suggestion", content = @Content(mediaType = "application/json"))})
    @PostMapping ("/users/{userId}/suggestions")
    public SuggestionResource createSuggestion(@PathVariable Long userId, @Valid @RequestBody SaveSuggestionResource resource){
        return convertToResource(suggestionService.createSuggestion(userId, convertToEntity(resource)));
    }


    @Operation(summary = "Update a Suggestion", description = "Update a specific Suggestion",
            tags = {"suggestions"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Suggestion was updated succesfully", content = @Content(mediaType = "application/json"))})
    @PutMapping("/users/{userId}/suggestions/{suggestionId}")
    public SuggestionResource updateSuggestion(@PathVariable Long userId, @PathVariable Long suggestionId, @Valid @RequestBody SaveSuggestionResource resource){
        return convertToResource(suggestionService.updateSuggestion(userId,suggestionId, convertToEntity(resource)));
    }


    @Operation(summary = "Delete a Suggestion", description = "Deletes a suggestion",
            tags = {"suggestions"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "One suggestion was deleted", content = @Content(mediaType = "application/json"))})
    @DeleteMapping("/users/{userId}/suggestions/{suggestionId}")
    public ResponseEntity<?> deleteSuggestion(@PathVariable Long userId, @PathVariable Long suggestionId){
        return suggestionService.deleteSuggestion(userId, suggestionId);
    }




    private Suggestion convertToEntity(SaveSuggestionResource resource) {
        Suggestion suggestion = new Suggestion();
        suggestion.setMessage(resource.getMessage());
        suggestion.setUser(userService.getUserById(resource.getUserId()));
        return suggestion;
    }

    private SuggestionResource convertToResource(Suggestion entity) {
        return mapper.map(entity, SuggestionResource.class);
    }
}
