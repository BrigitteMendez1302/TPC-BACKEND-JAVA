package com.acme.tpc_backend.domain.repository;

import com.acme.tpc_backend.domain.model.University; 
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UniversityRepository extends JpaRepository<University, Long> {

}
