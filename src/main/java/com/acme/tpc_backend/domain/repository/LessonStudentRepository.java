package com.acme.tpc_backend.domain.repository;

import com.acme.tpc_backend.domain.model.Lesson;
import com.acme.tpc_backend.domain.model.LessonStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface LessonStudentRepository extends JpaRepository<LessonStudent, Long> {
    @Query("select ls from LessonStudent ls where ls.lesson.id = :#{#lessonId} and ls.student.id = :#{#studentId}")
    LessonStudent existsByLessonIdAndStudentId(Long lessonId, Long studentId);
    List<LessonStudent> getAllByLessonId(Long lessonId);
    @Query("select ls from LessonStudent ls where ls.lesson.id = :#{#lessonId} and ls.complaint = :#{#isComplaint}")
    List<LessonStudent> getAllByLessonIdAndComplaint(Long lessonId, Boolean isComplaint);
    LessonStudent findByLessonIdAndStudentId(Long lessonId, Long studentId);
    //
    @Query("select ls from LessonStudent ls where ls.lesson.lessonType.id = :#{#lessonTypeId} and ls.lesson.tutor.id = :#{#tutorId}")
    List<LessonStudent> getAllByLesson_LessonTypeIdAndLesson_TutorId(Long lessonTypeId, Long tutorId);
    @Query("select ls from LessonStudent ls where ls.lesson.lessonType.id = :#{#lessonTypeId} and ls.lesson.tutor.id = :#{#tutorId} and ls.lesson.course.id = :#{#courseId}")
    List<LessonStudent> getAllByLesson_LessonTypeIdAndLesson_TutorIdAndLesson_CourseId(Long lessonTypeId, Long tutorId, Long courseId);
    
}