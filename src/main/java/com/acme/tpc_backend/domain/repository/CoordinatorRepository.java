package com.acme.tpc_backend.domain.repository;

import com.acme.tpc_backend.domain.model.Account;
import com.acme.tpc_backend.domain.model.Coordinator;
import com.acme.tpc_backend.domain.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CoordinatorRepository extends JpaRepository<Coordinator, Long> {
    public Optional<Coordinator> findByAccount(Account acount);

}
