package com.acme.tpc_backend.domain.service;
 import com.acme.tpc_backend.domain.model.Account;
 import com.acme.tpc_backend.domain.model.Coordinator;
 import com.acme.tpc_backend.domain.model.Faculty;
 import com.acme.tpc_backend.domain.model.Student;
 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.Pageable;
 import org.springframework.http.ResponseEntity;
 
 public interface CoordinatorService{
 	Coordinator getCoordinatorById(Long coordinatorId);
    Coordinator getCoordinatorByAccount(Account accountId);
 	Coordinator createCoordinator(Coordinator coordinator);
 	Coordinator updateCoordinator(Long CoordinatorId, Coordinator coordinatorDetails);
 	ResponseEntity<?> deleteCoordinator(Long coordinatorId);
 	Page<Coordinator> getAllCoordinators(Pageable pageable);
 	Coordinator updateCoordinatorRole(Long coordinatorId, Coordinator coordinatorDetails);
 }