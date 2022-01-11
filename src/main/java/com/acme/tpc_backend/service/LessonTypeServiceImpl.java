package com.acme.tpc_backend.service;


import com.acme.tpc_backend.domain.model.Faculty;
import com.acme.tpc_backend.domain.model.LessonType;
import com.acme.tpc_backend.domain.repository.LessonTypeRepository;
import com.acme.tpc_backend.domain.service.LessonTypeService;
import com.acme.tpc_backend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LessonTypeServiceImpl implements LessonTypeService {

    @Autowired
    private LessonTypeRepository lessonTypeRepository;

    @Override
    public LessonType getLessonTypeById(Long lessonTypeId) {
        return lessonTypeRepository.findById(lessonTypeId)
                .orElseThrow(()-> new ResourceNotFoundException("LessonType", "Id", lessonTypeId));
    }

    @Override
    public LessonType createLessonType(LessonType lessonType) {
        return lessonTypeRepository.save(lessonType);
    }

    @Override
    public LessonType updateLessonType(Long lessonTypeId, LessonType lessonTypeDetails) {
        return lessonTypeRepository.findById(lessonTypeId)
                .map(lessonType -> {
                    lessonType.setName(lessonTypeDetails.getName());
                    return lessonTypeRepository.save(lessonType);
                }).orElseThrow(()-> new ResourceNotFoundException("LessonType", "Id", lessonTypeId));
    }

    @Override
    public ResponseEntity<?> deleteLessonType(Long lessonTypeId) {
        return lessonTypeRepository.findById(lessonTypeId)
                .map(lessonType -> {
                    lessonTypeRepository.delete(lessonType);
                    return ResponseEntity.ok().build();
                }).orElseThrow(()->new ResourceNotFoundException("LessonType", "Id", lessonTypeId));
    }
    @Override
    public Page<LessonType> getAllLessonTypes(Pageable pageable) {
        return lessonTypeRepository.findAll(pageable);
    }
}
