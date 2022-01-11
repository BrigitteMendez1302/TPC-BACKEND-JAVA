package com.acme.tpc_backend.domain.service;
 import com.acme.tpc_backend.domain.model.Career;
 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.Pageable;
 import org.springframework.http.ResponseEntity;
 
 public interface CareerService {
 	Career getCareerById(Long careerId);
 	Career createCareer(Career career);
 	Career updateCareer(Long CareerId, Career careerDetails);
 	ResponseEntity<?> deleteCareer(Long careerId);
 	Page<Career> getAllCareers(Pageable pageable);
 	Career assignCareerCourse(Long careerId, Long courseId);
 	Career unassignCareerCourse(Long careerId, Long courseId);
 	Page<Career> getAllCareersByCourseId(Long courseId, Pageable pageable);
 }