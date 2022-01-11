package com.acme.tpc_backend.controller;

import com.acme.tpc_backend.domain.model.Career;
import com.acme.tpc_backend.domain.service.CareerService;
import com.acme.tpc_backend.resource.CareerResource;
import com.acme.tpc_backend.resource.SaveCareerResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CareerCoursesController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CareerService careerService;

    @Operation(summary = "Assign course to Career", description = "Establishes assocication between Career and Course", tags = {"career-courses"})
    @PostMapping("/careers/{careerId}/courses/{courseId}")
    public CareerResource asssignCareerCourse(@PathVariable Long careerId, @PathVariable Long courseId) {
        return convertToResource(careerService.assignCareerCourse(careerId, courseId));
    }

    @Operation(summary = "Remove assignment between Career and Course", description = "Ends association between Career and Course" , tags = {"career-courses"})
    @DeleteMapping("/careers/{careerId}/courses/{courseId}")
    public CareerResource unassignCareerCourse(@PathVariable Long careerId, @PathVariable Long courseId) {
        return convertToResource(careerService.unassignCareerCourse(careerId, courseId));
    }

    @Operation(summary = "Get All Careers By CourseId", description = "Get All Careers By CourseId" , tags = {"career-courses"})
    @GetMapping("/courses/{courseId}/careers")
    public Page<CareerResource> getAllCareersByCourseId(@PathVariable Long courseId, Pageable pageable) {
        Page<Career> careersPage = careerService.getAllCareersByCourseId(courseId, pageable);
        List<CareerResource> resources = careersPage.getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    private Career convertToEntity(SaveCareerResource resource) {
        Career career = new Career();
        career.setName(resource.getName());
        return career;
    }

    private CareerResource convertToResource(Career entity) {
        return mapper.map(entity, CareerResource.class);
    }
}
