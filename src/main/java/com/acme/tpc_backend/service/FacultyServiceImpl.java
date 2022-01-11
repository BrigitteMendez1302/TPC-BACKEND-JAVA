package com.acme.tpc_backend.service;

import com.acme.tpc_backend.domain.model.Career;
import com.acme.tpc_backend.domain.model.Faculty;
import com.acme.tpc_backend.domain.repository.FacultyRepository;
import com.acme.tpc_backend.domain.service.FacultyService;
import com.acme.tpc_backend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    @Override
    public Faculty getFacultyById(Long facultyId) {
        return facultyRepository.findById(facultyId)
                .orElseThrow(()-> new ResourceNotFoundException("Faculty", "Id", facultyId));
    }

    @Override
    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty updateFaculty(Long facultyId, Faculty facultyDetails) {
        return facultyRepository.findById(facultyId)
                .map( faculty -> {
                    faculty.setName(facultyDetails.getName());
                    faculty.setDescription(facultyDetails.getDescription());
                    return facultyRepository.save(faculty);
                }).orElseThrow(() -> new ResourceNotFoundException("Faculty", "Id", facultyId));
    }

    @Override
    public ResponseEntity<?> deleteFaculty(Long facultyId) {
        return facultyRepository.findById(facultyId)
                .map( faculty -> {
                    facultyRepository.delete(faculty);
                    return ResponseEntity.ok().build();
                }).orElseThrow(()-> new ResourceNotFoundException("Faculty", "Id", facultyId));
    }

    @Override
    public Page<Faculty> getAllFaculties(Pageable pageable) {
        return facultyRepository.findAll(pageable);
    }
}
