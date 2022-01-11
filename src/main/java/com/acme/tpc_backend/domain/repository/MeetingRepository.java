package com.acme.tpc_backend.domain.repository;

import com.acme.tpc_backend.domain.model.Meeting; 
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {

}
