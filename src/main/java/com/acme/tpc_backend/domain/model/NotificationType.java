package com.acme.tpc_backend.domain.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "notification_types")
public class NotificationType extends AuditModel{
	public Long getId() {
		return id;
	}

	public NotificationType setId(Long id) {
		this.id = id;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public NotificationType setDescription(String description) {
		this.description = description;
		return this;
	}

	public List<Notification> getNotifications() {
		return notifications;
	}

	public NotificationType setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
		return this;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Lob
	private String description;

	@OneToMany(mappedBy = "notificationType", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Notification> notifications;
}
