package com.acme.tpc_backend.domain.repository;

import com.acme.tpc_backend.domain.model.Account; 
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
