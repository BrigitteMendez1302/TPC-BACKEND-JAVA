package com.acme.tpc_backend.controller;


import com.acme.tpc_backend.domain.model.Career;
import com.acme.tpc_backend.domain.service.CareerService;
import com.acme.tpc_backend.domain.service.FacultyService;
import com.acme.tpc_backend.resource.CareerResource;
import com.acme.tpc_backend.resource.CourseResource;
import com.acme.tpc_backend.resource.SaveCareerResource;
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
public class CareersController {

    @Autowired
    private CareerService careerService;

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "List careers", description = "Lists careers", tags = {"careers"})
    @GetMapping("/careers")
    public Page<CareerResource> getAllCareers(Pageable pageable) {
        Page<Career> careersPage = careerService.getAllCareers(pageable);
        List<CareerResource> resources = careersPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "List career by id", description = "Lists the specified career", tags = {"careers"})
    @GetMapping("/careers/{careerId}")
    public CareerResource getCareerById(@PathVariable Long careerId) {
        return convertToResource(careerService.getCareerById(careerId));
    }

    @Operation(summary = "Create Career", description = "Permits the Insertion of a career", tags = {"careers"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "One career created (the one entered)", content = @Content(mediaType = "application/json"))})
    @PostMapping("/careers")
    public CareerResource createCareer(@Valid @RequestBody SaveCareerResource resource) {
        Career career = convertToEntity(resource);
        return convertToResource(careerService.createCareer(career));
    }
    @Operation(summary = "Update Career", description = "Permits to update a career", tags = {"careers"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "One Career updated (according to the changed parameters)", content = @Content(mediaType = "application/json"))})
    @PutMapping("/careers/{careerId}")
    public CareerResource updateCareer(@PathVariable Long careerId, @RequestBody SaveCareerResource resource) {
        Career career = convertToEntity(resource);
        return convertToResource(careerService.updateCareer(careerId, career));
    }

    @Operation(summary = "Delete Career", description = "Permits to delete a career", tags = {"careers"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "One Career delete(according to the given id)", content = @Content(mediaType = "application/json"))})

    @DeleteMapping("/careers/{careerId}")
    public ResponseEntity<?> deleteCareer(@PathVariable Long careerId) {
        return careerService.deleteCareer(careerId);
    }

    private Career convertToEntity(SaveCareerResource resource) {
        Career career = new Career();
        career.setName(resource.getName());
        career.setFaculty(facultyService.getFacultyById(resource.getFacultyId()));
        return  career;
    }

    private CareerResource convertToResource(Career entity) {
        return mapper.map(entity, CareerResource.class);
    }
}