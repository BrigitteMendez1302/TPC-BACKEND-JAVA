package com.acme.tpc_backend.domain.service;
 import com.acme.tpc_backend.domain.model.Course;
 import com.acme.tpc_backend.domain.model.User;
 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.Pageable;
 import org.springframework.http.ResponseEntity;

public interface UserService {
    Page<User> getAllUsers(Pageable pageable);
 	User getUserById(Long userId);
 	User createUser(User user);
 	User updateUser(Long UserId, User userDetails);
 	ResponseEntity<?> deleteUser(Long userId);
    Page<Course> getAllCoursesByUserId(Long userId, Pageable pageable);
 }