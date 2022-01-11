package com.acme.tpc_backend.service;

import com.acme.tpc_backend.domain.model.*;
import com.acme.tpc_backend.domain.repository.CoordinatorRepository;
import com.acme.tpc_backend.domain.service.CoordinatorService;
import com.acme.tpc_backend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service

public class CoordinatorServiceImpl implements CoordinatorService {

    @Autowired
    private CoordinatorRepository coordinatorRepository;


    @Override
    public Coordinator getCoordinatorById(Long coordinatorId) {
        return coordinatorRepository.findById(coordinatorId)
                 .orElseThrow(()-> new ResourceNotFoundException("Coordinator", "Id", coordinatorId));
    }

    @Override
    public Coordinator getCoordinatorByAccount(Account accountId) {
        return coordinatorRepository.findByAccount(accountId)
                .orElseThrow(()-> new ResourceNotFoundException("Coordinator", "Account", accountId));
    }


    @Override
    public Coordinator createCoordinator(Coordinator coordinator) {
        return coordinatorRepository.save(coordinator);
    }

    @Override
    public Coordinator updateCoordinator(Long CoordinatorId, Coordinator coordinatorDetails) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteCoordinator(Long coordinatorId) {
        return null;
    }

    @Override
    public Page<Coordinator> getAllCoordinators(Pageable pageable) {
        return coordinatorRepository.findAll(pageable);
    }

    @Override
    public Coordinator updateCoordinatorRole(Long coordinatorId, Coordinator coordinatorDetails) {
        Coordinator coordinator = coordinatorRepository.findById(coordinatorId)
                .orElseThrow(()-> new ResourceNotFoundException("Coordinator", "Id", coordinatorId));

        coordinator.setRole(coordinatorDetails.getRole());
        return coordinatorRepository.save(coordinator);
    }
}
