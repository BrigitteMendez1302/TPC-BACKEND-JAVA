package com.acme.tpc_backend;

import com.acme.tpc_backend.domain.model.*;
import com.acme.tpc_backend.domain.repository.CareerRepository;
import com.acme.tpc_backend.domain.repository.TrainingTutorRepository;
import com.acme.tpc_backend.domain.service.CareerService;
import com.acme.tpc_backend.domain.service.TrainingTutorService;
import com.acme.tpc_backend.exception.ResourceNotFoundException;
import com.acme.tpc_backend.service.CareerServiceImpl;
import com.acme.tpc_backend.service.TrainingTutorServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class TrainingTutorServiceImplTest {
    @MockBean
    private TrainingTutorRepository trainingTutorRepository;

    @Autowired
    private TrainingTutorService trainingTutorService;

    @TestConfiguration
    static class TrainingTutorServiceImplTestConfiguration {
        @Bean
        public TrainingTutorService trainingTutorService() { return new TrainingTutorServiceImpl(); }
    }

    @Test
    @DisplayName("when SaveTrainingTutor With Valid TrainingTutor Then Returns Success") //happy path
    public void whenSaveTrainingTutorWithValidTrainingTutorThenReturnsSuccess() {
        Long trainingId = 1L;
        Long tutorId = 1L;
        TrainingTutor trainingTutor = new TrainingTutor();
        Training training = new Training();
        Tutor tutor = new Tutor();
        tutor.setId(tutorId);
        training.setId(trainingId);
        trainingTutor.setTraining(training);
        trainingTutor.setTutor(tutor);
        when(trainingTutorRepository.save(trainingTutor)).thenReturn(trainingTutor);
        TrainingTutor saveTrainingTutor = trainingTutorService.createTrainingTutor(trainingTutor);
        assertThat(saveTrainingTutor).isEqualTo(trainingTutor);
    }

    @Test
    @DisplayName("when SaveTrainingTutor With No Valid TrainingTutor Then Returns Success") //happy path
    public void whenSaveTrainingTutorWithInvalidTrainingThenReturnsResponseNotFoundException() {
        String template = "Resource %s not found for %s with value %s";
        String exceptedMessage = String.format(template, "TrainingTutor", "Id", 2L);
        Long trainingId = 1L;
        Long tutorId = 1L;
        TrainingTutor trainingTutor = new TrainingTutor();
        Training training = new Training();
        Tutor tutor = new Tutor();
        tutor.setId(tutorId);
        training.setId(trainingId);
        trainingTutor.setTraining(training);
        trainingTutor.setTutor(tutor);
        when(trainingTutorRepository.findById(2L)).thenReturn(Optional.empty());
        TrainingTutor saveTrainingTutor = trainingTutorService.createTrainingTutor(trainingTutor);
        //assertThat(saveTrainingTutor).isEqualTo(trainingTutor);
        Throwable exception = catchThrowable(() ->{
            TrainingTutor foundTT = trainingTutorService.getTrainingTutorById(2L);
        });
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(exceptedMessage);
    }

}
