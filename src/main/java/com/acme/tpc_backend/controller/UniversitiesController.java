package com.acme.tpc_backend.controller;

import com.acme.tpc_backend.domain.model.University;
import com.acme.tpc_backend.domain.service.UniversityService;
import com.acme.tpc_backend.resource.SaveUniversityResource;
import com.acme.tpc_backend.resource.UniversityResource;
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
public class UniversitiesController {

    @Autowired
    private UniversityService universityService;

    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "List universities", description = "Lists universities", tags = {"universities"})
    @GetMapping("/universities")
    public Page<UniversityResource> getAllUniversities(Pageable pageable) {
        Page<University> universitiesPage = universityService.getAllUniversities(pageable);
        List<UniversityResource> resources = universitiesPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Create university", description = "Permits the Insertion of a university", tags = {"universities"})
    @PostMapping("/universities")
    public UniversityResource createUniversity(@Valid @RequestBody SaveUniversityResource resource) {
        University university = convertToEntity(resource);
        return convertToResource(universityService.createUniversity(university));
    }

    @Operation(summary = "Update university", description = "Permits to update a university", tags = {"universities"})
    @PutMapping("/universities/{universityId}")
    public UniversityResource updateUniversity(@PathVariable Long universityId, @RequestBody SaveUniversityResource resource) {
        University university = convertToEntity(resource);
        return convertToResource(universityService.updateUniversity(universityId, university));
    }

    @Operation(summary = "Delete university", description = "Permits to delete a university", tags = {"universities"})
    @DeleteMapping("/universities/{universityId}")
    public ResponseEntity<?> deleteUniversity(@PathVariable Long universityId) {
        return universityService.deleteUniversity(universityId);
    }

    private University convertToEntity(SaveUniversityResource resource) {
        University university = new University();
        university.setName(resource.getName());
        return university;
    }

    private UniversityResource convertToResource(University entity) {
        return mapper.map(entity, UniversityResource.class);
    }
}