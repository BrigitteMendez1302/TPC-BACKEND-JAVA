package com.acme.tpc_backend.service;

import com.acme.tpc_backend.domain.model.Course;
import com.acme.tpc_backend.domain.model.User;
import com.acme.tpc_backend.domain.repository.CourseRepository;
import com.acme.tpc_backend.domain.repository.UserRepository;
import com.acme.tpc_backend.domain.service.CourseService;
import com.acme.tpc_backend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<Course> getAllCourses(Pageable pageable) {
        return courseRepository.findAll(pageable);
    }

    public Course getCourseById(Long courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(()-> new ResourceNotFoundException("Course", "Id", courseId));
    }

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Long courseId, Course courseDetails) {
        return courseRepository.findById(courseId)
                .map(course -> {
                    course.setName(courseDetails.getName());
                    return courseRepository.save(course);
                }).orElseThrow(()-> new ResourceNotFoundException("Course", "Id", courseId));
    }

    @Override
    public ResponseEntity<?> deleteCourse(Long courseId) {
        return courseRepository.findById(courseId)
                .map(course -> {
                    courseRepository.delete(course);
                    return ResponseEntity.ok().build();
                }).orElseThrow(()->new ResourceNotFoundException("Course", "Id", courseId));
    }

    @Override
    public Course assignCourseUser(Long courseId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        return courseRepository.findById(courseId).map(
                course -> courseRepository.save(course.likesBy(user)))
                .orElseThrow(()->new ResourceNotFoundException("Course","Id",courseId));


    }

    @Override
    public Course unassignCourseUser(Long courseId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        return courseRepository.findById(courseId).map(
                course -> courseRepository.save(course.dislikesBy(user)))
                .orElseThrow(()->new ResourceNotFoundException("Course","Id",courseId));
    }



    @Override
    public Page<User> getAllUsersByCourseId(Long courseId, Pageable pageable) {
        return courseRepository.findById(courseId).map(course -> {
            List<User> userList = course.getUsers();
            int usersCount = userList.size();
            return new PageImpl<>(userList, pageable, usersCount);
        }).orElseThrow(()->new ResourceNotFoundException("Course","Id",courseId));
    }
}
