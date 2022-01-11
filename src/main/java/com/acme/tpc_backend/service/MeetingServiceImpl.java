package com.acme.tpc_backend.service;

import com.acme.tpc_backend.domain.model.Meeting;
import com.acme.tpc_backend.domain.repository.MeetingRepository;
import com.acme.tpc_backend.domain.service.MeetingService;
import com.acme.tpc_backend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class MeetingServiceImpl implements MeetingService {
    
    @Autowired
    private MeetingRepository meetingRepository;
    
    @Override
    public Meeting getMeetingById(Long meetingId) {
        return meetingRepository.findById(meetingId)
                .orElseThrow(()-> new ResourceNotFoundException("Meeting", "Id", meetingId));
    }

    @Override
    public Meeting createMeeting(Meeting meeting) {
        return meetingRepository.save(meeting);
    }

    @Override
    public Meeting updateMeeting(Long meetingId, Meeting meetingDetails) {
        return meetingRepository.findById(meetingId)
                .map(meeting -> {
                    meeting.setDescription(meetingDetails.getDescription());
                    return meetingRepository.save(meeting);
                }).orElseThrow(()-> new ResourceNotFoundException("Meeting", "Id", meetingId));
    }

    @Override
    public ResponseEntity<?> deleteMeeting(Long meetingId) {
        return meetingRepository.findById(meetingId)
                .map(meeting -> {
                    meetingRepository.delete(meeting);
                    return ResponseEntity.ok().build();
                }).orElseThrow(()->new ResourceNotFoundException("Meeting", "Id", meetingId));
    }
    @Override
    public Page<Meeting> getAllMeetings(Pageable pageable) {
        return meetingRepository.findAll(pageable);
    }
}
