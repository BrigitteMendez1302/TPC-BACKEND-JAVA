package com.acme.tpc_backend.domain.repository;

import com.acme.tpc_backend.domain.model.Faculty; 
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

}
