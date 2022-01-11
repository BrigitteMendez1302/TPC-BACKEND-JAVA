package com.acme.tpc_backend;

import com.acme.tpc_backend.domain.model.LessonType;
import com.acme.tpc_backend.domain.model.LessonType;
import com.acme.tpc_backend.domain.repository.LessonTypeRepository;
import com.acme.tpc_backend.domain.service.LessonTypeService;
import com.acme.tpc_backend.exception.ResourceNotFoundException;
import com.acme.tpc_backend.service.LessonTypeServiceImpl;
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
public class LessonTypeServiceImplTest {
    @MockBean
    private LessonTypeRepository lessonTypeRepository;
    @Autowired
    private LessonTypeService lessonTypeService;

    @TestConfiguration
    static class LessonTypeServiceImplTestConfiguration {
        @Bean
        public LessonTypeService lessonTypeService() {
            return new LessonTypeServiceImpl();
        }
    }

    @Test
    @DisplayName("when SaveLessonType With Valid LessonType Then Returns Success") //happy path
    public void whenSaveLessonTypeWithValidLessonTypeThenReturnsSuccess() {

        Long id = 1L;
        String name = "taller";
        int studentQuantity = 1;
        LessonType lessonType = new LessonType().setId(id)
                .setName(name)
                .setQuantity(studentQuantity);

        when(lessonTypeRepository.save(lessonType)).thenReturn(lessonType);
        LessonType savedLessonType = lessonTypeService.createLessonType(lessonType);
        //Assert

        assertThat(savedLessonType).isEqualTo(lessonType);
    }

    @Test
    @DisplayName("when GetLessonTypeById With Valid Id Then Returns LessonType") //happy path
    public void whenGetLessonTypeByIdWithValidIdThenReturnsLessonType() {
        //Arrange
        Long id = 1L;
        LessonType lessonType = new LessonType().setId(id);
        when(lessonTypeRepository.findById(id)).thenReturn(Optional.of(lessonType));
        //Act
        LessonType foundLessonType = lessonTypeService.getLessonTypeById(id);
        //Assert
        assertThat(foundLessonType.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("when getLessonTypeById With Invalid Id Then Returns ResourceNotFoundException") //unhappy path
    public void whenGetLessonTypeByIdWithInvalidIdThenReturnsResourceNotFoundException() {
        //Arrange
        Long id = 1L;
        String template = "Resource %s not found for %s with value %s";
        when(lessonTypeRepository.findById(id)).thenReturn(Optional.empty());
        String exceptedMessage = String.format(template, "LessonType", "Id", id);
        //Act
        Throwable exception = catchThrowable(() ->{
            LessonType foundLessonType = lessonTypeService.getLessonTypeById(id);
        });
        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(exceptedMessage);
    }
}

