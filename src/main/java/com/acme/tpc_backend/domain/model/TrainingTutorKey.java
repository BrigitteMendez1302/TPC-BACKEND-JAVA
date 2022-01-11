package com.acme.tpc_backend.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TrainingTutorKey implements Serializable {
    @Column(name = "training_id")
    private Long trainingId;

    @Column(name = "tutor_id")
    private Long tutorId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrainingTutorKey)) return false;
        TrainingTutorKey that = (TrainingTutorKey) o;
        return trainingId.equals(that.trainingId) && tutorId.equals(that.tutorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trainingId, tutorId);
    }
}
