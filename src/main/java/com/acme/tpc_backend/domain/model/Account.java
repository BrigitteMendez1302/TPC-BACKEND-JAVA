package com.acme.tpc_backend.domain.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
@Entity
@Table(name = "accounts")
public class Account extends AuditModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "university_id", nullable = false)
	private University university;

	@NotNull
	@Length(max = 100)
	private String accountNumber;

	@NotNull
	@Length(max = 100)
	private String password;

	@OneToOne(mappedBy = "account")
	private User user;

	public Long getId() {
		return id;
	}

	public Account setId(Long id) {
		this.id = id;
		return this;
	}

	public Account(){}
	public University getUniversity() {
		return university;
	}

	public Account setUniversity(University university) {
		this.university = university;
		return this;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public Account setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public Account setPassword(String password) {
		this.password = password;
		return this;
	}

	public User getUser() {
		return user;
	}

	public Account setUser(User user) {
		this.user = user;
		return this;
	}
}
