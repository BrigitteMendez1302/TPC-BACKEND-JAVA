package com.acme.tpc_backend.domain.repository;

import com.acme.tpc_backend.domain.model.TrainingTutor; 
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrainingTutorRepository extends JpaRepository<TrainingTutor, Long> {

}
