package com.acme.tpc_backend.service;

import com.acme.tpc_backend.domain.model.University;
import com.acme.tpc_backend.domain.repository.UniversityRepository;
import com.acme.tpc_backend.domain.service.UniversityService;
import com.acme.tpc_backend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UniversityServiceImpl implements UniversityService {

    @Autowired
    private UniversityRepository universityRepository;

    @Override
    public University getUniversityById(Long universityId) {
        return universityRepository.findById(universityId)
                 .orElseThrow(()-> new ResourceNotFoundException("University", "Id", universityId));
    }

    @Override
    public University createUniversity(University university) {
        return universityRepository.save(university);
    }

    @Override
    public University updateUniversity(Long UniversityId, University universityDetails) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteUniversity(Long universityId) {
        return null;
    }

    @Override
    public Page<University> getAllUniversities(Pageable pageable) {
        return universityRepository.findAll(pageable);
    }
}