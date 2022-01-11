package com.acme.tpc_backend.controller;

import com.acme.tpc_backend.domain.model.Faculty;
import com.acme.tpc_backend.domain.model.LessonStudent;
import com.acme.tpc_backend.domain.model.LessonType;
import com.acme.tpc_backend.domain.service.LessonTypeService;
import com.acme.tpc_backend.resource.FacultyResource;
import com.acme.tpc_backend.resource.LessonTypeResource;
import com.acme.tpc_backend.resource.SaveLessonTypeResource;
import com.acme.tpc_backend.domain.service.LessonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
public class LessonTypeController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private LessonTypeService lessonTypeService;

    @Operation(summary = "List faculties", description = "Lists faculties", tags = {"lesson-types"})
    @GetMapping("/lessontypes")
    public Page<LessonTypeResource> getAllLessonTypes(Pageable pageable) {
        Page<LessonType> lessonTypePage  = lessonTypeService.getAllLessonTypes(pageable);
        List<LessonTypeResource> resources = lessonTypePage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Returns a lessonType by the specified Id", description = "Returns a lesson type by its Id", tags = {"lesson-types"})
    @GetMapping("/lessontypes/{lessontypeId}")
    public LessonTypeResource getLessonTypeById(@PathVariable Long lessontypeId) {
        return convertToResource(lessonTypeService.getLessonTypeById(lessontypeId));
    }

    @Operation(summary = "Create LessonType", description = "Permits the Insertion of a lesson type", tags = {"lesson-types"})
    @PostMapping("/lessontypes")
    public LessonTypeResource createLessonType(@Valid @RequestBody SaveLessonTypeResource resource) {
        return convertToResource(lessonTypeService.createLessonType(convertToEntity(resource)));
    }

    @Operation(summary = "Update lesson type", description = "Permits to update a lesson type", tags = {"lesson-types"})
    @PutMapping("/lessontypes/{lessontypeId}")
    public LessonTypeResource updateLessonType(@PathVariable Long lessontypeId, @Valid @RequestBody SaveLessonTypeResource resource) {
        return convertToResource(lessonTypeService.updateLessonType(lessontypeId, convertToEntity(resource)));
    }

    private LessonType convertToEntity(SaveLessonTypeResource resource) {
        LessonType lessonType = new LessonType();
        lessonType.setName(resource.getName());
        lessonType.setQuantity(resource.getQuantity());
        return lessonType;
    }

    private LessonTypeResource convertToResource(LessonType entity) {
        return mapper.map(entity, LessonTypeResource.class);
    }
}