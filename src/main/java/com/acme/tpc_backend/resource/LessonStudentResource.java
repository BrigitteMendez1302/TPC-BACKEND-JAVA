package com.acme.tpc_backend.resource;

import com.acme.tpc_backend.domain.model.AuditModel;

public class LessonStudentResource extends AuditModel {
    private StudentResource student;
    private LessonResource lesson;

    public StudentResource getStudent() {
        return student;
    }

    public void setStudent(StudentResource student) {
        this.student = student;
    }

    public LessonResource getLesson() {
        return lesson;
    }

    public void setLesson(LessonResource lesson) {
        this.lesson = lesson;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getQualification() {
        return qualification;
    }

    public void setQualification(int qualification) {
        this.qualification = qualification;
    }

    public boolean isComplaint() {
        return complaint;
    }

    public void setComplaint(boolean complaint) {
        this.complaint = complaint;
    }

    public boolean isAssistance() {
        return assistance;
    }

    public void setAssistance(boolean assistance) {
        this.assistance = assistance;
    }

    private String topic;
    private String comment;
    private int qualification;
    private boolean complaint;
    private boolean assistance;
}