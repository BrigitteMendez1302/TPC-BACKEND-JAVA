package com.acme.tpc_backend.domain.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName="id")
@OnDelete(action = OnDeleteAction.CASCADE)
@Table(name = "tutors")
public class Tutor extends User{
	public List<Lesson> getLessons() {
		return lessons;
	}

	public void setLessons(List<Lesson> lessons) {
		this.lessons = lessons;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public List<TrainingTutor> getTrainingTutors() {
		return trainingTutors;
	}

	public void setTrainingTutors(List<TrainingTutor> trainingTutors) {
		this.trainingTutors = trainingTutors;
	}

	@OneToMany(mappedBy = "tutor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Lesson> lessons;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "faculty_id", nullable = false)
	private Faculty faculty;

	@OneToMany(mappedBy = "tutor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<TrainingTutor> trainingTutors;

	public Tutor(Account account, String firstName, String lastName, String mail, Long phoneNumber, Faculty faculty) {
		super(account, firstName, lastName, mail, phoneNumber);
		this.faculty = faculty;
	}

	public Tutor() {
	}
}
