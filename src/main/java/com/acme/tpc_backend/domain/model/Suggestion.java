package com.acme.tpc_backend.domain.model;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
@Entity
@Table(name = "suggestions")
public class Suggestion extends AuditModel{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Lob
	private String message;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	public Long getId() {
		return id;
	}

	public Suggestion setId(Long id) {
		this.id = id;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public Suggestion setMessage(String message) {
		this.message = message;
		return this;
	}

	public User getUser() {
		return user;
	}

	public Suggestion setUser(User user) {
		this.user = user;
		return this;
	}

}
