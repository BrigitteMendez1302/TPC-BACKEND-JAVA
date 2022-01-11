package com.acme.tpc_backend.service;

import com.acme.tpc_backend.domain.model.*;
import com.acme.tpc_backend.domain.repository.LessonRepository;
import com.acme.tpc_backend.domain.repository.LessonStudentRepository;
import com.acme.tpc_backend.domain.repository.TutorRepository;
import com.acme.tpc_backend.domain.service.TutorService;
import com.acme.tpc_backend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorServiceImpl implements TutorService {

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private LessonStudentRepository lessonStudentRepository;

    @Override
    public Tutor getTutorById(Long tutorId) {
         return tutorRepository.findById(tutorId)
                 .orElseThrow(()-> new ResourceNotFoundException("Tutor", "Id", tutorId));
    }

    @Override
    public Tutor createTutor(Tutor tutor) {
        return tutorRepository.save(tutor);
    }

    @Override
    public Tutor updateTutor(Long TutorId, Tutor tutorDetails) {
        Tutor tutor = tutorRepository.findById(TutorId)
                .orElseThrow(()-> new ResourceNotFoundException("Tutor", "Id", TutorId));

        tutor.setFirstName(tutorDetails.getFirstName());
        tutor.setLastName(tutorDetails.getLastName());
        tutor.setPhoneNumber(tutorDetails.getPhoneNumber());
        return tutorRepository.save(tutor);
    }

    @Override
    public Tutor updateTutorRole(Long TutorId, Tutor tutorDetails) {
        Tutor tutor = tutorRepository.findById(TutorId)
                .orElseThrow(()-> new ResourceNotFoundException("Tutor", "Id", TutorId));

        tutor.setRole(tutorDetails.getRole());
        return tutorRepository.save(tutor);
    }

    @Override
    public ResponseEntity<?> deleteTutor(Long tutorId) {
        return tutorRepository.findById(tutorId).map(tutor -> {
            tutorRepository.delete(tutor);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Tutor", "Id", tutorId));
    }

    @Override
    public Page<Tutor> getAllTutors(Pageable pageable) {
        return tutorRepository.findAll(pageable);
    }

    @Override
    public Tutor getTutorByAccount(Account accountId) {
        return tutorRepository.findByAccount(accountId)
                .orElseThrow(()-> new ResourceNotFoundException("Tutor", "Account", accountId));
    }

    @Override
    public double GetWorkshopsAverage(Long tutorId, Long courseId, Long lessonTypeId) {
        double sum = 0;
        int quantity = 0;
        List<Lesson> lessons = lessonRepository.findAllByTutorIdAndCourseIdAndLessonTypeId(tutorId, courseId, lessonTypeId);
        if (lessons.size() == 0){
            return 0.0;
        }
        List<LessonStudent> lessonStudents;
        for(Lesson lesson: lessons){
            lessonStudents = lessonStudentRepository.getAllByLessonId(lesson.getId());
            if (lessonStudents.size() != 0){
                for(LessonStudent lessonStudent: lessonStudents){
                    sum+=lessonStudent.getQualification();
                    quantity +=1;
                }
            }
            else continue;
        }
        return sum/quantity;
    }
}
