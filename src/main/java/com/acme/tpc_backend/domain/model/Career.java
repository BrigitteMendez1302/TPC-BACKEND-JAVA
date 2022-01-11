package com.acme.tpc_backend.domain.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "careers")
public class Career extends AuditModel {
	public Long getId() {
		return id;
	}

	public Career setId(Long id) {
		this.id = id;		return this;

	}

	public String getName() {
		return name;
	}

	public Career setName(String name) {
		this.name = name;		return this;

	}

	public List<Student> getStudents() {
		return students;
	}

	public Career setStudents(List<Student> students) {
		this.students = students;		return this;

	}

	public boolean isCoursedWith(Course course) {
		return this.getCourses().contains(course);
	}

	public Career courseWith(Course course) {
		if(!isCoursedWith(course)){
			this.getCourses().add(course);
		}
		return this;
	}

	public Career unCourseWith(Course course) {
		if(this.isCoursedWith(course))
			this.getCourses().remove(course);
		return this;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	@NotNull
	@Length(max = 100)
	private String name;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "faculty_id", nullable = false)
	private Faculty faculty;

    @OneToMany(mappedBy = "career", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Student> students;

	public List<Course> getCourses() {
		return courses;
	}

	@ManyToMany(fetch = FetchType.LAZY,
	cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "career_courses",
	joinColumns = {@JoinColumn(name="career_id")},
	inverseJoinColumns = {@JoinColumn(name = "course_id")})
	private List<Course> courses;

}
