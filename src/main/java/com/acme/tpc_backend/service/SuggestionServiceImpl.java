package com.acme.tpc_backend.service;

import com.acme.tpc_backend.domain.model.Suggestion;
import com.acme.tpc_backend.domain.repository.SuggestionRepository;
import com.acme.tpc_backend.domain.repository.UserRepository;
import com.acme.tpc_backend.domain.service.SuggestionService;
import com.acme.tpc_backend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SuggestionServiceImpl implements SuggestionService {
    @Autowired
    private SuggestionRepository suggestionRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<Suggestion> getAllSuggestionsByUserId(Long userId, Pageable pageable) {
        return suggestionRepository.findByUserId(userId,pageable);
    }

    @Override
    public Suggestion getSuggestionById(Long suggestionId) {
        return suggestionRepository.findById(suggestionId)
                .orElseThrow(() -> new ResourceNotFoundException("Suggestion", "Id", suggestionId));
    }

    @Override
    public Suggestion getSuggestionByIdAndUserId(Long id, Long userId) {
        return suggestionRepository.findByIdAndUserId(id,userId)
                .orElseThrow(()->new ResourceNotFoundException(
                        "Suggestion not found with Id "+id+" and UserId "+userId));
    }

    @Override
    public Suggestion createSuggestion(Long userId, Suggestion suggestion) {
        return userRepository.findById(userId).map(user->{
            suggestion.setUser(user);
            return suggestionRepository.save(suggestion);
        }).orElseThrow(()->new ResourceNotFoundException(
                "User","Id",userId));
    }

    @Override
    public Suggestion updateSuggestion(Long userId, Long suggestionId, Suggestion suggestionDetails) {
        if(!userRepository.existsById(userId))
            throw new ResourceNotFoundException("User", "Id", userId);

        return suggestionRepository.findById(suggestionId).map(suggestion -> {
            suggestion.setMessage(suggestionDetails.getMessage())
                    .setUpdatedAt(suggestionDetails.getCreatedAt());
            return suggestionRepository.save(suggestion);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Suggestion", "Id", suggestionId));
    }

    @Override
    public ResponseEntity<?> deleteSuggestion(Long userId, Long id) {
        if(!userRepository.existsById(userId))
            throw new ResourceNotFoundException("User", "Id", userId);

        return suggestionRepository.findById(id).map(suggestion -> {
            suggestionRepository.delete(suggestion);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Suggestion", "Id", id));
    }

    @Override
    public Page<Suggestion> getAllSuggestions(Pageable pageable) {
        return suggestionRepository.findAll(pageable);
    }
}
