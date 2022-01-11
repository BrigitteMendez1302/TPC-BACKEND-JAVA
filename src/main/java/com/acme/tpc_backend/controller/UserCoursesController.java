package com.acme.tpc_backend.controller;

import com.acme.tpc_backend.domain.model.Course;
import com.acme.tpc_backend.domain.model.User;
import com.acme.tpc_backend.domain.service.CourseService;
import com.acme.tpc_backend.domain.service.UserService;
import com.acme.tpc_backend.resource.CourseResource;
import com.acme.tpc_backend.resource.SaveCourseResource;
import com.acme.tpc_backend.resource.SaveUserResource;
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

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserCoursesController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CourseService courseService;

    @Operation(summary = "Assign User to Course",
            description = "Establishes association between User and Course",
            tags = {"user-courses"}
    )
    @PostMapping("/users/{userId}/courses/{courseId}")
    public CourseResource assignPostTag(
            @PathVariable Long userId,
            @PathVariable Long courseId) {
        return convertToResource(courseService.assignCourseUser(userId, courseId));
    }

    @Operation(summary = "Remove assignment between User and Course",
            description = "Ends association between User and Course",
            tags = {"user-courses"}
    )
    @DeleteMapping("/users/{userId}/courses/{courseId}")
    public CourseResource unassignPostTag(
            @PathVariable Long userId,
            @PathVariable Long courseId) {
        return convertToResource(courseService.unassignCourseUser(userId, courseId));
    }

    private Course convertToEntity(SaveCourseResource resource) {
        return mapper.map(resource, Course.class);
    }

    private CourseResource convertToResource(Course entity) {
        return mapper.map(entity, CourseResource.class);
    }
    private UserResource convertToResource(User entity) {
        return mapper.map(entity, UserResource.class);
    }
}
