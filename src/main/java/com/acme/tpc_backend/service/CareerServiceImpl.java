package com.acme.tpc_backend.service;

import com.acme.tpc_backend.domain.model.Career;
import com.acme.tpc_backend.domain.model.Course;
import com.acme.tpc_backend.domain.repository.CareerRepository;
import com.acme.tpc_backend.domain.repository.CourseRepository;
import com.acme.tpc_backend.domain.service.CareerService;
import com.acme.tpc_backend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CareerServiceImpl implements CareerService {

    @Autowired
    private CareerRepository careerRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Career getCareerById(Long careerId) {
        return careerRepository.findById(careerId)
                 .orElseThrow(()-> new ResourceNotFoundException("Career", "Id", careerId));
    }

    @Override
    public Career createCareer(Career career) {
        return careerRepository.save(career);
    }

    @Override
    public Career updateCareer(Long CareerId, Career careerDetails) {
        Career career = careerRepository.findById(CareerId)
                .orElseThrow(() -> new ResourceNotFoundException("Career", "Id", CareerId));
        career.setName(careerDetails.getName());
        return careerRepository.save(career);
    }

    @Override
    public ResponseEntity<?> deleteCareer(Long careerId) {
        return careerRepository.findById(careerId)
                .map(career -> {
                    careerRepository.delete(career);
                    return ResponseEntity.ok().build();
                }).orElseThrow(()->new ResourceNotFoundException("Career", "Id", careerId));
    }

    @Override
    public Page<Career> getAllCareers(Pageable pageable) {
        return careerRepository.findAll(pageable);
    }

    @Override
    public Career assignCareerCourse(Long careerId, Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "Id", courseId));
        return careerRepository.findById(careerId)
                .map(career -> careerRepository.save(career.courseWith(course)))
                .orElseThrow(() -> new ResourceNotFoundException("Career", "Id", careerId));
    }

    @Override
    public Career unassignCareerCourse(Long careerId, Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "Id", courseId));
        return careerRepository.findById(careerId)
                .map(career -> careerRepository.save(career.unCourseWith(course)))
                .orElseThrow(() -> new ResourceNotFoundException("Career", "Id", careerId));
    }

    @Override
    public Page<Career> getAllCareersByCourseId(Long courseId, Pageable pageable) {
        return courseRepository.findById(courseId)
                .map(course -> {
                    List<Career> careers = course.getCareers();
                    int careersCount = careers.size();
                    return new PageImpl<>(careers, pageable, careersCount);
                }).orElseThrow(() -> new ResourceNotFoundException("Course", "Id", courseId));
    }
}