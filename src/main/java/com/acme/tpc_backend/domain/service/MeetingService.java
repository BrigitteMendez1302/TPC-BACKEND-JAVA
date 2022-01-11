package com.acme.tpc_backend.domain.service;
 import com.acme.tpc_backend.domain.model.Meeting;
 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.Pageable;
 import org.springframework.http.ResponseEntity;
 
 public interface MeetingService {
 	Meeting getMeetingById(Long meetingId);
 	Meeting createMeeting(Meeting meeting);
 	Meeting updateMeeting(Long MeetingId, Meeting meetingDetails);
 	ResponseEntity<?> deleteMeeting(Long meetingId);
 	Page<Meeting> getAllMeetings(Pageable pageable);
 }