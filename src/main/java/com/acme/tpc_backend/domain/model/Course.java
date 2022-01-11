package com.acme.tpc_backend.domain.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course extends AuditModel{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Length(max=50)
	private String name;

	public Long getId() {
		return id;
	}



	public Course setId(Long id) {
		this.id = id;
		return this;
	}

	public List<Lesson> getLessons() {
		return lessons;
	}

	public Course setLessons(List<Lesson> lessons) {
		this.lessons = lessons;
		return this;
	}

	public String getName() {
		return name;
	}

	public Course setName(String name) {
		this.name = name;
		return this;
	}



	@OneToMany(mappedBy = "course", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Lesson> lessons;



	public List<Career> getCareers() {
		return careers;
	}

	public Course setCareers(List<Career> careers) {
		this.careers = careers;
		return this;
	}

	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "courses")
	private List<Career> careers;


	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "course_users",
			joinColumns = {@JoinColumn(name = "course_id")},
			inverseJoinColumns = { @JoinColumn(name = "user_id")})
	private List<User> users;

	public List<User> getUsers() {
		return users;
	}

	public Course setUsers(List<User> users) {
		this.users = users;
		return this;
	}

	public boolean likedBy(User user){
		return this.getUsers().contains(user);
	}

	public Course likesBy(User user){
		if(!likedBy(user))
			this.getUsers().add(user);
		return this;
	}

	public Course dislikesBy(User user)
	{
		if(likedBy(user))
			this.getUsers().remove(user);
		return this;
	}





}
