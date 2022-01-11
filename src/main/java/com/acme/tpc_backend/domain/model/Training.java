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
@Table(name = "trainings")
public class Training extends  Meeting{

	public Training(Coordinator coordinator, List<TrainingTutor> trainingTutors) {
		this.coordinator = coordinator;
		this.trainingTutors = trainingTutors;
	}

	public Training() {

	}

	public Coordinator getCoordinator() {
		return coordinator;
	}

	public Training setCoordinator(Coordinator coordinator) {
		this.coordinator = coordinator;
		return this;
	}

	public List<TrainingTutor> getTrainingTutors() {
		return trainingTutors;
	}

	public void setTrainingTutors(List<TrainingTutor> trainingTutors) {
		this.trainingTutors = trainingTutors;
	}

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "coordinator_id", nullable = false)
	private Coordinator coordinator;

	@OneToMany(mappedBy = "training", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<TrainingTutor> trainingTutors;
}
