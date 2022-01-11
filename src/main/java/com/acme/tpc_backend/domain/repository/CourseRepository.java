package com.acme.tpc_backend.domain.repository;

import com.acme.tpc_backend.domain.model.Course; 
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
