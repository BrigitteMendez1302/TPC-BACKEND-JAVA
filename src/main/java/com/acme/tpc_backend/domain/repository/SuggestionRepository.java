package com.acme.tpc_backend.domain.repository;

import com.acme.tpc_backend.domain.model.Suggestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SuggestionRepository extends JpaRepository<Suggestion, Long> {
    Page<Suggestion> findByUserId(Long userId, Pageable pageable);
    Optional<Suggestion> findByIdAndUserId(Long id, Long userId);
}
