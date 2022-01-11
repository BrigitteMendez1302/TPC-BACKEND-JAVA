package com.acme.tpc_backend.controller;

import com.acme.tpc_backend.domain.model.TrainingTutor;
import com.acme.tpc_backend.domain.service.TrainingService;
import com.acme.tpc_backend.domain.service.TrainingTutorService;
import com.acme.tpc_backend.domain.service.TutorService;
import com.acme.tpc_backend.resource.SaveTrainingTutorResource;
import com.acme.tpc_backend.resource.TrainingTutorResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class TrainingTutorsController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private TrainingTutorService trainingTutorService;

     @Autowired
    private TrainingService trainingService;

     @Autowired
    private TutorService tutorService;

    @Operation(summary = "Get TrainingTutor By id", description = "Gets a TrainingTutor by a given Id", tags = {"training-tutors"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "One TrainingTutor returned (the one that matches)", content = @Content(mediaType = "application/json"))})
    @GetMapping("/trainingTutor/{trainingTutorId}")
    public TrainingTutorResource getTrainingTutorById(@PathVariable Long trainingTutorId) {
        return convertToResource(trainingTutorService.getTrainingTutorById(trainingTutorId));
    }

    @Operation(summary = "Create TrainingTutor", description = "Create a TrainingTutor ", tags = {"training-tutors"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Created TrainingTutor", content = @Content(mediaType = "application/json"))})
    @PostMapping("/trainings/{trainingId}/tutors/{tutorId}")
    public TrainingTutorResource createTrainingTutor(@Valid @RequestBody SaveTrainingTutorResource resource) {
        return convertToResource(trainingTutorService.createTrainingTutor(convertToEntity(resource)));
    }

    @Operation(summary = "Create TrainingTutor", description = "Create a TrainingTutor ", tags = {"training-tutors"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Created TrainingTutor", content = @Content(mediaType = "application/json"))})
    @PutMapping("/trainingTutor/{trainingTutorId}")
    public TrainingTutorResource updateTrainingTutor(@PathVariable Long trainingTutorId, @Valid @RequestBody SaveTrainingTutorResource resource){
        return convertToResource(trainingTutorService.updateTrainingTutor(trainingTutorId, convertToEntity(resource)));
    }

    private TrainingTutor convertToEntity(SaveTrainingTutorResource resource) {
        TrainingTutor trainingTutor = new TrainingTutor();

        trainingTutor.setTutor(tutorService.getTutorById(resource.getTutorId()));
        trainingTutor.setAssistance(resource.isAssisance());
        return mapper.map(resource, TrainingTutor.class);
    }

    private TrainingTutorResource convertToResource(TrainingTutor entity) {
        return mapper.map(entity, TrainingTutorResource.class);
    }
}
