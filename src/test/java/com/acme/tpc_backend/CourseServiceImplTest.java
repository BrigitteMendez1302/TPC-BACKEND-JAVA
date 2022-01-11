package com.acme.tpc_backend;

import com.acme.tpc_backend.domain.model.Course;
import com.acme.tpc_backend.domain.repository.CourseRepository;
import com.acme.tpc_backend.domain.repository.UserRepository;
import com.acme.tpc_backend.domain.service.CourseService;
import com.acme.tpc_backend.exception.ResourceNotFoundException;
import com.acme.tpc_backend.service.CourseServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CourseServiceImplTest {
    @MockBean
    private CourseRepository courseRepository;
    @Autowired
    private CourseService courseService;
    @MockBean
    private UserRepository userRepository;

    @TestConfiguration
    static class CourseServiceImplTestConfiguration {
        @Bean
        public CourseService courseService() {
            return new CourseServiceImpl();
        }
    }

    @Test
    @DisplayName("when SaveCourse With Valid Course Then Returns Success") //happy path
    public void whenSaveCourseWithValidCourseThenReturnsSuccess() {
        Long id = 1L;
        String name = "Programacion 1";
        Course course = new Course().setId(id).setName(name);
        when( courseRepository.save(course)).thenReturn(course);
        Course savedCourse = courseService.createCourse(course);
        assertThat(savedCourse).isEqualTo(course);
    }

    @Test
    @DisplayName("when GetCourseById With Valid Id Then Returns Course") //happy path
    public void whenGetCourseByIdWithValidIdThenReturnsCourse() {
        //Arrange
        Long id = 1L;
        Course course = new Course().setId(id);
        when(courseRepository.findById(id)).thenReturn(Optional.of(course));
        //Act
        Course foundCourse = courseService.getCourseById(id);
        //Assert
        assertThat(foundCourse.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("when GetCourseById With Invalid Id Then Returns ResourceNotFoundException") //unhappy path
    public void whenGetCourseByIdWithInvalidIdThenReturnsResourceNotFoundException() {
        //Arrange
        Long id = 1L;
        String template = "Resource %s not found for %s with value %s";
        when(courseRepository.findById(id)).thenReturn(Optional.empty());
        String exceptedMessage = String.format(template, "Course", "Id", id);
        //Act
        Throwable exception = catchThrowable(() ->{
            Course foundCourse = courseService.getCourseById(id);
        });
        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(exceptedMessage);
    }
}
