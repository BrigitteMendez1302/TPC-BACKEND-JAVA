package com.acme.tpc_backend.controller;

import com.acme.tpc_backend.domain.model.Course;
import com.acme.tpc_backend.domain.model.User;
import com.acme.tpc_backend.domain.service.CourseService;
import com.acme.tpc_backend.resource.CourseResource;
import com.acme.tpc_backend.resource.SaveCourseResource;
import com.acme.tpc_backend.resource.UserResource;
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
public class CoursesController {
    
    @Autowired
    private ModelMapper mapper;
    
    @Autowired
    private CourseService courseService;

    @Operation(summary = "Get Course By id", description = "Gets a course by a given Id", tags = {"courses"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "One course returned (the one that matches)", content = @Content(mediaType = "application/json"))})
    @GetMapping("/courses/{courseId}")
    public CourseResource getCourseById(@PathVariable Long courseId) {
        return convertToResource(courseService.getCourseById(courseId));
    }

    @Operation(summary = "Get All Courses", description = "Gets all courses by pages", tags = {"courses"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "All Courses returned", content = @Content(mediaType = "application/json"))})
    @GetMapping("/courses")
    public Page<CourseResource> getAllCourses(Pageable pageable) {
        Page<Course> coursePage = courseService.getAllCourses(pageable);
        List<CourseResource> resources = coursePage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources,pageable,resources.size());
    }

    @Operation(summary = "Get All Users by CourseId", description = "Gets all users by courseId", tags = {"courses"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "All users of a Course returned", content = @Content(mediaType = "application/json"))})
    @GetMapping("/courses/{courseId}/users")
    public Page<UserResource> getAllUsersByCourseId(@PathVariable Long courseId, Pageable pageable){
        Page<User> userPage = courseService.getAllUsersByCourseId(courseId, pageable);
        List<UserResource> resources = userPage.getContent().stream().map(
                this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Create Course", description = "Permits the Insertion of a course", tags = {"courses"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "One course created (the one entered)", content = @Content(mediaType = "application/json"))})
    @PostMapping("/courses")
    public CourseResource createCourse(@Valid @RequestBody SaveCourseResource resource) {
        return convertToResource(courseService.createCourse(convertToEntity(resource)));
    }

    @Operation(summary = "Update Course", description = "Permits to update a course", tags = {"courses"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "One course updated (according to the changed parameters)", content = @Content(mediaType = "application/json"))})
    @PutMapping("/courses/{courseId}")
    public CourseResource updateCourse(@PathVariable Long courseId, @Valid @RequestBody SaveCourseResource resource) {
        return convertToResource(courseService.updateCourse(courseId, convertToEntity(resource)));
    }


    private Course convertToEntity(SaveCourseResource resource)
    {
        Course course = new Course();
        course.setName(resource.getName());
        return course;
    }

    private CourseResource convertToResource(Course entity) { return mapper.map(entity, CourseResource.class); }

    private UserResource convertToResource(User entity) {
        return mapper.map(entity, UserResource.class);
    }
}