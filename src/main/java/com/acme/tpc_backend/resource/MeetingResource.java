package com.acme.tpc_backend.resource;

import com.acme.tpc_backend.domain.model.AuditModel;

import java.util.Date;

public abstract class MeetingResource extends AuditModel {
    private Long id;
    private Date startDate;
    private Date endDate;
    private String description;
    private String calendarId;

    public String getCalendarId(){
        return calendarId;
    }

    public void setCalendarId(){
        this.calendarId = calendarId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    private String meetingLink;
    private String resourceLink;
}