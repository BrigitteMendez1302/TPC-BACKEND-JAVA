package com.acme.tpc_backend.controller;
import com.acme.tpc_backend.domain.model.Coordinator;
import com.acme.tpc_backend.domain.service.AccountService;
import com.acme.tpc_backend.domain.service.CoordinatorService;
import com.acme.tpc_backend.domain.service.FacultyService;
import com.acme.tpc_backend.resource.CoordinatorResource;
import com.acme.tpc_backend.resource.SaveCoordinatorResource;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CoordinatorsController {

    @Autowired
    private CoordinatorService coordinatorService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "List coordinators", description = "Lists coordinators", tags = {"coordinators"})
    @GetMapping("/coordinators")
    public Page<CoordinatorResource> getAllCoordinators(Pageable pageable) {
        Page<Coordinator> coordinatorsPage = coordinatorService.getAllCoordinators(pageable);
        List<CoordinatorResource> resources = coordinatorsPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Returns a coordinator by the specified Id", description = "Returns a coordinator type by its Id", tags = {"coordinators"})
    @GetMapping("/coordinators/{coordinatorId}")
    public CoordinatorResource getCoordinatorById(@PathVariable Long coordinatorId) {
        return convertToResource(coordinatorService.getCoordinatorById(coordinatorId));
    }

    @Operation(summary = "Create Coordinator", description = "Permits the Insertion of a coordinator", tags = {"coordinators"})
    @PostMapping("/coordinators")
    public CoordinatorResource createCoordinator(@Valid @RequestBody SaveCoordinatorResource resource) {
        Coordinator coordinator = convertToEntity(resource);
        return convertToResource(coordinatorService.createCoordinator(coordinator));
    }

    @Operation(summary = "Update Coordinator info", description = "Permits to update a coordinator", tags = {"coordinators"})
    @PutMapping("/coordinators/{coordinatorId}")
    public CoordinatorResource updateCoordinator(@PathVariable Long coordinatorId, @RequestBody SaveCoordinatorResource resource) {
        Coordinator coordinator = convertToEntity(resource);
        return convertToResource(coordinatorService.updateCoordinator(coordinatorId, coordinator));
    }

    @Operation(summary = "Update Coordinator Role", description = "Permits to update a role of a coordinator", tags = {"coordinators"})
    @PutMapping("/coordinators/{coordinatorId}/role")
    public CoordinatorResource updateCoordinatorRole(@PathVariable Long coordinatorId, @RequestBody SaveCoordinatorResource resource) {
        Coordinator coordinator = convertToEntity(resource);
        return convertToResource(coordinatorService.updateCoordinatorRole(coordinatorId, coordinator));
    }

    @Operation(summary = "Delete Coordinator", description = "Permits to delete a coordinator", tags = {"coordinators"})
    @DeleteMapping("/coordinators/{coordinatorId}")
    public ResponseEntity<?> deleteCoordinator(@PathVariable Long coordinatorId) {
        return coordinatorService.deleteCoordinator(coordinatorId);
    }

    private Coordinator convertToEntity(SaveCoordinatorResource resource) {
        Coordinator coordinator = new Coordinator();
        coordinator.setAccount(accountService.getAccountById(resource.getAccountId()));
        coordinator.setFirstName(resource.getFirstName());
        coordinator.setLastName(resource.getLastName());
        coordinator.setMail(resource.getMail());
        coordinator.setPhoneNumber(resource.getPhoneNumber());
        coordinator.setFaculty(facultyService.getFacultyById(resource.getFacultyId()));
        coordinator.setRole(resource.getRole());
        return coordinator;
    }

    private CoordinatorResource convertToResource(Coordinator entity) {
        return mapper.map(entity, CoordinatorResource.class);
    }
}