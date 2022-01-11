package com.acme.tpc_backend.controller;

import com.acme.tpc_backend.domain.model.Meeting;
import com.acme.tpc_backend.domain.service.MeetingService;
import com.acme.tpc_backend.resource.MeetingResource;
import com.acme.tpc_backend.resource.SaveMeetingResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import com.acme.tpc_backend.domain.model.Suggestion;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.acme.tpc_backend.resource.SuggestionResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class MeetingController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private MeetingService meetingService;

    @Operation(summary = "Returns a meeting by the specified Id", description = "Returns a meeting by its Id", tags = {"meetings"})
    @GetMapping("/meetings/{meetingId}")
    public MeetingResource getMeetingById(@PathVariable Long meetingId) {
        return convertToResource(meetingService.getMeetingById(meetingId));
    }

    @Operation(summary = "List meetings", description = "Lists meetings", tags = {"meetings"})
    @GetMapping("/meetings")
    public Page<MeetingResource> getAllSuggestions(Pageable pageable) {
        Page<Meeting> meetingsPage = meetingService.getAllMeetings(pageable);
        List<MeetingResource> resources = meetingsPage.getContent().stream().map(
                this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Create Meeting", description = "Permits the Insertion of a meeting", tags = {"meetings"})
    @PostMapping("/meetings")
    public MeetingResource createMeeting(@Valid @RequestBody SaveMeetingResource resource) {
        return convertToResource(meetingService.createMeeting(convertToEntity(resource)));
    }

    @Operation(summary = "Update Meeting", description = "Permits to update a meeting", tags = {"meetings"})
    @PutMapping("/meetings/{meetingId}")
    public MeetingResource updateMeeting(@PathVariable Long meetingId, @Valid @RequestBody SaveMeetingResource resource) {
        return convertToResource(meetingService.updateMeeting(meetingId, convertToEntity(resource)));
    }

    private Meeting convertToEntity(SaveMeetingResource resource) {
        return mapper.map(resource, Meeting.class);
    }

    private MeetingResource convertToResource(Meeting entity) {
        return mapper.map(entity, MeetingResource.class);
    }
}
