package com.acme.tpc_backend.domain.service;
 import com.acme.tpc_backend.domain.model.Faculty;
 import com.acme.tpc_backend.domain.model.University;
 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.Pageable;
 import org.springframework.http.ResponseEntity;
 
 public interface UniversityService {
 	University getUniversityById(Long universityId);
 	University createUniversity(University university);
 	University updateUniversity(Long UniversityId, University universityDetails);
 	ResponseEntity<?> deleteUniversity(Long universityId);
 	Page<University> getAllUniversities(Pageable pageable);
 }