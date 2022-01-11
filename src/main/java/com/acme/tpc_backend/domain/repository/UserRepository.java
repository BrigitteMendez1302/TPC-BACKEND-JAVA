package com.acme.tpc_backend.domain.repository;

import com.acme.tpc_backend.domain.model.User; 
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

}
