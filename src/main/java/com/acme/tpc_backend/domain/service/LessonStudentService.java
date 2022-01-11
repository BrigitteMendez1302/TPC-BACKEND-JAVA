package com.acme.tpc_backend.domain.service;
 import com.acme.tpc_backend.domain.model.LessonStudent;
 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.Pageable;
 import org.springframework.http.ResponseEntity;

 import java.util.List;

public interface LessonStudentService {
 	LessonStudent getLessonStudentById(Long lessonStudentId);
 	LessonStudent createLessonStudent(LessonStudent lessonStudent);
 	LessonStudent updateLessonStudent(Long LessonStudentId, LessonStudent lessonStudentDetails);
 	ResponseEntity<?> deleteLessonStudent(Long lessonStudentId);
 	List<LessonStudent> getAllLessonStudentsByLessonId(Long lessonId);
    List<LessonStudent> getAllLessonStudentsByLessonIdAndComplaint(Long lessonId, Boolean isComplaint);
    LessonStudent getLessonStudentByLessonIdAndStudentId(Long lessonId, Long studentId);
    //
    List<LessonStudent> getLessonStudentByLessonTypeIdAndTutorId(Long lessonTypeId, Long tutorId);
    List<LessonStudent> getLessonStudentByLessonTypeIdAndTutorIdAndCourseId(Long lessonTypeId, Long tutorId, Long courseId);
    LessonStudent updateAssistance(Long lessonId, Long studentId, Boolean assistance);
}