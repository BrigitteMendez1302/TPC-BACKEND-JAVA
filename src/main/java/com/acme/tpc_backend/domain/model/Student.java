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
@Table(name = "students")
public class Student extends User {
	public Student(Account account, String firstName, String lastName, String mail, Long phoneNumber, int cycleNumber, Career career, List<LessonStudent> lessonStudents) {
		super(account, firstName, lastName, mail, phoneNumber);
		this.cycleNumber = cycleNumber;
		this.career = career;
		this.lessonStudents = lessonStudents;
	}
	public Student(Account account, String firstName, String lastName, String mail, Long phoneNumber, Career career) {
		super(account, firstName, lastName, mail, phoneNumber);

		this.career = career;

	}



	public int getCycleNumber() {
		return cycleNumber;
	}

	public Student setCycleNumber(int cycleNumber) {
		this.cycleNumber = cycleNumber;
		return this;
	}

	public Career getCareer() {
		return career;
	}

	public void setCareer(Career career) {
		this.career = career;
	}

	@NotNull
	@Positive
	private int cycleNumber;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "career_id", nullable = false)
	private Career career;

	public List<LessonStudent> getLessonStudents() {
		return lessonStudents;
	}

	public void setLessonStudents(List<LessonStudent> lessonStudents) {
		this.lessonStudents = lessonStudents;
	}

	@OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<LessonStudent> lessonStudents;



	public Student() {
	}
}