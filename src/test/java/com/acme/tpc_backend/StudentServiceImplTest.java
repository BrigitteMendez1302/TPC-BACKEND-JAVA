package com.acme.tpc_backend;

import com.acme.tpc_backend.domain.model.*;
import com.acme.tpc_backend.domain.repository.StudentRepository;
import com.acme.tpc_backend.domain.service.StudentService;
import com.acme.tpc_backend.exception.ResourceNotFoundException;
import com.acme.tpc_backend.service.StudentServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class StudentServiceImplTest {

    @MockBean
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    @TestConfiguration
    static class StudentServiceImplTestConfiguration {
        @Bean
        public StudentService studentService() {
            return new StudentServiceImpl();
        }
    }

    @Test

    @DisplayName("When Create with account a Student then returns a student")
    public void WhenCreateaStudentWithAccountThenResturnsAStudent() {
        // Arrange

        Account account = new Account();
        Career career = new Career();
        List<LessonStudent> lessonStudentList = new ArrayList<>();
        Student student = new Student(account,"Car","Ville","caro@gmail.com",1L,1,career,lessonStudentList);
        when(studentRepository.findByAccount(account))
                .thenReturn(Optional.of(student));

        // Act
        Student foundStudent = studentService.getStudentByAccount(account);

        // Assert
        assertThat(foundStudent.getAccount()).isEqualTo(account);
    }

    @Test
    @DisplayName("When Create with account a Student then returns a student")
    public void  WhenCreateaStudentWithAccountThenReturnsResourceNotFoundException() {
        // Arrange

        String template = "Resource %s not found for %s with value %s";
        Account account = new Account();
        Career career = new Career();
        List<LessonStudent> lessonStudentList = new ArrayList<>();
        Student student = new Student(account,"Car","Ville","caro@gmail.com",1L,1,career,lessonStudentList);
        when(studentRepository.findByAccount(account))
                .thenReturn(Optional.empty());

        String expectedMessage = String.format(template, "Student", "Account", account);
        // Act
        Throwable exception = catchThrowable(() -> {
            Student foundStudent = studentService.getStudentByAccount(account);
        });

        // Assert
        Assertions.assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }


    @Test
    @DisplayName("When Create a Student then returns a student")
    public void WhenUpdateaStudentWithAccountThenResturnsAStudent() {
        // Arrange

        Account account = new Account();
        Career career = new Career();
        List<LessonStudent> lessonStudentList = new ArrayList<>();
        Student student = new Student(account,"Car","Ville","caro@gmail.com",1L,1,career,lessonStudentList);
        when(studentRepository.findByAccount(account))
                .thenReturn(Optional.of(student));

        // Act
        Student createStudent = studentService.createStudent(student);

        // Assert
        assertThat(student).isNotNull();
    }

    @Test
    @DisplayName("When Update a Student then returns a student")
    public void  WhenUpdateaStudentWithAccountThenReturnsResourceNotFoundException() {


        // Arrange
        int cicle =3;
        String template = "Resource %s not found for %s with value %s";
        List<LessonStudent> lessonStudentList = new ArrayList<>();
        Career career = new Career();
        Account account = new Account();
        Student newStudent =new Student(account,"Car","Ville","caro@gmail.com",1L,1,career,lessonStudentList);


        Student student = new Student(account,"Car","Ville","caro@gmail.com",1L,1,career,lessonStudentList);
        when(studentRepository.findByAccount(account))
                .thenReturn(Optional.empty());


        String expectedMessage = String.format(template, "Student", "Id", account);
        // Act
        Throwable exception = catchThrowable(() -> {
            Student updateStudent = studentService.updateStudent(student.getId(), newStudent).setCycleNumber(cicle);
        });

        // Assert
        Assertions.assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);


    }


    @Test
    @DisplayName("When Get a Student then returns a students")
    public void  WhenGeteaStudentWithAccountThenReturnsResourceNotFoundException() {


        // Arrange

        String template = "Resource %s not found for %s with value %s";
        List<LessonStudent> lessonStudentList = new ArrayList<>();
        Career career = new Career();
        Account account = new Account();

        Student student = new Student(account,"Car","Ville","caro@gmail.com",1L,1,career,lessonStudentList);
        when(studentRepository.findByAccount(account))
                .thenReturn(Optional.empty());


        String expectedMessage = String.format(template,"Student", "Id", student.getId());
        // Act
        Throwable exception = catchThrowable(() -> {
            Student updateStudent = studentService.getStudentById(student.getId());
        });

        // Assert
        Assertions.assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);


    }
}



