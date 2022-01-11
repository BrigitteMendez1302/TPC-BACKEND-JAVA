package com.acme.tpc_backend.controller;

import com.acme.tpc_backend.domain.model.Lesson;
import com.acme.tpc_backend.domain.model.LessonStudent;
import com.acme.tpc_backend.domain.service.LessonService;
import com.acme.tpc_backend.domain.service.LessonStudentService;
import com.acme.tpc_backend.domain.service.StudentService;
import com.acme.tpc_backend.resource.LessonResource;
import com.acme.tpc_backend.resource.LessonStudentResource;
import com.acme.tpc_backend.resource.SaveLessonStudentResource;
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
public class LessonStudentsController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private LessonStudentService lessonStudentService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private LessonService lessonService;


    @Operation(summary = "Get LessonStudents By id", description = "Gets a collection of students according to a lesson",
            tags = {"lesson-students"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "A students collection returned (the one that matches)", content = @Content(mediaType = "application/json"))})
    @GetMapping("/lessonstudents/{lessonStudentId}")
    public LessonStudentResource getLessonStudentById(@PathVariable Long lessonStudentId) {
        return convertToResource(lessonStudentService.getLessonStudentById(lessonStudentId));
    }

    @Operation(summary = "Get LessonStudents By Lesson id and Student Id", description = "Gets a specific  lesson student",
            tags = {"lesson-students"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "A lesson student returns", content = @Content(mediaType = "application/json"))})

    @GetMapping("/lessonstudents/{lessonId}/students/{studentId}")
    public LessonStudentResource getLessonStudentByLessonIdAndStudentId(@PathVariable Long lessonId,
                                                                        @PathVariable Long studentId) {
        return convertToResource(lessonStudentService.getLessonStudentByLessonIdAndStudentId(lessonId, studentId));
    }

    @Operation(summary = "Create LessonStudent", description = "Permits the union of al lesson and its studetns",
            tags = {"lesson-students"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "One coordinator created (the one entered)", content = @Content(mediaType = "application/json"))})
    @PostMapping("/lessonstudents")
    public LessonStudentResource createLessonStudent(@Valid @RequestBody SaveLessonStudentResource resource) {
        return convertToResource(lessonStudentService.createLessonStudent(convertToEntity(resource)));
    }

    @Operation(summary = "Update LessonStudent", description = "Permits to update a lesson-student class",
            tags = {"lesson-students"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Lesson student updated",
                    content = @Content(mediaType = "application/json"))})
    @PutMapping("/lessonstudents/{lessonStudentId}")
    public LessonStudentResource updateLessonStudent(@PathVariable Long lessonStudentId,
                                                     @Valid @RequestBody SaveLessonStudentResource resource) {
        return convertToResource(lessonStudentService.updateLessonStudent(lessonStudentId, convertToEntity(resource)));
    }

    @Operation(summary = "Update LessonStudent attendance", description = "Permits to update the attendance of a student in a lesson",
            tags = {"lesson-students"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Lesson student attendance updated",
                    content = @Content(mediaType = "application/json"))})
    @PutMapping("/lessonstudents/{lessonId}/students/{studentId}")
    public LessonStudentResource updateLessonStudentAttendance(@PathVariable Long lessonId, @PathVariable Long studentId,
                                                     Boolean attendance) {
        return convertToResource(lessonStudentService.updateAssistance(lessonId, studentId, attendance));
    }


    @Operation(summary = "Get Lessons By Student id", description = "Gets a collection of lessons according to a studentId",
            tags = {"lesson-students"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "A lessons collection returned (the one that matches)",
                    content = @Content(mediaType = "application/json"))})
    @GetMapping("/students/{studentId}/lessons")
    public Page<LessonStudentResource> getAllLesssonsByStudentId(
            @PathVariable Long studentId,
            Pageable pageable) {
        Page<LessonStudent> lessonsPage = lessonService.getAllLessonsByStudentId(studentId, pageable);
        List<LessonStudentResource> resources = lessonsPage.getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get Lesson Students By Lesson id, for front", description =
            "Gets a collection of students according to a lesson without filters",
            tags = {"lesson-students"})
    @GetMapping("lessons/{lessonId}/lessonstudents")
    public Page<LessonStudentResource> getAllLessonStudentsByLessonId(@PathVariable Long lessonId, Pageable pageable){
        List<LessonStudent> lessonStudentPage;
        lessonStudentPage = lessonStudentService.getAllLessonStudentsByLessonId(lessonId);
        List<LessonStudentResource> resources = lessonStudentPage.stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get Lesson Students By Lesson id when it is a comment", description =
            "Gets a collection of students according to a lesson when the comment is not  a complaint",
            tags = {"lesson-students"})
    @GetMapping("lessons/{lessonId}/lessonstudents/complaint")
    public Page<LessonStudentResource> getAllLessonStudentsByLessonIdAndComplaint(@PathVariable Long lessonId, Boolean isComplaint, Pageable pageable){
        List<LessonStudent> lessonStudentPage;
        lessonStudentPage = lessonStudentService.getAllLessonStudentsByLessonIdAndComplaint(lessonId, isComplaint);
        List<LessonStudentResource> resources = lessonStudentPage.stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get Students By Lesson id", description = "Gets a collection of students according to a lesson",
            tags = {"lesson-students"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "A students collection returned (the one that matches)", content = @Content(mediaType = "application/json"))})
    @GetMapping("/lessons/{lessonId}/students")
    public Page<LessonStudentResource> getAllStudentsByLessonId(
            @PathVariable Long lessonId, @RequestParam(defaultValue = "true") Boolean assistants,
            Pageable pageable) {
        Page<LessonStudent>studentsPage;
        if(assistants == false)
        {
            studentsPage = studentService.getAllStudentsByLessonId(lessonId, pageable);
        }
        else
            studentsPage = studentService.getStudentsAssistantsByLessonId(lessonId, pageable);

        List<LessonStudentResource> resources = studentsPage.getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    //
    @GetMapping("/lessonstudents/califications")
    public Page<LessonStudentResource> getLessonStudentByLessonTypeIdAndTutorId(@PathVariable Long lessonTypeId, @PathVariable Long tutorId, Pageable pageable) {
        List<LessonStudent>lessonStudentPage;
        lessonStudentPage = lessonStudentService.getLessonStudentByLessonTypeIdAndTutorId(lessonTypeId, tutorId);
        List<LessonStudentResource> resources = lessonStudentPage.stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }
    @GetMapping("/lessonstudents/calificationsbycourse")
    public Page<LessonStudentResource> getLessonStudentByLessonTypeIdAndTutorId(@PathVariable Long lessonTypeId, @PathVariable Long tutorId,@PathVariable Long courseId, Pageable pageable) {
        List<LessonStudent>lessonStudentPage;
        lessonStudentPage = lessonStudentService.getLessonStudentByLessonTypeIdAndTutorIdAndCourseId(lessonTypeId, tutorId, courseId);
        List<LessonStudentResource> resources = lessonStudentPage.stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }
    //

    @Operation(summary = "Delete a LessonStudent", description = "Deletes LessonStudent",
            tags = {"lesson-students"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "One coordinator created (the one entered)", content = @Content(mediaType = "application/json"))})
    @DeleteMapping("/lessonstudents/{lessonStudentId}")
    public ResponseEntity<?> deleteLessonStudentById(@PathVariable Long lessonStudentId) {
        return lessonStudentService.deleteLessonStudent(lessonStudentId);
    }

    private LessonStudent convertToEntity(SaveLessonStudentResource resource) {
        LessonStudent lessonStudent = new LessonStudent();
        lessonStudent.setLesson(lessonService.getLessonById(resource.getLessonId()));
        lessonStudent.setStudent(studentService.getStudentById(resource.getStudentId()));
        lessonStudent.setTopic(resource.getTopic());
        lessonStudent.setComment(resource.getComment());
        lessonStudent.setQualification(resource.getQualification());
        lessonStudent.setComplaint(resource.isComplaint());
        lessonStudent.setAssistance(resource.isAssistance());
        return lessonStudent;
    }

    private LessonStudentResource convertToResource(LessonStudent entity) {
        return mapper.map(entity, LessonStudentResource.class);
    }
}