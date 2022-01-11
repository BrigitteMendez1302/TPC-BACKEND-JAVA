package com.acme.tpc_backend.controller;

import com.acme.tpc_backend.domain.model.Tutor;
import com.acme.tpc_backend.domain.service.AccountService;
import com.acme.tpc_backend.domain.service.FacultyService;
import com.acme.tpc_backend.domain.service.TutorService;
import com.acme.tpc_backend.resource.LessonResource;
import com.acme.tpc_backend.resource.LessonTypeResource;
import com.acme.tpc_backend.resource.SaveTutorResource;
import com.acme.tpc_backend.resource.TutorResource;
import io.swagger.v3.oas.annotations.Operation;
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
public class TutorsController {

    @Autowired
    private TutorService tutorService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "List tutors", description = "Lists tutors", tags = {"tutors"})
    @GetMapping("/tutors")
    public Page<TutorResource> getAllTutors(Pageable pageable) {
        Page<Tutor> tutorsPage = tutorService.getAllTutors(pageable);
        List<TutorResource> resources = tutorsPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Returns a tutor by the specified Id", description = "Returns a tutor type by its Id", tags = {"tutors"})
    @PostMapping("/tutors")
    public TutorResource createTutor(@Valid @RequestBody SaveTutorResource resource) {
        Tutor tutor = convertToEntity(resource);
        return convertToResource(tutorService.createTutor(tutor));
    }

    @Operation(summary = "Update tutor", description = "Permits to update a tutor", tags = {"tutors"})
    @PutMapping("/tutors/{tutorId}")
    public TutorResource updateTutor(@PathVariable Long tutorId, @RequestBody SaveTutorResource resource) {
        Tutor tutor = convertToEntity(resource);
        return convertToResource(tutorService.updateTutor(tutorId, tutor));
    }

    @Operation(summary = "Update tutor role", description = "Permits to update a role of a tutor", tags = {"tutors"})
    @PutMapping("/tutors/{tutorId}/role")
    public TutorResource updateTutorRole(@PathVariable Long tutorId, @RequestBody SaveTutorResource resource) {
        Tutor tutor = convertToEntity(resource);
        return convertToResource(tutorService.updateTutorRole(tutorId, tutor));
    }

    @Operation(summary = "Delete tutor", description = "Permits to delete a tutor", tags = {"tutors"})
    @DeleteMapping("/tutors/{tutorId}")
    public ResponseEntity<?> deleteTutor(@PathVariable Long tutorId) {
        return tutorService.deleteTutor(tutorId);
    }


    @Operation(summary = "Returns a tutor by the specified Id", description = "Returns a tutor type by its Id", tags = {"tutors"})
    @GetMapping("/tutors/{tutorId}")
    public TutorResource getTutorById(@PathVariable Long tutorId) {
        return convertToResource(tutorService.getTutorById(tutorId));
    }


    private Tutor convertToEntity(SaveTutorResource resource) {
        Tutor tutor = new Tutor();
        tutor.setAccount(accountService.getAccountById(resource.getAccountId()));
        tutor.setFirstName(resource.getFirstName());
        tutor.setLastName(resource.getLastName());
        tutor.setMail(resource.getMail());
        tutor.setPhoneNumber(resource.getPhoneNumber());
        tutor.setFaculty(facultyService.getFacultyById(resource.getFacultyId()));
        tutor.setRole(resource.getRole());
        return tutor;
    }

    private TutorResource convertToResource(Tutor entity) {
        return mapper.map(entity, TutorResource.class);
    }
}