package com.acme.tpc_backend.service;

import com.acme.tpc_backend.domain.model.TrainingTutor;
import com.acme.tpc_backend.domain.repository.TrainingRepository;
import com.acme.tpc_backend.domain.repository.TrainingTutorRepository;
import com.acme.tpc_backend.domain.service.TrainingTutorService;
import com.acme.tpc_backend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TrainingTutorServiceImpl implements TrainingTutorService {
    @Autowired
    private TrainingTutorRepository trainingTutorRepository;

    @Override
    public TrainingTutor getTrainingTutorById(Long trainingTutorId) {
        return trainingTutorRepository.findById(trainingTutorId)
                .orElseThrow(()-> new ResourceNotFoundException("TrainingTutor", "Id", trainingTutorId));
    }

    @Override
    public TrainingTutor createTrainingTutor(TrainingTutor trainingTutor) {
        return trainingTutorRepository.save(trainingTutor);
    }

    @Override
    public TrainingTutor updateTrainingTutor(Long TrainingTutorId, TrainingTutor trainingTutorDetails) {
        return trainingTutorRepository.findById(TrainingTutorId)
                .map(trainingTutor -> {
                    trainingTutor.setTraining(trainingTutorDetails.getTraining());
                    trainingTutor.setTutor(trainingTutorDetails.getTutor());
                    trainingTutor.setAssistance(trainingTutorDetails.getAssistance());
                    return trainingTutorRepository.save(trainingTutor);
                }).orElseThrow(()-> new ResourceNotFoundException("TrainingTutor", "Id", TrainingTutorId));
    }

    @Override
    public ResponseEntity<?> deleteTrainingTutor(Long trainingTutorId) {
        return trainingTutorRepository.findById(trainingTutorId)
                .map(trainingTutor -> {
                    trainingTutorRepository.delete(trainingTutor);
                    return ResponseEntity.ok().build();
                }).orElseThrow(()-> new ResourceNotFoundException("TrainingTutor", "Id", trainingTutorId));
    }
}
