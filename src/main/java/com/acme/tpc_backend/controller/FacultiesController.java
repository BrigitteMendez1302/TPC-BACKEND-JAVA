package com.acme.tpc_backend.controller;

import com.acme.tpc_backend.domain.model.Faculty;
import com.acme.tpc_backend.domain.service.FacultyService;
import com.acme.tpc_backend.resource.FacultyResource;
import com.acme.tpc_backend.resource.SaveFacultyResource;
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
public class FacultiesController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private FacultyService facultyService;

    @Operation(summary = "List faculties", description = "Lists faculties", tags = {"faculties"})
    @GetMapping("/faculties")
    public Page<FacultyResource> getAllFaculties(Pageable pageable) {
        Page<Faculty> facultiesPage = facultyService.getAllFaculties(pageable);
        List<FacultyResource> resources = facultiesPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Returns a faculty by the specified Id", description = "Returns a faculty by its Id", tags = {"faculties"})
    @GetMapping("faculties/{facultyId}")
    public FacultyResource getFacultyById(@PathVariable Long facultyId) {
        return convertToResource(facultyService.getFacultyById(facultyId));
    }

    @Operation(summary = "Create Faculty", description = "Permits the Insertion of a faculty", tags = {"faculties"})
    @PostMapping("/faculties")
    public FacultyResource createFaculty(@Valid @RequestBody SaveFacultyResource resource) {
        Faculty faculty = convertToEntity(resource);
        return convertToResource(facultyService.createFaculty(faculty));
    }

    @Operation(summary = "Update faculty", description = "Permits to update a faculty", tags = {"faculties"})
    @PutMapping("/faculties/{facultyId}")
    public FacultyResource updateFaculty(@PathVariable Long facultyId, @RequestBody SaveFacultyResource resource) {
        Faculty faculty = convertToEntity(resource);
        return convertToResource(facultyService.updateFaculty(facultyId, faculty));
    }

    @Operation(summary = "Delete faculty", description = "Permits to delete a faculty", tags = {"faculties"})
    @DeleteMapping("/faculties/{facultyId}")
    public ResponseEntity<?> deleteFaculty(@PathVariable Long facultyId) {
        return facultyService.deleteFaculty(facultyId);
    }

    private Faculty convertToEntity(SaveFacultyResource resource) {
        Faculty faculty = new Faculty();
        faculty.setName(resource.getName());
        faculty.setDescription(resource.getDescription());
        return faculty;
    }

    private FacultyResource convertToResource(Faculty entity) {
        return mapper.map(entity, FacultyResource.class);
    }
}