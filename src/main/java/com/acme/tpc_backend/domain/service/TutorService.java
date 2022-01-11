package com.acme.tpc_backend.domain.service;
 import com.acme.tpc_backend.domain.model.Account;
 import com.acme.tpc_backend.domain.model.Coordinator;
 import com.acme.tpc_backend.domain.model.Student;
 import com.acme.tpc_backend.domain.model.Tutor;
 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.Pageable;
 import org.springframework.http.ResponseEntity;
 
 public interface TutorService{
 	Tutor getTutorById(Long tutorId);
 	Tutor createTutor(Tutor tutor);
 	Tutor updateTutor(Long TutorId, Tutor tutorDetails);
 	ResponseEntity<?> deleteTutor(Long tutorId);
 	Page<Tutor> getAllTutors(Pageable pageable);
 	Tutor getTutorByAccount(Account accountId);
 	double GetWorkshopsAverage(Long tutorId, Long courseId, Long lessonTypeId);
 	Tutor updateTutorRole(Long TutorId, Tutor tutorDetails);
 }