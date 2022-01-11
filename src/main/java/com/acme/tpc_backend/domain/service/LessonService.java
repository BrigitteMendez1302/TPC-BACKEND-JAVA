package com.acme.tpc_backend.domain.service;
 import com.acme.tpc_backend.domain.model.*;
 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.Pageable;
 import org.springframework.http.ResponseEntity;

 import java.util.Date;
 import java.util.List;

public interface LessonService{
 	Lesson getLessonById(Long lessonId);
 	Lesson createLesson(Long tutorId, Lesson lesson);
 	Lesson updateLesson(Long LessonId, Lesson lessonDetails);
    Lesson updateByTutor(Long LessonId, Date start, Date end);
 	ResponseEntity<?> deleteLesson(Long lessonId);
 	Page<LessonStudent> getAllLessonsByStudentId(Long studentId, Pageable pageable);
    List<Lesson> getAllInRange(Date start, Date end);
    Lesson getLessonByCourse(Course courseId);
    Page<Lesson> getAllLessonsByLessonTypeId(Long lessonTypeId,Pageable pageable);
    Lesson updateLessonVacants(Long LessonId, int lessVacants);
    Page<Lesson> getAllLessons(Pageable pageable);
    List<Lesson> getAllLessonsInRangeByTutorIdAndLessonTypeId(Date start, Date end, Long tutorId, Long lessonTypeId);
    Lesson updateMeetingLink(Long lessonId, String meetingLink);
    List<Lesson> getAllLessonsByLessonTypeId(Long lessonTypeId);
    Lesson updateCalendarId(Long lessonId, String calendarId);
}