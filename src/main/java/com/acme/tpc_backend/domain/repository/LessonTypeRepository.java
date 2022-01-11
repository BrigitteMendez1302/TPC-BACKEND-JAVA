package com.acme.tpc_backend.domain.repository;

import com.acme.tpc_backend.domain.model.LessonType; 
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LessonTypeRepository extends JpaRepository<LessonType, Long> {

}
