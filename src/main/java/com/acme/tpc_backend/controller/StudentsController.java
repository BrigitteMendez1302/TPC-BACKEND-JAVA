package com.acme.tpc_backend.controller;

import com.acme.tpc_backend.domain.model.Account;
import com.acme.tpc_backend.domain.model.Student;
import com.acme.tpc_backend.domain.service.AccountService;
import com.acme.tpc_backend.domain.service.CareerService;
import com.acme.tpc_backend.domain.service.StudentService;
import com.acme.tpc_backend.resource.SaveStudentResource;
import com.acme.tpc_backend.resource.StudentResource;
import com.acme.tpc_backend.resource.TutorResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLOutput;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class StudentsController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CareerService careerService;

    @Operation(summary = "List students", description = "Lists students", tags = {"students"})
    @GetMapping("/students")
    public Page<StudentResource> getAllStudents(Pageable pageable) {
        Page<Student> studentsPage = studentService.getAllStudents(pageable);
        List<StudentResource> resources = studentsPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Create Student", description = "Permits the Insertion of a student", tags = {"students"})
    @PostMapping("/students")
    public StudentResource createStudent(@Valid @RequestBody SaveStudentResource resource) {
        Student student = convertToEntity(resource);
        return convertToResource(studentService.createStudent(student));
    }

    @Operation(summary = "Update Student", description = "Permits to update a student", tags = {"students"})
    @PutMapping("/students/{studentId}")
    public StudentResource updateStudent(@PathVariable Long studentId, @RequestBody SaveStudentResource resource) {
        Student student = convertToEntity(resource);
        return convertToResource(studentService.updateStudent(studentId, student));
    }

    @Operation(summary = "Returns a student by the specified Id", description = "Returns a student type by its Id", tags = {"students"})
    @GetMapping("/students/{studentId}")
    public StudentResource getStudentById(@PathVariable Long studentId) {
        return convertToResource(studentService.getStudentById(studentId));
    }


    @Operation(summary = "Update Student Role", description = "Permits to update a student role", tags = {"students"})
    @PutMapping("/students/{studentId}/role")
    public StudentResource updateStudentRole(@PathVariable Long studentId, @RequestBody SaveStudentResource resource) {
        Student student = convertToEntity(resource);
        return convertToResource(studentService.updateStudentRole(studentId, student));
    }

    @Operation(summary = "Delete student", description = "Permits to delete a student", tags = {"students"})
    @DeleteMapping("/students/{studentId}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long studentId) {
        return studentService.deleteStudent(studentId);
    }

    private Student convertToEntity(SaveStudentResource resource) {
        Student student = new Student();
        student.setAccount(accountService.getAccountById(resource.getAccountId()));
        student.setFirstName(resource.getFirstName());
        student.setLastName(resource.getLastName());
        student.setMail(resource.getMail());
        student.setPhoneNumber(resource.getPhoneNumber());
        student.setCycleNumber(resource.getCycleNumber());
        student.setCareer(careerService.getCareerById(resource.getCareerId()));
        student.setRole(resource.getRole());
        return student;
    }
    private StudentResource convertToResource(Student entity) {
        return mapper.map(entity, StudentResource.class);
    }
}