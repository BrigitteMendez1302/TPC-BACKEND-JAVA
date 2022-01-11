package com.acme.tpc_backend.service;

import com.acme.tpc_backend.domain.model.Lesson;
import com.acme.tpc_backend.domain.model.LessonStudent;
import com.acme.tpc_backend.domain.model.Student;
import com.acme.tpc_backend.domain.repository.LessonRepository;
import com.acme.tpc_backend.domain.repository.LessonStudentRepository;
import com.acme.tpc_backend.domain.repository.StudentRepository;
import com.acme.tpc_backend.domain.service.LessonService;
import com.acme.tpc_backend.domain.service.LessonStudentService;
import com.acme.tpc_backend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LessonStudentServiceImpl implements LessonStudentService {
    
    @Autowired
    private LessonStudentRepository lessonStudentRepository;
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private LessonService lessonService;

    @Override
    public LessonStudent getLessonStudentByLessonIdAndStudentId(Long lessonId, Long studentId) {
        try{
            return lessonStudentRepository.findByLessonIdAndStudentId(lessonId, studentId);
        } catch (Exception e) {
            throw new ResourceNotFoundException(
                    "LessonStudent not found with lessonId" + lessonId +
                            " and studentId" + studentId);
        }
    }


    @Override
    public LessonStudent getLessonStudentById(Long lessonStudentId) {
        return lessonStudentRepository.findById(lessonStudentId)
                .orElseThrow(()-> new ResourceNotFoundException("LessonStudent", "Id", lessonStudentId));
    }


    @Override
    //@Transactional(propagation = Propagation.NESTED)
    public LessonStudent createLessonStudent(LessonStudent lessonStudent) {
        Long lessonId = lessonStudent.getLesson().getId();
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(()-> new ResourceNotFoundException("Lesson", "Id", lessonId));

        Long studentId = lessonStudent.getStudent().getId();
        Student student = studentRepository.findById(studentId)
                .orElseThrow(()-> new ResourceNotFoundException("Student", "Id", studentId));

        if(lessonStudentRepository.existsByLessonIdAndStudentId(lessonId, studentId)!=null){
            throw new IllegalArgumentException("You are already part of this lesson");
        }

        if(lesson.getContador()==lesson.getLessonType().getQuantity()){
            throw new IllegalArgumentException("The lesson is full");
        }

        /*
        lessonRepository.findById(lessonId)
                .map(lessonUpdated -> {
                    lessonUpdated.setVacants(lessonUpdated.getVacants()-1);
                    return lessonRepository.save(lessonUpdated);
                });
        */
        /*
        short num = 1;
        lessonRepository.setLessonVacants(lessonId,num );
        lessonRepository.save(lesson);
        */

        //lessonService.updateLessonVacants(lessonId, 1);

        lesson.setContador(lesson.getContador()+1);
        return lessonStudentRepository.save(lessonStudent);
    }

    @Override
    public LessonStudent updateLessonStudent(Long lessonStudentId, LessonStudent lessonStudentDetails) {
        return lessonStudentRepository.findById(lessonStudentId)
                .map(lessonStudent -> {
                    lessonStudent.setLesson(lessonStudentDetails.getLesson());
                    return lessonStudentRepository.save(lessonStudent);
                }).orElseThrow(()-> new ResourceNotFoundException("LessonStudent", "Id", lessonStudentId));
    }

    @Override
    public LessonStudent updateAssistance(Long lessonId, Long studentId, Boolean assistance){

        LessonStudent lessonStudent = lessonStudentRepository.findByLessonIdAndStudentId(lessonId, studentId);
        if(lessonStudent==null){
            throw new ResourceNotFoundException("Lesson Student not found with lesson id"+
                    lessonId + "and student id" + studentId);
        }
        lessonStudent.setAssistance(assistance);
        return lessonStudentRepository.save(lessonStudent);
    }

    @Override
    public ResponseEntity<?> deleteLessonStudent(Long lessonStudentId) {
        return lessonStudentRepository.findById(lessonStudentId)
                .map(lessonStudent -> {
                    lessonStudentRepository.delete(lessonStudent);
                    return ResponseEntity.ok().build();
                }).orElseThrow(()->new ResourceNotFoundException("LessonStudent", "Id", lessonStudentId));
    }

    @Override
    public List<LessonStudent> getAllLessonStudentsByLessonId(Long lessonId){
        return lessonStudentRepository.getAllByLessonId(lessonId);
    }

    @Override
    public List<LessonStudent> getAllLessonStudentsByLessonIdAndComplaint(Long lessonId, Boolean isComplaint){
        return lessonStudentRepository.getAllByLessonIdAndComplaint(lessonId, isComplaint);
    }

    @Override
    public List<LessonStudent> getLessonStudentByLessonTypeIdAndTutorId(Long lessonTypeId, Long tutorId) {
        return lessonStudentRepository.getAllByLesson_LessonTypeIdAndLesson_TutorId(lessonTypeId, tutorId);
    }

    @Override
    public List<LessonStudent> getLessonStudentByLessonTypeIdAndTutorIdAndCourseId(Long lessonTypeId, Long tutorId, Long courseId) {
        return lessonStudentRepository.getAllByLesson_LessonTypeIdAndLesson_TutorIdAndLesson_CourseId(lessonTypeId, tutorId, courseId);
    }
}
