package com.acme.tpc_backend.domain.repository;

import com.acme.tpc_backend.domain.model.Account;
import com.acme.tpc_backend.domain.model.Lesson;
import com.acme.tpc_backend.domain.model.Training;
import com.acme.tpc_backend.domain.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TrainingRepository extends JpaRepository<Training, Long>{
    @Query("select l from Training l where l.startDate  between :#{#start} and :#{#end}")
    List<Training> getTrainingsInRange(@Param("start") Date start, @Param("end") Date end);
    Optional<Training> findMeetingById(Long meetingId);
}
