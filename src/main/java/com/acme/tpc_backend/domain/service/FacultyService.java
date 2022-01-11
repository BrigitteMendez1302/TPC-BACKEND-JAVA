package com.acme.tpc_backend.domain.service;
 import com.acme.tpc_backend.domain.model.Career;
 import com.acme.tpc_backend.domain.model.Faculty;
 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.Pageable;
 import org.springframework.http.ResponseEntity;
 
 public interface FacultyService {
 	Faculty getFacultyById(Long facultyId);
 	Faculty createFaculty(Faculty faculty);
 	Faculty updateFaculty(Long facultyId, Faculty facultyDetails);
 	ResponseEntity<?> deleteFaculty(Long facultyId);
 	Page<Faculty> getAllFaculties(Pageable pageable);
 }