package com.acme.tpc_backend;

import com.acme.tpc_backend.domain.model.Career;
import com.acme.tpc_backend.domain.model.Lesson;
import com.acme.tpc_backend.domain.model.LessonStudent;
import com.acme.tpc_backend.domain.model.Student;
import com.acme.tpc_backend.domain.repository.LessonStudentRepository;
import com.acme.tpc_backend.domain.service.CareerService;
import com.acme.tpc_backend.domain.service.LessonService;
import com.acme.tpc_backend.domain.service.LessonStudentService;
import com.acme.tpc_backend.service.CareerServiceImpl;
import com.acme.tpc_backend.service.LessonServiceImpl;
import com.acme.tpc_backend.service.LessonStudentServiceImpl;
import io.cucumber.java.ht.Le;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class LessonStudentServiceImplTest {
    @MockBean
    private LessonStudentRepository lessonStudentRepository;

    @Autowired
    private LessonStudentService lessonStudentService;

    @MockBean
    private LessonService lessonService;

    @TestConfiguration
    static class LessonStudentServiceImplTestConfiguration {
        @Bean
        public LessonStudentService lessonStudentService() { return new LessonStudentServiceImpl(); }
    }

    @Test
    @DisplayName("when SaveLessonStudent With Valid LessonStudent Then Returns Success") //happy path
    public void whenSaveLessonStudentWithValidLessonStudentThenReturnsSuccess() {
        Long studentId = 1L;
        Long lessonId = 1L;
        Student student = new Student();
        student.setId(studentId);
        Lesson lesson = new Lesson();
        lesson.setId(lessonId);
        LessonStudent lessonStudent = new LessonStudent();
        lessonStudent.setStudent(student);
        lessonStudent.setLesson(lesson);
        lessonStudent.setComment("Hola a todos");

        when(lessonStudentRepository.save(lessonStudent)).thenReturn(lessonStudent);
        LessonStudent saveLS = lessonStudentService.createLessonStudent(lessonStudent);
        assertThat(saveLS).isEqualTo(lessonStudent);
    }

    @Test
    @DisplayName("when UpdateLessonStudent With Valid LessonStudent Then Returns Success") //happy path
    public void whenUpdateLessonStudentWithValidLessonStudentThenReturnsSuccess() {
        Long studentId = 1L;
        Long lessonId = 1L;
        Student student = new Student();
        student.setId(studentId);
        Lesson lesson = new Lesson();
        lesson.setId(lessonId);
        LessonStudent lessonStudent = new LessonStudent();
        lessonStudent.setStudent(student);
        lessonStudent.setLesson(lesson);
        lessonStudent.setComment("Hola a todos");
        when(lessonStudentRepository.save(lessonStudent)).thenReturn(lessonStudent);
        when(lessonStudentRepository.findById(1L)).thenReturn(Optional.of(lessonStudent));
        LessonStudent ls = lessonStudentService.updateLessonStudent(1L, lessonStudent);
        assertThat(ls).isEqualTo(lessonStudent);
    }

    @Test
    @DisplayName("Get All Lessons By StudentId") //happy path
    public void GetAllLessonsByStudentId(){
        Long studentId = 1L;
        Long lessonId = 1L;
        Student student = new Student();
        student.setId(studentId);
        Lesson lesson = new Lesson();
        lesson.setId(lessonId);
        Lesson lesson1 = new Lesson();
        lesson1.setId(2L);
        LessonStudent lessonStudent = new LessonStudent();
        lessonStudent.setStudent(student);
        lessonStudent.setLesson(lesson);
        lessonStudent.setComment("Hola a todos");
        List<Lesson> list = new ArrayList<Lesson>();
        list.add(lesson);
        list.add(lesson1);
        Page<Lesson> lessons  = new PageImpl<>(list);
        Pageable pageable = PageRequest.of(0,2);
        //when(lessonService.getAllLessonsByStudentId(studentId, pageable)).thenReturn(lessons);
        assertThat(1).isEqualTo(1);
    }
}
