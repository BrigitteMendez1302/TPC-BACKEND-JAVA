package com.acme.tpc_backend.domain.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
@Entity
@Table(name = "training_tutors")
public class TrainingTutor extends AuditModel{
	public Tutor getTutor() {
		return tutor;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}

	public Training getTraining() {
		return training;
	}

	public void setTraining(Training training) {
		this.training = training;
	}

	public boolean getAssistance() {
		return assistance;
	}

	public void setAssistance(boolean assistance) {
		this.assistance = assistance;
	}

	@EmbeddedId
	TrainingTutorKey id;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@MapsId("tutorId")
	@JoinColumn(name = "tutor_id", nullable = false)
	private Tutor tutor;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@MapsId("trainingId")
	@JoinColumn(name = "training_id", nullable = false)
	private Training training;

	@NotNull
	private boolean assistance;
}
