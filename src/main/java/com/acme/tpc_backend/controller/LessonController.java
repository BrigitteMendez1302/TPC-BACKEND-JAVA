package com.acme.tpc_backend.controller;

import com.acme.tpc_backend.domain.model.Faculty;
import com.acme.tpc_backend.domain.model.Lesson;
import com.acme.tpc_backend.domain.model.Tutor;
import com.acme.tpc_backend.domain.service.*;
import com.acme.tpc_backend.resource.FacultyResource;
import com.acme.tpc_backend.resource.LessonResource;
import com.acme.tpc_backend.resource.SaveLessonResource;
import com.acme.tpc_backend.resource.TutorResource;
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

import javax.persistence.Convert;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class LessonController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private LessonService lessonService;

    @Autowired
    private TutorService tutorService;

    @Autowired
    private LessonTypeService lessonTypeService;

    @Autowired
    private CourseService courseService;

    @Operation(summary = "List lessons", description = "Lists lessons", tags = {"lessons"})
    @GetMapping("/lessons")
    public Page<LessonResource> getAllLessons(Pageable pageable) {
        Page<Lesson> lessonsPage = lessonService.getAllLessons(pageable);
        List<LessonResource> resources = lessonsPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Returns a lesson by the specified Id", description = "Returns a lesson by its Id", tags = {"lessons"})
    @GetMapping("/lessons/{lessonId}")
    public LessonResource getLessonById(@PathVariable Long lessonId) {
        return convertToResource((Lesson) lessonService.getLessonById(lessonId));
    }

    @Operation(summary = "Create Lesson", description = "Permits the Insertion of a lesson", tags = {"lessons"})
    @PostMapping("/lessons")
    public LessonResource createLesson(Long tutorId, @Valid @RequestBody SaveLessonResource resource) {
        return convertToResource(lessonService.createLesson(tutorId, convertToEntity(resource)));
    }

    @Operation(summary = "List lessons in a specified range", description = "Returns lessons in a range of dates", tags = {"lessons"})
    @GetMapping("/lessons/range")
    public Page<LessonResource> getAllLessonsInRange(String start, String end, Pageable pageable){
        //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date s;
        Date f;
        try{
            s = new SimpleDateFormat("yyyy-MM-dd").parse(start);
            f = new SimpleDateFormat("yyyy-MM-dd").parse(end);
        }
        catch (ParseException e){
            throw new IllegalArgumentException("mal formato de fecha");
        }

        List<Lesson> lessonList = lessonService.getAllInRange(s, f);
        List<LessonResource> resources = lessonList
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "List lessons in a specified range and a given tutor",
            description = "Returns lessons in a range of dates by a gicen tutor anf lessontype", tags = {"lessons"})
    @GetMapping("/lessons/filtered")
    public Page<LessonResource> getAllLessonsInRangeByTutorIdAndLessonTypeId(String start, String end, Long tutorId,
                                                                             Long lessonTypeId, Pageable pageable){
        Date s;
        Date f;
        try{
            s = new SimpleDateFormat("yyyy-MM-dd").parse(start);
            f = new SimpleDateFormat("yyyy-MM-dd").parse(end);
        }
        catch (ParseException e){
            throw new IllegalArgumentException("mal formato de fecha");
        }
        List<Lesson> lessonList = lessonService.getAllLessonsInRangeByTutorIdAndLessonTypeId(s,f,tutorId, lessonTypeId);
        List<LessonResource> resources = lessonList
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "List lessons by LessonTypeId",
            description = "Returns lessons by lessontype", tags = {"lessons"})
    @GetMapping("/lessons/filter")
    public Page<LessonResource> getLessonsByLessonTypeId(Long lessonTypeId, Pageable pageable){
        List<Lesson> lessonList = lessonService.getAllLessonsByLessonTypeId(lessonTypeId);
        List<LessonResource> resources = lessonList
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Update Lesson", description = "Permits to update a lesson", tags = {"lessons"})
    @PutMapping("/lessons/{lessonId}")
    public LessonResource updateLesson(@PathVariable Long lessonId, @Valid @RequestBody SaveLessonResource resource) {
        return convertToResource((Lesson) lessonService.updateLesson(lessonId, convertToEntity(resource)));
    }

    @Operation(summary = "Update Lesson meeting link", description = "Permits to update a meeting link of a lesson", tags = {"lessons"})
    @PutMapping("/lessons/{lessonId}/link")
    public LessonResource updateLessonMeetingLink(@PathVariable Long lessonId,
                                                  String meetingLink) {
        return convertToResource((Lesson) lessonService.updateMeetingLink(lessonId, meetingLink));
    }

    @Operation(summary = "Update Lesson calendarId", description = "Permits to update a calendar id of a lesson", tags = {"lessons"})
    @PutMapping("/lessons/{lessonId}/calendar")
    public LessonResource updateCalendarId(@PathVariable Long lessonId,
                                                  String calendarId) {
        return convertToResource((Lesson) lessonService.updateCalendarId(lessonId, calendarId));
    }

    @Operation(summary = "Update Lesson by some fields", description =
            "Permits to update a lesson the startDate and endDate of a lesson", tags = {"lessons"})
    @PutMapping("/lessons/{lessonId}/filtered")
    public LessonResource updateLessonFiltered(@PathVariable Long lessonId, String start, String end ) {
        Date s;
        Date f;
        try{
            s = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S").parse(start);
            f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S").parse(end);
        }
        catch (ParseException e){
            throw new IllegalArgumentException("mal formato de fecha");
        }
        return convertToResource((Lesson) lessonService.updateByTutor(lessonId, s, f));
    }


    private Lesson convertToEntity(SaveLessonResource resource) {
        Lesson lesson = new Lesson();
        lesson.setStartDate(resource.getStartDate());
        lesson.setEndDate(resource.getEndDate());
        lesson.setResourceLink(resource.getResourceLink());
        lesson.setDescription(resource.getDescription());
        lesson.setMeetingLink(resource.getMeetingLink());
        lesson.setTutor(tutorService.getTutorById(resource.getTutorId()));
        lesson.setLessonType(lessonTypeService.getLessonTypeById(resource.getLessonTypeId()));
        lesson.setCourse(courseService.getCourseById(resource.getCourseId()));
        lesson.setVacants(resource.getVacants());
        lesson.setDescription(resource.getDescription());
        lesson.setResourceLink(resource.getResourceLink());
        lesson.setMeetingLink(resource.getMeetingLink());
        lesson.setStartDate(resource.getStartDate());
        lesson.setEndDate(resource.getEndDate());
        lesson.setCalendarId("-");
        return  lesson;
    }

    private LessonResource convertToResource(Lesson entity) {
        return mapper.map(entity, LessonResource.class);
    }
}