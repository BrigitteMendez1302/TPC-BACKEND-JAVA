package com.acme.tpc_backend.domain.service;
 import com.acme.tpc_backend.domain.model.*;
 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.Pageable;
 import org.springframework.http.ResponseEntity;
 
 public interface StudentService{
 	Student getStudentById(Long studentId);
     Student getStudentByAccount(Account accountId);
 	Student createStudent(Student student);
 	Student updateStudent(Long StudentId, Student studentDetails);
 	ResponseEntity<?> deleteStudent(Long studentId);
 	Page<Student> getAllStudents(Pageable pageable);
 	Page<LessonStudent> getAllStudentsByLessonId(Long lessonId, Pageable pageable);
 	Page<LessonStudent> getStudentsAssistantsByLessonId(Long lessonId, Pageable pageable);
 	Student updateStudentRole(Long StudentId, Student studentDetails);
 }