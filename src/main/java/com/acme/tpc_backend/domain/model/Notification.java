package com.acme.tpc_backend.domain.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "notifications")
public class Notification extends AuditModel {
	public Long getId() {
		return id;
	}

	public Notification setId(Long id) {
		this.id = id;
		return this;
	}

	public NotificationType getNotificationType() {
		return notificationType;
	}

	public Notification setNotificationType(NotificationType notificationType) {
		this.notificationType = notificationType;
		return this;
	}

	public String getContent() {
		return content;
	}

	public Notification setContent(String content) {
		this.content = content;
		return this;
	}

	public String getLink() {
		return link;
	}

	public Notification setLink(String link) {
		this.link = link;
		return this;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public Notification setSendDate(Date sendDate) {
		this.sendDate = sendDate;
		return this;
	}

	public List<User> getUsers() {
		return users;
	}

	public Notification setUsers(List<User> users) {
		this.users = users;
		return this;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "notification_type_id", nullable = false)
	private NotificationType notificationType;

	@NotNull
	@Lob
	private String content;

	@NotNull
	@URL
	private String link;

	@NotNull
	@DateTimeFormat
	private Date sendDate;

	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "notification_users",
			joinColumns = {@JoinColumn(name = "notification_id")},
			inverseJoinColumns = { @JoinColumn(name = "user_id")})
	private List<User> users;
}
