package com.acme.tpc_backend.domain.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Entity
@Table(name = "lesson_types")
public class LessonType extends AuditModel{
	public Long getId() {
		return id;
	}

	public LessonType setId(Long id) {
		this.id = id;
		return this;
	}

	public List<Lesson> getLessons() {
		return lessons;
	}

	public LessonType setLessons(List<Lesson> lessons) {
		this.lessons = lessons;
		return this;
	}

	public String getName() {
		return name;
	}

	public LessonType setName(String name) {
		this.name = name;
		return this;
	}

	public int getQuantity() {
		return quantity;
	}

	public LessonType setQuantity(int quantity) {
		this.quantity = quantity;
		return this;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "lessonType", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Lesson> lessons;

    @NotNull
	@Length(max = 50)
	private String name;

    @NotNull
	@Positive
	private int quantity;

}
