package com.acme.tpc_backend.domain.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Inheritance(strategy=InheritanceType.JOINED)
@Entity
@Table(name = "users")
public class User extends AuditModel{
	public Long getId() {
		return id;
	}

	public User setId(Long id) {
		this.id = id;
		return this;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getFirstName() {
		return firstName;
	}

	public User setFirstName(String firstName) {
		this.firstName = firstName;
        return this;
    }

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	public Integer getRole(){
		return role;
	}
	public void setRole(Integer role){
		this.role = role;
	}
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "account_id", unique = true)
	private Account account;

    @NotNull
	@Size(max = 30)
	private String firstName;

	@NotNull
	@Size(max = 30)
	private String lastName;

	@NotNull
	@Email
	@Size(max = 30)
	private String mail;

	@NotNull
	private Long phoneNumber;

	@NotNull
	private Integer role; //1: student, 2:tutor, 3:coordinator

	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE},
			mappedBy = "users")
	private List<Notification> notifications;

	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE},
			mappedBy = "users")
	private List<Course> courses;

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public List<Suggestion> getSuggestions() {
		return suggestions;
	}

	public User setSuggestions(List<Suggestion> suggestions) {
		this.suggestions = suggestions;
		return this;
	}

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Suggestion> suggestions;

	public User(Account account, String firstName, String lastName, String mail, Long phoneNumber) {
		this.account = account;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mail = mail;
		this.phoneNumber = phoneNumber;
	}
	public User() {

	}
}
