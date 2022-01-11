package com.acme.tpc_backend.controller;

import com.acme.tpc_backend.domain.model.Lesson;
import com.acme.tpc_backend.domain.model.Suggestion;
import com.acme.tpc_backend.domain.service.LessonService;
import com.acme.tpc_backend.resource.LessonResource;
import com.acme.tpc_backend.resource.LessonTypeResource;
import com.acme.tpc_backend.resource.SuggestionResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class LessonTypeLessonsController {
    @Autowired
    private LessonService lessonService;

    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "Get All Lessons of a LessonType", description = "get all lessons of a lessontype",
            tags = {"lesson-lessontypes"})
    @GetMapping("/lessontypes/{lessontypeId}/lessons")
    public Page<LessonResource> getLessonsByLessonTypeId(@PathVariable Long lessonTypeId, Pageable pageable) {
        Page<Lesson> lessonPage = lessonService.getAllLessonsByLessonTypeId(lessonTypeId, pageable);
        List<LessonResource> resources = lessonPage.getContent().stream().map(
                this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    private LessonResource convertToResource(Lesson entity) {
        return mapper.map(entity, LessonResource.class);
    }
}