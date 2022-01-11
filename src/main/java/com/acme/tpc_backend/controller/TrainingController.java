package com.acme.tpc_backend.controller;
import com.acme.tpc_backend.domain.model.Lesson;
import com.acme.tpc_backend.domain.model.Training;
import com.acme.tpc_backend.domain.service.CoordinatorService;
import com.acme.tpc_backend.domain.service.LessonService;
import com.acme.tpc_backend.domain.service.TrainingService;
import com.acme.tpc_backend.resource.LessonResource;
import com.acme.tpc_backend.resource.SaveLessonResource;
import com.acme.tpc_backend.resource.SaveTrainingResource;
import com.acme.tpc_backend.resource.TrainingResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TrainingController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private TrainingService trainingService;
    @Autowired
    private CoordinatorService coordinatorService;

    @Operation(summary = "List trainings", description = "Lists trainings", tags = {"trainings"})
    @GetMapping("/trainings")
    public Page<TrainingResource> getAllTrainings(Pageable pageable) {
        Page<Training> trainingPage = trainingService.getAllTrainings(pageable);
        List<TrainingResource> resources = trainingPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Returns a training by the specified Id", description = "Returns a training by its Id", tags = {"trainings"})
    @GetMapping("/trainings/{trainingId}")
    public TrainingResource getTrainingById(@PathVariable Long trainingId) {
        return convertToResource((Training) trainingService.getTrainingById(trainingId));
    }

    @Operation(summary = "Create training", description = "Permits the Insertion of a training", tags = {"trainings"})
    @PostMapping("/trainings")
    public TrainingResource createTraining(@Valid @RequestBody SaveTrainingResource resource) {
        return convertToResource(trainingService.createTraining(convertToEntity(resource)));
    }

    @Operation(summary = "Update training", description = "Permits to update a training", tags = {"trainings"})
    @PutMapping("/trainings/{trainingId}")
    public TrainingResource updateLesson(@PathVariable Long trainingId, @Valid @RequestBody SaveTrainingResource resource) {
        return convertToResource((Training) trainingService.updateTraining(trainingId, convertToEntity(resource)));
    }

    private Training convertToEntity(SaveTrainingResource resource) {
        Training training = new Training();
        training.setStartDate(resource.getStartDate());
        training.setEndDate(resource.getEndDate());
        training.setResourceLink(resource.getResourceLink());
        training.setDescription(resource.getDescription());
        training.setMeetingLink(resource.getMeetingLink());
        training.setCoordinator(coordinatorService.getCoordinatorById(resource.getCoordinatorId()));
        training.setCalendarId("-");
        return training;
    }

        private TrainingResource convertToResource(Training entity) {
        return mapper.map(entity, TrainingResource.class);
    }

}
