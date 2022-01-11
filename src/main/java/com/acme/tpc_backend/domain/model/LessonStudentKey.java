package com.acme.tpc_backend.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class LessonStudentKey implements Serializable {
    @Column(name="lesson_id")
    private Long  lessonId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LessonStudentKey)) return false;
        LessonStudentKey that = (LessonStudentKey) o;
        return lessonId.equals(that.lessonId) && studentId.equals(that.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lessonId, studentId);
    }

    @Column(name="student_id")
    private Long  studentId;
}
