package com.acme.tpc_backend.domain.service;
 import com.acme.tpc_backend.domain.model.LessonType;
 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.Pageable;
 import org.springframework.http.ResponseEntity;
 
 public interface LessonTypeService {
 	LessonType getLessonTypeById(Long lessonTypeId);
 	LessonType createLessonType(LessonType lessonType);
 	LessonType updateLessonType(Long LessonTypeId, LessonType lessonTypeDetails);
 	ResponseEntity<?> deleteLessonType(Long lessonTypeId);
 	Page<LessonType> getAllLessonTypes(Pageable pageable);
 }