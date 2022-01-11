package com.acme.tpc_backend.domain.service;
 import com.acme.tpc_backend.domain.model.TrainingTutor;
 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.Pageable;
 import org.springframework.http.ResponseEntity;
 
 public interface TrainingTutorService {
 	TrainingTutor getTrainingTutorById(Long trainingTutorId);
 	TrainingTutor createTrainingTutor(TrainingTutor trainingTutor);
 	TrainingTutor updateTrainingTutor(Long TrainingTutorId, TrainingTutor trainingTutorDetails);
 	ResponseEntity<?> deleteTrainingTutor(Long trainingTutorId);
 }