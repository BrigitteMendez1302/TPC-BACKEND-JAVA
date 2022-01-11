package com.acme.tpc_backend;

import com.acme.tpc_backend.domain.model.*;
import com.acme.tpc_backend.domain.repository.CoordinatorRepository;
import com.acme.tpc_backend.domain.repository.StudentRepository;
import com.acme.tpc_backend.domain.repository.TrainingRepository;
import com.acme.tpc_backend.domain.repository.TutorRepository;
import com.acme.tpc_backend.domain.service.CoordinatorService;
import com.acme.tpc_backend.domain.service.TrainingService;
import com.acme.tpc_backend.domain.service.TutorService;
import com.acme.tpc_backend.exception.ResourceNotFoundException;
import com.acme.tpc_backend.service.CoordinatorServiceImpl;
import com.acme.tpc_backend.service.TrainingServiceImpl;
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

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class TrainingServiceImplTest {
    @MockBean
    private TrainingRepository trainingRepository;

    @Autowired
    private TrainingService trainingService;

    @MockBean
    private TutorRepository tutorRepository;

    @MockBean
    private StudentRepository studentRepository;

    @TestConfiguration
    static class TrainingServiceImplTestConfiguration {
        @Bean
        public TrainingService trainingService() {
            return new TrainingServiceImpl();
        }

    }

    @Test

    @DisplayName("When Create a Training then returns a training")
    public void WhenCreateaTrainingWithAccountThenResturnsATraining() {
        // Arrange



        Training training = new Training() ;
        when(trainingRepository.findMeetingById(training.getId()))
                .thenReturn(Optional.of(training));


    }

    @Test
    @DisplayName("When Create a Training then returns a training")
    public void  WhenCreateaTrainingWithAccountThenReturnsResourceNotFoundException() {
        // Arrange

        String template = "Resource %s not found for %s with value %s";

        Training training = new Training() ;
        when(trainingRepository.findMeetingById(training.getId()))
                .thenReturn(Optional.of(training));
        String expectedMessage = String.format(template, "Training", "Id", training.getId());
        // Act
        Throwable exception = catchThrowable(() -> {
            Meeting foundTraining = trainingService.getTrainingById(training.getId());
        });

        // Assert
        Assertions.assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When Create a Training then returns a training")
    public void WhenUpdateaTrainingWithAccountThenResturnsATraining() {
        // Arrange

        Course course = new Course();
        String template = "Resource %s not found for %s with value %s";
        Training training = new Training() ;
        when(trainingRepository.findMeetingById(training.getId()))
                .thenReturn(Optional.of(training));
        Tutor tutor = new Tutor();

        String expectedMessage = String.format(template,  "Training", "Id", tutor.getId());
        // Act

            Training createTraining = trainingService.createTraining( training);
           assertThat(createTraining).isNull();
    }

    @Test
    @DisplayName("When Update a Training then returns a training")
    public void  WhenUpdateaTrainingWithAccountThenReturnsResourceNotFoundException() {


        // Arrange
        String description ="oa";
        String template = "Resource %s not found for %s with value %s";
        Training newtraining = new Training();
        Course course = new Course();

        Training training = new Training() ;
        when(trainingRepository.findMeetingById(training.getId()))
                .thenReturn(Optional.empty());


        String expectedMessage = String.format(template, "Training", "Id", course.getId());
        // Act
        Throwable exception = catchThrowable(() -> {
            Meeting updateTraining = trainingService.updateTraining(training.getId(), training).setDescription(description);
        });

        // Assert
        Assertions.assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);


    }


    @Test
    @DisplayName("When Get a Training then returns a trainings")
    public void  WhenGeteaTrainingWithAccountThenReturnsResourceNotFoundException() {


        // Arrange

        String template = "Resource %s not found for %s with value %s";
        Course course = new Course();

        Training training = new Training() ;
        when(trainingRepository.findMeetingById(training.getId()))
                .thenReturn(Optional.empty());


        String expectedMessage = String.format(template,"Training", "Id", training.getId());
        // Act
        Throwable exception = catchThrowable(() -> {
            Meeting updateTraining = trainingService.getTrainingById(training.getId());
        });

        // Assert
        Assertions.assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);


    }


    @Test
    @DisplayName("When Eliminate a Training then returns a trainings")
    public void  WhenEliminateeaTrainingWithAccountThenReturnsResourceNotFoundException() {


        // Arrange

        String template = "Resource %s not found for %s with value %s";
        Course course = new Course();

        Training training = new Training() ;
        when(trainingRepository.findMeetingById(training.getId()))
                .thenReturn(Optional.empty());


        String expectedMessage = String.format(template,"Training", "Id", training.getId());
        // Act
        Throwable exception = catchThrowable(() -> {
            Meeting deleteTraining = trainingService.getTrainingById(training.getId());
        });

        // Assert
        Assertions.assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);


    }
}
