package com.cmx.cms.model;

public class OfferingView {
    private String offeringId;
    private String semesterId;
    private String semesterName;
    private String courseName;
    private String teacherName;
    private String weeklyHours;
    private String classNames;

    public OfferingView() {
    }

    public OfferingView(String offeringId, String semesterId, String semesterName, String courseName, String teacherName,
            String weeklyHours, String classNames) {
        this.offeringId = offeringId;
        this.semesterId = semesterId;
        this.semesterName = semesterName;
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.weeklyHours = weeklyHours;
        this.classNames = classNames;
    }

    public String getOfferingId() {
        return offeringId;
    }

    public void setOfferingId(String offeringId) {
        this.offeringId = offeringId;
    }

    public String getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(String semesterId) {
        this.semesterId = semesterId;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getWeeklyHours() {
        return weeklyHours;
    }

    public void setWeeklyHours(String weeklyHours) {
        this.weeklyHours = weeklyHours;
    }

    public String getClassNames() {
        return classNames;
    }

    public void setClassNames(String classNames) {
        this.classNames = classNames;
    }

}
