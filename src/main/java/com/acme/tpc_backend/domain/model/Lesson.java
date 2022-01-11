package com.acme.tpc_backend.domain.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName="id")
@OnDelete(action = OnDeleteAction.CASCADE)
@Table(name = "lessons")
public class Lesson extends Meeting {
	public Lesson(List<LessonStudent> lessonStudents, Tutor tutor, LessonType lessonType, Course course, int vacants) {
		this.lessonStudents = lessonStudents;
		this.tutor = tutor;
		this.lessonType = lessonType;
		this.course = course;
		this.vacants = vacants;
	}

	public Lesson() {

	}

	public List<LessonStudent> getLessonStudents() {
		return lessonStudents;
	}

	public void setLessonStudents(List<LessonStudent> lessonStudents) {
		this.lessonStudents = lessonStudents;
	}

	public Tutor getTutor() {
		return tutor;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}

	public LessonType getLessonType() {
		return lessonType;
	}

	public void setLessonType(LessonType lessonType) {
		this.lessonType = lessonType;
	}

	public Course getCourse() {
		return course;
	}

	public Lesson setCourse(Course course) {
		this.course = course;
		return this;
	}

	public int getVacants() {
		return vacants;
	}

	public void setVacants(int vacants) {
		this.vacants = vacants;
	}

	public int getContador(){return contador;}
	public void setContador(int contador){this.contador = contador;}

	@OneToMany(mappedBy = "lesson", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<LessonStudent> lessonStudents;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "tutor_id", nullable = false)
	@JsonIgnore
	private Tutor tutor;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "lesson_type_id", nullable = false)
	private LessonType lessonType;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "course_id", nullable = false)
	private Course course;

	@NotNull
	@Positive
	private int vacants;

	@JsonIgnore
	@Transient
	private int contador = 0;
}
