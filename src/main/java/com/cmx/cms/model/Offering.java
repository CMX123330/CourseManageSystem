package com.cmx.cms.model;

public class Offering {
    private String offeringId;
    private String semesterId;
    private String courseId;
    private String teacherId;
    private Integer weeklyHours;

    public Offering(String courseId, String offeringId, String semesterId, String teacherId, Integer weeklyHours) {
        this.courseId = courseId;
        this.offeringId = offeringId;
        this.semesterId = semesterId;
        this.teacherId = teacherId;
        this.weeklyHours = weeklyHours;
    }

    public Offering() {
    }

    public String getOfferingId() { return offeringId; }
    public void setOfferingId(String offeringId) { this.offeringId = offeringId; }
    public String getSemesterId() { return semesterId; }
    public void setSemesterId(String semesterId) { this.semesterId = semesterId; }
    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }
    public String getTeacherId() { return teacherId; }
    public void setTeacherId(String teacherId) { this.teacherId = teacherId; }
    public Integer getWeeklyHours() { return weeklyHours; }
    public void setWeeklyHours(Integer weeklyHours) { this.weeklyHours = weeklyHours; }

    @Override
    public String toString() {
        return "Offering{offeringId=" + offeringId + ", semesterId=" + semesterId + ", courseId=" + courseId
                + ", teacherId=" + teacherId + ", weeklyHours=" + weeklyHours + "}";
    }
}
