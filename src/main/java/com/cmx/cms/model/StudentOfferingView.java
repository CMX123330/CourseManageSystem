package com.cmx.cms.model;

public class StudentOfferingView {
    private String offeringId;
    private String courseName;
    private String teacherName;
    private Integer weeklyHours;

    public StudentOfferingView() {
    }

    public StudentOfferingView(String offeringId, String courseName, String teacherName, Integer weeklyHours) {
        this.offeringId = offeringId;
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.weeklyHours = weeklyHours;
    }

    public String getOfferingId() {
        return offeringId;
    }

    public void setOfferingId(String offeringId) {
        this.offeringId = offeringId;
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

    public Integer getWeeklyHours() {
        return weeklyHours;
    }

    public void setWeeklyHours(Integer weeklyHours) {
        this.weeklyHours = weeklyHours;
    }
}
