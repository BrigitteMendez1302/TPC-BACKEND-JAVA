package com.acme.tpc_backend.service;

import com.acme.tpc_backend.domain.model.Course;
import com.acme.tpc_backend.domain.model.Faculty;
import com.acme.tpc_backend.domain.model.Lesson;
import com.acme.tpc_backend.domain.repository.LessonRepository;
import com.acme.tpc_backend.domain.repository.TutorRepository;
import com.acme.tpc_backend.domain.service.LessonService;
import com.acme.tpc_backend.exception.ResourceNotFoundException;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.acme.tpc_backend.domain.model.LessonStudent;
import com.acme.tpc_backend.domain.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {
    
    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private StudentRepository studentRepository;


    @Override
    public Lesson getLessonById(Long lessonId) {
        return lessonRepository.findById(lessonId)
                .orElseThrow(()-> new ResourceNotFoundException("Lesson", "Id", lessonId));
    }

    @Override
    public Lesson createLesson(Long tutorId, Lesson lesson) {
        return tutorRepository.findById(tutorId).map(
                tutor->
                { lesson.setTutor(tutor);
                    lesson.setContador(0);
                    return lessonRepository.save(lesson);
                }).orElseThrow(()-> new ResourceNotFoundException(
                "Tutor", "Id", tutorId
        ));
    }

    @Override
    public Lesson updateLesson(Long LessonId, Lesson lessonDetails) {
        return lessonRepository.findById(LessonId)
                .map(lesson -> {
                    lesson.setDescription(lessonDetails.getDescription());
                    return lessonRepository.save(lesson);
                }).orElseThrow(()-> new ResourceNotFoundException("Lesson", "Id", LessonId));
    }

    @Override
    public Lesson updateByTutor(Long LessonId, Date start, Date end) {
        return lessonRepository.findById(LessonId)
                .map(lesson -> {
                    lesson.setStartDate(start);
                    lesson.setEndDate(end);
                    return lessonRepository.save(lesson);
                }).orElseThrow(()-> new ResourceNotFoundException("Lesson", "Id", LessonId));
    }

    @Override
    //@Transactional(propagation = Propagation.NESTED)
    public Lesson updateLessonVacants(Long LessonId, int lessVacants) {
        return lessonRepository.findById(LessonId)
                .map(lesson -> {
                    lesson.setVacants(lesson.getVacants()-lessVacants);
                    return lessonRepository.save(lesson);
                }).orElseThrow(()-> new ResourceNotFoundException("Lesson", "Id", LessonId));
    }

    @Override
    public ResponseEntity<?> deleteLesson(Long lessonId) {
        return lessonRepository.findById(lessonId)
                .map(lesson -> {
                    lessonRepository.delete(lesson);
                    return ResponseEntity.ok().build();
                }).orElseThrow(()->new ResourceNotFoundException("Lesson", "Id", lessonId));
    }

    @Override
    public Page<LessonStudent> getAllLessonsByStudentId(Long studentId, Pageable pageable) {
        return studentRepository.findById(studentId).map(student -> {
            List<LessonStudent> lessons = student.getLessonStudents();
            int lessonsCount = lessons.size();
            return new PageImpl<>(lessons, pageable, lessonsCount); })
                .orElseThrow(() -> new ResourceNotFoundException("Student", "Id", studentId));
    }

    @Override
    public Page<Lesson> getAllLessonsByLessonTypeId(Long lessonTypeId, Pageable pageable) {
        return lessonRepository.findByLessonTypeId(lessonTypeId,pageable);
    }

    @Override
    public List<Lesson> getAllInRange(Date start, Date end) {
        return lessonRepository.getLessonsInRange(start, end);
    }

    @Override
    public Lesson getLessonByCourse(Course courseId) {
        return lessonRepository.findByCourse(courseId)
                .orElseThrow(()-> new ResourceNotFoundException("Student", "Course", courseId));
    }

    @Override
    public List<Lesson> getAllLessonsInRangeByTutorIdAndLessonTypeId(Date start, Date end, Long tutorId, Long lessonTypeId){
        return lessonRepository.getLessonsInRangeByTutorIdAndLessonTypeId(start, end, tutorId, lessonTypeId);
    }

    @Override
    public Page<Lesson> getAllLessons(Pageable pageable) {
        return lessonRepository.findAll(pageable);
    }
    @Override
    public Lesson updateMeetingLink(Long lessonId, String meetingLink){
        return lessonRepository.findById(lessonId)
                .map(lesson -> {
                    lesson.setMeetingLink(meetingLink);
                    return lessonRepository.save(lesson);
                }).orElseThrow(()-> new ResourceNotFoundException("Lesson", "Id", lessonId));
    }

    @Override
    public Lesson updateCalendarId(Long lessonId, String calendarId){
        return lessonRepository.findById(lessonId)
                .map(lesson -> {
                    lesson.setCalendarId(calendarId);
                    return lessonRepository.save(lesson);
                }).orElseThrow(()-> new ResourceNotFoundException("Lesson", "Id", lessonId));
    }

    //public Page<Lesson> getAllInRange(Date start, Date end){
    //    Page<Lesson> page = new PageImpl<>(lessonRepository.getLessonsInRange(start, end));
    //    return page;
    //}

    @Override
    public List<Lesson> getAllLessonsByLessonTypeId(Long lessonTypeId){
        return lessonRepository.getLessonsByLessonTypeId(lessonTypeId);
    }
}