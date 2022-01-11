package com.acme.tpc_backend.service;

import com.acme.tpc_backend.domain.model.Lesson;
import com.acme.tpc_backend.domain.model.Meeting;
import com.acme.tpc_backend.domain.model.Training;
import com.acme.tpc_backend.domain.repository.TrainingRepository;
import com.acme.tpc_backend.domain.service.TrainingService;
import com.acme.tpc_backend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TrainingServiceImpl implements TrainingService {
    @Autowired
    private TrainingRepository trainingRepository;

    @Override
    public Meeting getTrainingById(Long trainingId) {
        return trainingRepository.findById(trainingId)
                .orElseThrow(()-> new ResourceNotFoundException("Training", "Id", trainingId));
    }

    @Override
    public Training createTraining(Training training) {
        return trainingRepository.save(training);
    }

    @Override
    public Meeting updateTraining(Long TrainingId, Training trainingDetails) {
        return trainingRepository.findById(TrainingId)
                .map(training -> {
                    training.setDescription(trainingDetails.getDescription());
                    return trainingRepository.save(training);
                }).orElseThrow(()-> new ResourceNotFoundException("Training", "Id", TrainingId));
    }

    @Override
    public ResponseEntity<?> deleteTraining(Long trainingId) {
        return null;
    }

    @Override
    public List<Training> getAllInRange(Date start, Date end) {
        return trainingRepository.getTrainingsInRange(start, end);
    }

    @Override
    public Training getTrainingByMeetingId(Long longId) {
        return trainingRepository.findById(longId)
                .orElseThrow(()-> new ResourceNotFoundException("Training", "Id", longId));
    }
    @Override
    public Page<Training> getAllTrainings(Pageable pageable) {
        return trainingRepository.findAll(pageable);
    }

}