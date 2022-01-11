package com.acme.tpc_backend.resource;
public class LessonResource extends MeetingResource{
    private TutorResource tutor;

    public TutorResource getTutor() {
        return tutor;
    }

    public void setTutor(TutorResource tutor) {
        this.tutor = tutor;
    }

    public LessonTypeResource getLessonType() {
        return lessonType;
    }

    public void setLessonType(LessonTypeResource lessonType) {
        this.lessonType = lessonType;
    }

    public CourseResource getCourse() {
        return course;
    }

    public void setCourse(CourseResource course) {
        this.course = course;
    }

    public int getVacants() {
        return vacants;
    }

    public void setVacants(int vacants) {
        this.vacants = vacants;
    }

    private LessonTypeResource lessonType;
    private CourseResource course;
    private int vacants;
}