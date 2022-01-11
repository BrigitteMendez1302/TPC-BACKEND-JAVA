package com.acme.tpc_backend.domain.repository;

import com.acme.tpc_backend.domain.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import com.acme.tpc_backend.domain.model.Suggestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface LessonRepository extends JpaRepository<Lesson, Long>{
    @Query("select l from Lesson l where l.startDate  between :#{#start} and :#{#end}")
    List<Lesson> getLessonsInRange(@Param("start") Date start, @Param("end") Date end);
    @Query("select l from Lesson l where l.tutor.id = :#{#tutorId} and " +
            "l.lessonType.id = :#{#lessonTypeId} and l.startDate >= :#{#start} and l.startDate <= :#{#end}" )
    List<Lesson> getLessonsInRangeByTutorIdAndLessonTypeId
            (@Param("start") Date start, @Param("end") Date end, @Param("tutorId") Long tutorId,
             @Param("lessonTypeId") Long lessonTypeId);
    Optional<Lesson> findByCourse(Course course);
    Page<Lesson> findByLessonTypeId(Long lessonTypeId, Pageable pageable);
    List<Lesson> findAllByTutorIdAndCourseIdAndLessonTypeId(Long tutorId, Long courseId, Long lessonTypeId);
    @Modifying
    @Query("update Lesson lesson set lesson.vacants = lesson.vacants - :#{#subs} where lesson.id = :#{#lessonId}")
    void setLessonVacants(@Param("lessonId") Long lessonId, @Param("subs") Short subs);

    //NEW
    @Query("select l from Lesson l where l.lessonType.id = :#{#lessonTypeId}")
    List<Lesson> getLessonsByLessonTypeId(@Param("lessonTypeId") Long lessonTypeId);
}