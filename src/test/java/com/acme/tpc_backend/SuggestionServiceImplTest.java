package com.acme.tpc_backend;

import com.acme.tpc_backend.domain.model.Course;
import com.acme.tpc_backend.domain.model.Suggestion;
import com.acme.tpc_backend.domain.model.User;
import com.acme.tpc_backend.domain.repository.SuggestionRepository;
import com.acme.tpc_backend.domain.repository.UserRepository;
import com.acme.tpc_backend.domain.service.SuggestionService;
import com.acme.tpc_backend.exception.ResourceNotFoundException;
import com.acme.tpc_backend.service.SuggestionServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class SuggestionServiceImplTest {
    @MockBean
    private UserRepository userRepository;

    @MockBean
    private SuggestionRepository suggestionRepository;

    @Autowired
    private SuggestionService suggestionService;

    @TestConfiguration
    static class SuggestionServiceImplTestConfiguration {
        @Bean
        public SuggestionService suggestionService() {
            return new SuggestionServiceImpl();
        }
    }

    @Test
    @DisplayName("when SaveSuggestion With invalid userId Then Returns ResourceNotFoundException") //happy path
    public void WhenSaveSuggestionWithInvalidUserIdThenReturnsResourceNotFoundException() {
        Long id = 1L;
        String message = "Deberian de mejorar el sistema de horarios";
        Long userId = 2L;
        User user = new User();
        user.setId(userId);
        Suggestion suggestion = new Suggestion().setId(id).setMessage(message);
        suggestion.setUser(user);
        when(suggestionRepository.save(suggestion)).thenReturn(
                suggestion);
        when(userRepository.findById(user.getId())).thenReturn(
                Optional.of(user));

        Suggestion found = suggestionService.createSuggestion(userId, suggestion);
        assertThat(found).isEqualTo(suggestion);
    }

    @Test
    @DisplayName("when Get Suggestions by valid userId but Returns Nothing") //happy path
    public void WhenGetSuggestionsByValidUserIdButReturnsNothing() {
        Long userId = 1L;
        String template = "Resource %s not found for %s with value %s";
        when(suggestionService.getAllSuggestionsByUserId(userId, Pageable.unpaged())).thenReturn(Page.empty());

        //Act
        Page<Suggestion> suggestions = suggestionService.getAllSuggestionsByUserId(userId,Pageable.unpaged());

        //Assert
        assertThat(suggestions.stream().count()).isEqualTo(0);

    }


}
