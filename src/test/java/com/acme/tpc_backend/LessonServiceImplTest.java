package com.acme.tpc_backend;


import com.acme.tpc_backend.domain.model.*;
import com.acme.tpc_backend.domain.repository.CoordinatorRepository;
import com.acme.tpc_backend.domain.repository.LessonRepository;
import com.acme.tpc_backend.domain.repository.StudentRepository;
import com.acme.tpc_backend.domain.repository.TutorRepository;
import com.acme.tpc_backend.domain.service.CoordinatorService;
import com.acme.tpc_backend.domain.service.LessonService;
import com.acme.tpc_backend.domain.service.TutorService;
import com.acme.tpc_backend.exception.ResourceNotFoundException;
import com.acme.tpc_backend.service.CoordinatorServiceImpl;
import com.acme.tpc_backend.service.LessonServiceImpl;
import com.acme.tpc_backend.service.TutorServiceImpl;
import io.cucumber.java8.Fa;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class LessonServiceImplTest {


    @MockBean
    private LessonRepository lessonRepository;

    @Autowired
    private LessonService lessonService;

    @MockBean
    private TutorRepository tutorRepository;

    @MockBean
    private StudentRepository studentRepository;

    @TestConfiguration
    static class LessonServiceImplTestConfiguration {
        @Bean
        public LessonService lessonService() {
            return new LessonServiceImpl();
        }

    }

    @Test

    @DisplayName("When Create a Lesson then returns a lesson")
    public void WhenCreateaLessonWithAccountThenResturnsALesson() {
        // Arrange


        Course course = new Course();
        List<LessonStudent> lessonStudents =new ArrayList<>();
        Tutor tutor = new Tutor();
        LessonType lessonType = new LessonType();
        Lesson lesson = new Lesson(lessonStudents, tutor, lessonType, course, 1) ;
        when(lessonRepository.findByCourse(course))
                .thenReturn(Optional.of(lesson));


    }

    @Test
    @DisplayName("When Create a Lesson then returns a lesson")
    public void  WhenCreateaLessonWithAccountThenReturnsResourceNotFoundException() {
        // Arrange

        String template = "Resource %s not found for %s with value %s";
        Course course = new Course();

        Lesson lesson = new Lesson().setCourse(course);

        when(lessonRepository.findByCourse(course))
                .thenReturn(Optional.empty());

        String expectedMessage = String.format(template, "Lesson", "Id", lesson.getId());
        // Act
        Throwable exception = catchThrowable(() -> {
            Lesson foundLesson = lessonService.getLessonById(lesson.getId());
        });

        // Assert
        Assertions.assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When Create a Lesson then returns a lesson")
    public void WhenUpdateaLessonWithAccountThenResturnsALesson() {
        // Arrange

        Course course = new Course();
        String template = "Resource %s not found for %s with value %s";
        Lesson lesson = new Lesson().setCourse(course);
        when(lessonRepository.findByCourse(course))
                .thenReturn(Optional.empty());
        Tutor tutor = new Tutor();

        String expectedMessage = String.format(template,  "Tutor", "Id", tutor.getId());
        // Act
        Throwable exception = catchThrowable(() -> {
        Lesson createLesson = lessonService.createLesson(tutor.getId(), lesson);
        });
        // Assert
        Assertions.assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When Update a Lesson then returns a lesson")
    public void  WhenUpdateaLessonWithAccountThenReturnsResourceNotFoundException() {


        // Arrange
        String description ="oa";
        String template = "Resource %s not found for %s with value %s";
        Lesson newlesson = new Lesson();
        Course course = new Course();

        Lesson lesson = new Lesson().setCourse(course);
        when(lessonRepository.findByCourse(course))
                .thenReturn(Optional.empty());


        String expectedMessage = String.format(template, "Lesson", "Id", course.getId());
        // Act
        Throwable exception = catchThrowable(() -> {
            Meeting updateLesson = lessonService.updateLesson(lesson.getId(), lesson).setDescription(description);
        });

        // Assert
        Assertions.assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);


    }


    @Test
    @DisplayName("When Get a Lesson then returns a lessons")
    public void  WhenGeteaLessonWithAccountThenReturnsResourceNotFoundException() {


        // Arrange

        String template = "Resource %s not found for %s with value %s";
        Course course = new Course();

        Lesson lesson = new Lesson().setCourse(course);
        when(lessonRepository.findByCourse(course))
                .thenReturn(Optional.empty());


        String expectedMessage = String.format(template,"Lesson", "Id", lesson.getId());
        // Act
        Throwable exception = catchThrowable(() -> {
            Lesson updateLesson = lessonService.getLessonById(lesson.getId());
        });

        // Assert
        Assertions.assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);


    }


    @Test
    @DisplayName("When Eliminate a Lesson then returns a lessons")
    public void  WhenEliminateeaLessonWithAccountThenReturnsResourceNotFoundException() {


        // Arrange

        String template = "Resource %s not found for %s with value %s";
        Course course = new Course();

        Lesson lesson = new Lesson().setCourse(course);
        when(lessonRepository.findByCourse(course))
                .thenReturn(Optional.empty());


        String expectedMessage = String.format(template,"Lesson", "Id", lesson.getId());
        // Act
        Throwable exception = catchThrowable(() -> {
            Lesson deleteLesson = lessonService.getLessonById(lesson.getId());
        });

        // Assert
        Assertions.assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);


    }
}
