package com.acme.tpc_backend;

import com.acme.tpc_backend.domain.model.*;
import com.acme.tpc_backend.domain.repository.CoordinatorRepository;
import com.acme.tpc_backend.domain.repository.TutorRepository;
import com.acme.tpc_backend.domain.service.CoordinatorService;
import com.acme.tpc_backend.domain.service.TutorService;
import com.acme.tpc_backend.exception.ResourceNotFoundException;
import com.acme.tpc_backend.service.CoordinatorServiceImpl;
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
public class CoordinatorImplTest {
    @MockBean
    private CoordinatorRepository coordinatorRepository;

    @Autowired
    private CoordinatorService coordinatorService;

    @TestConfiguration
    static class CoordinatorServiceImplTestConfiguration {
        @Bean
        public CoordinatorService coordinatorService() {
            return new CoordinatorServiceImpl();
        }
    }

    @Test

    @DisplayName("When Create with account a Coordinator then returns a coordinator")
    public void WhenCreateaCoordinatorWithAccountThenResturnsACoordinator() {
        // Arrange

        Account account = new Account();
        Career career = new Career();
        Faculty faculty = new Faculty();

        Coordinator coordinator = new Coordinator(account,"Car","Ville","caro@gmail.com",1L,faculty);
        when(coordinatorRepository.findByAccount(account))
                .thenReturn(Optional.of(coordinator));

        // Act
        Coordinator foundCoordinator = coordinatorService.getCoordinatorByAccount(account);

        // Assert
        assertThat(foundCoordinator.getAccount()).isEqualTo(account);
    }

    @Test
    @DisplayName("When Create with account a Coordinator then returns a coordinator")
    public void  WhenCreateaCoordinatorWithAccountThenReturnsResourceNotFoundException() {
        // Arrange

        String template = "Resource %s not found for %s with value %s";
        Account account = new Account();
        Career career = new Career();
        Faculty faculty = new Faculty();

        Coordinator coordinator = new Coordinator(account,"Car","Ville","caro@gmail.com",1L,faculty);
        when(coordinatorRepository.findByAccount(account))
                .thenReturn(Optional.empty());

        String expectedMessage = String.format(template, "Coordinator", "Account", account);
        // Act
        Throwable exception = catchThrowable(() -> {
            Coordinator foundCoordinator = coordinatorService.getCoordinatorByAccount(account);
        });

        // Assert
        Assertions.assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

@Test
    @DisplayName("When Create a Coordinator then returns a coordinator")
    public void WhenUpdateaCoordinatorWithAccountThenResturnsACoordinator() {
        // Arrange

        Account account = new Account();
        Career career = new Career();
        Faculty faculty = new Faculty();

        Coordinator coordinator = new Coordinator(account,"Car","Ville","caro@gmail.com",1L,faculty);
        when(coordinatorRepository.findByAccount(account))
                .thenReturn(Optional.of(coordinator));

        // Act
        Coordinator createCoordinator = coordinatorService.createCoordinator(coordinator);

        // Assert
        assertThat(coordinator).isNotNull();
    }


    @DisplayName("When Update a Coordinator then returns a coordinator")
    public void  WhenUpdateaCoordinatorWithAccountThenReturnsResourceNotFoundException() {


        // Arrange
        String name ="oa";
        String template = "Resource %s not found for %s with value %s";

        Account account = new Account();
        Career career = new Career();
        Faculty faculty = new Faculty();

        Coordinator newcoordinator = new Coordinator(account,"Car","Ville","caro@gmail.com",1L,faculty);


        Coordinator coordinator = new Coordinator(account,"Car","Ville","caro@gmail.com",1L,faculty);
        when(coordinatorRepository.findByAccount(account))
                .thenReturn(Optional.empty());


        String expectedMessage = String.format(template, "Coordinator", "Id", account);
        // Act
        Throwable exception = catchThrowable(() -> {
            User updateCoordinator = coordinatorService.updateCoordinator(coordinator.getId(), coordinator).setFirstName(name);
        });

        // Assert
        Assertions.assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);


    }



    @DisplayName("When Get a Coordinator then returns a coordinators")
    public void  WhenGeteaCoordinatorWithAccountThenReturnsResourceNotFoundException() {


        // Arrange

        String template = "Resource %s not found for %s with value %s";
        Account account = new Account();
        Career career = new Career();
        Faculty faculty = new Faculty();

        Coordinator coordinator = new Coordinator(account,"Car","Ville","caro@gmail.com",1L,faculty);
        when(coordinatorRepository.findByAccount(account))
                .thenReturn(Optional.empty());


        String expectedMessage = String.format(template,"Coordinator", "Id", coordinator.getId());
        // Act
        Throwable exception = catchThrowable(() -> {
            Coordinator updateCoordinator = coordinatorService.getCoordinatorById(coordinator.getId());
        });

        // Assert
        Assertions.assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);


    }


    @Test
    @DisplayName("When Eliminate a Coordinator then returns a coordinators")
    public void  WhenEliminateeaCoordinatorWithAccountThenReturnsResourceNotFoundException() {


        // Arrange

        String template = "Resource %s not found for %s with value %s";
        Account account = new Account();
        Career career = new Career();
        Faculty faculty = new Faculty();

        Coordinator coordinator = new Coordinator(account,"Car","Ville","caro@gmail.com",1L,faculty);
        when(coordinatorRepository.findByAccount(account))
                .thenReturn(Optional.empty());


        String expectedMessage = String.format(template,"Coordinator", "Id", coordinator.getId());
        // Act
        Throwable exception = catchThrowable(() -> {
            Coordinator deleteCoordinator = coordinatorService.getCoordinatorById(coordinator.getId());
        });

        // Assert
        Assertions.assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);


    }
}

