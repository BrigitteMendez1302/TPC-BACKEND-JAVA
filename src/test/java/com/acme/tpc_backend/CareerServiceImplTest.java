package com.acme.tpc_backend;

import com.acme.tpc_backend.domain.model.Account;
import com.acme.tpc_backend.domain.model.Career;
import com.acme.tpc_backend.domain.repository.CareerRepository;
import com.acme.tpc_backend.domain.repository.CourseRepository;
import com.acme.tpc_backend.domain.service.CareerService;
import com.acme.tpc_backend.exception.ResourceNotFoundException;
import com.acme.tpc_backend.service.CareerServiceImpl;
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
public class CareerServiceImplTest {
    @MockBean
    private CareerRepository careerRepository;

    @MockBean
    private CourseRepository courseRepository;

    @Autowired
    private CareerService careerService;

    @TestConfiguration
    static class CareerServiceImplTestConfiguration {
        @Bean
        public CareerService careerService() { return new CareerServiceImpl(); }
    }

    @Test
    @DisplayName("when SaveCareer With Valid Career Then Returns Success") //happy path
    public void whenSaveCareerWithValidCareerThenReturnsSuccess() {
        Long id = 1L;
        String name = "Ingenieria";
        Career career = new Career().setId(id).setName(name);
        when(careerRepository.save(career)).thenReturn(career);
        Career saveCareer = careerService.createCareer(career);
        assertThat(saveCareer).isEqualTo(career);
    }

    @Test
    @DisplayName("when Get Career By Id With Invalid Id Then Returns ResourceNotFoundException") //unhappy path
    public void whenGetCareerByIdWithInvalidIdThenReturnsResourceNotFoundException() {
        //Arrange
        Long id = 1L;
        String template = "Resource %s not found for %s with value %s";
        when(careerRepository.findById(id)).thenReturn(Optional.empty());
        String exceptedMessage = String.format(template, "Career", "Id", id);
        //Act
        Throwable exception = catchThrowable(() ->{
            Career foundCareer = careerService.getCareerById(id);
        });
        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(exceptedMessage);
    }

}
