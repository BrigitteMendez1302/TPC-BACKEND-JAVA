package com.acme.tpc_backend.controller;

import com.acme.tpc_backend.domain.service.TutorService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TutorAveragesController {

    @Autowired
    private TutorService tutorService;

    @Operation(
            summary = "Returns the average of the rating students gave this tutor for every taught workshop",
            description = "The average of the rating students gave this tutor for every taught workshop",
            tags = {"tutor-averages"})
    @GetMapping("/tutors/{tutorId}/courses/{courseId}/workshopsaverage")
    public double getWorkshopsAverage( @PathVariable Long tutorId, Long lessonTypeId, @PathVariable Long courseId){
        return tutorService.GetWorkshopsAverage(tutorId, courseId, lessonTypeId );
    }

    @Operation(
            summary = "Returns the average of the rating students gave this tutor for every taught tutorship",
            description = "The average of the rating students gave this tutor for every taught tutorship",
            tags = {"tutor-averages"})
    @GetMapping("/tutors/{tutorId}/courses/{courseId}/tutorshipsaverage")
    public double getTutorShipsAverage( @PathVariable Long tutorId, Long lessonTypeId, @PathVariable Long courseId){
        return tutorService.GetWorkshopsAverage(tutorId, courseId, lessonTypeId );
    }
}
