package com.acme.tpc_backend.domain.repository;

import com.acme.tpc_backend.domain.model.Account;
import com.acme.tpc_backend.domain.model.Student;
import com.acme.tpc_backend.domain.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TutorRepository extends JpaRepository<Tutor, Long> {
    public Optional<Tutor> findByAccount(Account account);
}
