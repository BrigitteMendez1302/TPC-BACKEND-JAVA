package com.acme.tpc_backend.domain.service;
 import com.acme.tpc_backend.domain.model.Lesson;
 import com.acme.tpc_backend.domain.model.Meeting;
 import com.acme.tpc_backend.domain.model.Training;
 import com.acme.tpc_backend.domain.model.Tutor;
 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.Pageable;
 import org.springframework.http.ResponseEntity;

 import java.util.Date;
 import java.util.List;

public interface TrainingService {
 	Meeting getTrainingById(Long trainingId);
 	Training createTraining(Training training);
 	Meeting updateTraining(Long TrainingId, Training trainingDetails);
 	ResponseEntity<?> deleteTraining(Long trainingId);
    List<Training> getAllInRange(Date start, Date end);
    Training getTrainingByMeetingId(Long longId);
    Page<Training> getAllTrainings(Pageable pageable);
}