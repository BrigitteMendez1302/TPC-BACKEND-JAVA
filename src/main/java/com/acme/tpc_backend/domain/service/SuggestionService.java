package com.acme.tpc_backend.domain.service;
 import com.acme.tpc_backend.domain.model.Suggestion;
 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.Pageable;
 import org.springframework.http.ResponseEntity;
 
 public interface SuggestionService {
    Page<Suggestion> getAllSuggestionsByUserId(Long userId, Pageable pageable);
    Suggestion getSuggestionByIdAndUserId(Long id, Long userId);
 	Suggestion createSuggestion(Long userId, Suggestion suggestion);
 	Suggestion updateSuggestion(Long userId, Long suggestionId,Suggestion suggestionDetails);
 	ResponseEntity<?> deleteSuggestion(Long userId,Long id);
 	Page<Suggestion> getAllSuggestions(Pageable pageable);
 	Suggestion getSuggestionById(Long suggestionId);
 }