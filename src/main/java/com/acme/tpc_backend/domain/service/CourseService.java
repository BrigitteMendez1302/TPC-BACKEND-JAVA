package com.acme.tpc_backend.domain.service;
 import com.acme.tpc_backend.domain.model.Course;
 import com.acme.tpc_backend.domain.model.User;
 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.Pageable;
 import org.springframework.http.ResponseEntity;
 
 public interface CourseService {
    Page<Course> getAllCourses(Pageable pageable);
 	Course getCourseById(Long courseId);
 	Course createCourse(Course course);
 	Course updateCourse(Long courseId, Course courseDetails);
 	ResponseEntity<?> deleteCourse(Long courseId);
 	Course assignCourseUser(Long courseId,Long userId);
 	Course unassignCourseUser(Long courseId,Long userId);
 	Page<User> getAllUsersByCourseId(Long courseId, Pageable pageable);


 }