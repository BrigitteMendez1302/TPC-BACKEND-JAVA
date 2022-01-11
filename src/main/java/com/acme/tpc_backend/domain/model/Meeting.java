package com.acme.tpc_backend.domain.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Inheritance(strategy=InheritanceType.JOINED)
@Entity
@Table(name = "meetings")
public class Meeting extends AuditModel{
	public Long getId() {
		return id;
	}

	public Meeting setId(Long id) {
		this.id = id;
		return this;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Meeting setStartDate(Date startDate) {
		this.startDate = startDate;
		return this;
	}

	public Date getEndDate() {
		return endDate;
	}

	public Meeting setEndDate(Date endDate) {
		this.endDate = endDate;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public Meeting setDescription(String description) {
		this.description = description;
		return this;
	}

	public String getMeetingLink() {
		return meetingLink;
	}

	public void setMeetingLink(String meetingLink) {
		this.meetingLink = meetingLink;
	}

	public String getResourceLink() {
		return resourceLink;
	}

	public void setResourceLink(String resourceLink) {
		this.resourceLink = resourceLink;
	}

	public String getCalendarId(){return calendarId;}
	public void setCalendarId(String calendarId){this.calendarId = calendarId;}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
	@DateTimeFormat
	private Date startDate;

	@NotNull
	@DateTimeFormat
	private Date endDate;

	@NotNull
	@Lob
	private String description;

	@NotNull
	@URL
	private String meetingLink;

	@NotNull
	@URL
	private String resourceLink;

	private String calendarId;
}
