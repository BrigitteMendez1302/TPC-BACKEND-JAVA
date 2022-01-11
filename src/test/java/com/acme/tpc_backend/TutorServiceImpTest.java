package com.acme.tpc_backend;

import com.acme.tpc_backend.domain.model.*;
import com.acme.tpc_backend.domain.repository.TutorRepository;
import com.acme.tpc_backend.domain.service.TutorService;
import com.acme.tpc_backend.exception.ResourceNotFoundException;
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
public class TutorServiceImpTest {

    @MockBean
    private TutorRepository tutorRepository;

    @Autowired
    private TutorService tutorService;

    @TestConfiguration
    static class TutorServiceImplTestConfiguration {
        @Bean
        public TutorService tutorService() {
            return new TutorServiceImpl();
        }
    }

    @Test

    @DisplayName("When Create with account a Tutor then returns a tutor")
    public void WhenCreateaTutorWithAccountThenResturnsATutor() {
        // Arrange

        Account account = new Account();
        Career career = new Career();
        Faculty faculty = new Faculty();

        Tutor tutor = new Tutor(account,"Car","Ville","caro@gmail.com",1L,faculty);
        when(tutorRepository.findByAccount(account))
                .thenReturn(Optional.of(tutor));

        // Act
        Tutor foundTutor = tutorService.getTutorByAccount(account);

        // Assert
        assertThat(foundTutor.getAccount()).isEqualTo(account);
    }

    @Test
    @DisplayName("When Create with account a Tutor then returns a tutor")
    public void  WhenCreateaTutorWithAccountThenReturnsResourceNotFoundException() {
        // Arrange

        String template = "Resource %s not found for %s with value %s";
        Account account = new Account();
        Career career = new Career();
        Faculty faculty = new Faculty();

        Tutor tutor = new Tutor(account,"Car","Ville","caro@gmail.com",1L,faculty);
        when(tutorRepository.findByAccount(account))
                .thenReturn(Optional.empty());

        String expectedMessage = String.format(template, "Tutor", "Account", account);
        // Act
        Throwable exception = catchThrowable(() -> {
            Tutor foundTutor = tutorService.getTutorByAccount(account);
        });

        // Assert
        Assertions.assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }


    @Test
    @DisplayName("When Create a Tutor then returns a tutor")
    public void WhenUpdateaTutorWithAccountThenResturnsATutor() {
        // Arrange

        Account account = new Account();
        Career career = new Career();
        Faculty faculty = new Faculty();

        Tutor tutor = new Tutor(account,"Car","Ville","caro@gmail.com",1L,faculty);
        when(tutorRepository.findByAccount(account))
                .thenReturn(Optional.of(tutor));

        // Act
        Tutor createTutor = tutorService.createTutor(tutor);

        // Assert
        assertThat(tutor).isNotNull();
    }

    @Test
    @DisplayName("When Update a Tutor then returns a tutor")
    public void  WhenUpdateaTutorWithAccountThenReturnsResourceNotFoundException() {


        // Arrange
        String name ="oa";
        String template = "Resource %s not found for %s with value %s";

        Account account = new Account();
        Career career = new Career();
        Faculty faculty = new Faculty();

        Tutor newtutor = new Tutor(account,"Car","Ville","caro@gmail.com",1L,faculty);


        Tutor tutor = new Tutor(account,"Car","Ville","caro@gmail.com",1L,faculty);
        when(tutorRepository.findByAccount(account))
                .thenReturn(Optional.empty());


        String expectedMessage = String.format(template, "Tutor", "Id", account.getId());
        // Act
        Throwable exception = catchThrowable(() -> {
            User updateTutor = tutorService.updateTutor(tutor.getId(), tutor).setFirstName(name);
        });

        // Assert
        Assertions.assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);


    }


    @Test
    @DisplayName("When Get a Tutor then returns a tutors")
    public void  WhenGeteaTutorWithAccountThenReturnsResourceNotFoundException() {


        // Arrange

        String template = "Resource %s not found for %s with value %s";
        Account account = new Account();
        Career career = new Career();
        Faculty faculty = new Faculty();

        Tutor tutor = new Tutor(account,"Car","Ville","caro@gmail.com",1L,faculty);
        when(tutorRepository.findByAccount(account))
                .thenReturn(Optional.empty());


        String expectedMessage = String.format(template,"Tutor", "Id", tutor.getId());
        // Act
        Throwable exception = catchThrowable(() -> {
            Tutor updateTutor = tutorService.getTutorById(tutor.getId());
        });

        // Assert
        Assertions.assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);


    }


    @Test
    @DisplayName("When Eliminate a Tutor then returns a tutors")
    public void  WhenEliminateeaTutorWithAccountThenReturnsResourceNotFoundException() {


        // Arrange

        String template = "Resource %s not found for %s with value %s";
        Account account = new Account();
        Career career = new Career();
        Faculty faculty = new Faculty();

        Tutor tutor = new Tutor(account,"Car","Ville","caro@gmail.com",1L,faculty);
        when(tutorRepository.findByAccount(account))
                .thenReturn(Optional.empty());


        String expectedMessage = String.format(template,"Tutor", "Id", tutor.getId());
        // Act
        Throwable exception = catchThrowable(() -> {
            Tutor deleteTutor = tutorService.getTutorById(tutor.getId());
        });

        // Assert
        Assertions.assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);


    }
}