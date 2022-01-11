package com.acme.tpc_backend.service;

import com.acme.tpc_backend.domain.model.*;
import com.acme.tpc_backend.domain.repository.StudentRepository;
import com.acme.tpc_backend.domain.repository.UserRepository;
import com.acme.tpc_backend.domain.service.StudentService;
import com.acme.tpc_backend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.acme.tpc_backend.exception.ResourceNotFoundException;
import org.springframework.data.domain.PageImpl;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;



    @Override
    public Student getStudentById(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(()-> new ResourceNotFoundException("Student", "Id", studentId));
    }

    @Override
    public Student getStudentByAccount(Account accountId) {
        return studentRepository.findByAccount(accountId)
                .orElseThrow(()-> new ResourceNotFoundException("Student", "Account", accountId));
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Long StudentId, Student studentDetails) {
        Student student = studentRepository.findById(StudentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "Id", StudentId));
        return studentRepository.save(
                student.setCycleNumber(studentDetails.getCycleNumber()));

    }

    @Override
    public ResponseEntity<?> deleteStudent(Long studentId) {
        return studentRepository.findById(studentId).map(student -> {
            studentRepository.delete(student);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Student", "Id", studentId));
    }

    @Override
    public Page<Student> getAllStudents(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public Page<LessonStudent> getAllStudentsByLessonId(Long lessonId, Pageable pageable) {
        return studentRepository.findById(lessonId).map(student -> {
            List<LessonStudent> students = student.getLessonStudents();
            int lessonsCount = students.size();
            return new PageImpl<>(students, pageable, lessonsCount); })
                .orElseThrow(() -> new ResourceNotFoundException("Lesson", "Id", lessonId));
    }

    @Override
    public Page<LessonStudent> getStudentsAssistantsByLessonId(Long lessonId, Pageable pageable) {
        return studentRepository.findById(lessonId).map(student -> {

            List<LessonStudent> students = student.getLessonStudents().stream()
                    .filter(c->c.isAssistance()==true).collect(Collectors.toList());

            int lessonsCount = students.size();
            return new PageImpl<>(students, pageable, lessonsCount); })
                .orElseThrow(() -> new ResourceNotFoundException("Lesson", "Id", lessonId));
    }

    @Override
    public Student updateStudentRole(Long StudentId, Student studentDetails) {
        Student student = studentRepository.findById(StudentId)
                .orElseThrow(()-> new ResourceNotFoundException("Student", "Id", StudentId));

        student.setRole(studentDetails.getRole());
        return studentRepository.save(student);
    }
}
