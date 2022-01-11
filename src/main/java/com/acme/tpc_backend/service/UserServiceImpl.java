package com.acme.tpc_backend.service;

import com.acme.tpc_backend.domain.model.Course;
import com.acme.tpc_backend.domain.model.User;
import com.acme.tpc_backend.domain.repository.CourseRepository;
import com.acme.tpc_backend.domain.repository.UserRepository;
import com.acme.tpc_backend.domain.service.UserService;
import com.acme.tpc_backend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long UserId, User userDetails) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteUser(Long userId) {
        return null;
    }

    @Override
    public Page<Course> getAllCoursesByUserId(Long userId, Pageable pageable) {
        return userRepository.findById(userId).map(user -> {
            List<Course> courseList = user.getCourses();
            int coursesCount = courseList.size();
            return new PageImpl<>(courseList, pageable, coursesCount);
        }).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));

    }

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
