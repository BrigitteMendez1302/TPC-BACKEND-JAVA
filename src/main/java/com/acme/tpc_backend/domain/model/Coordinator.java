package com.acme.tpc_backend.domain.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName="id")
@OnDelete(action = OnDeleteAction.CASCADE)
@Table(name = "coordinators")
public class Coordinator extends User{

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public List<MailMessage> getMailMessage() {
		return mailMessage;
	}

	public void setMailMessage(List<MailMessage> mailMessage) {
		this.mailMessage = mailMessage;
	}

	public List<Training> getTrainings() {
		return trainings;
	}

	public void setTrainings(List<Training> trainings) {
		this.trainings = trainings;
	}

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "faculty_id", nullable = false)
	private Faculty faculty;

	@OneToMany(mappedBy = "coordinator", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<MailMessage> mailMessage;


	@OneToMany(mappedBy = "coordinator", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Training> trainings;

	public Coordinator(Account account, String firstName, String lastName, String mail, Long phoneNumber, Faculty faculty) {
		super(account, firstName, lastName, mail, phoneNumber);
		this.faculty = faculty;
	}

	public Coordinator() {
	}
}
