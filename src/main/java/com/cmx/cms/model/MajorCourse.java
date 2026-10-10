package com.cmx.cms.model;

public class MajorCourse {
    private String majorId;
    private String courseId;
    
    public MajorCourse() {
    }
    public MajorCourse(String majorId, String courseId) {
        this.majorId = majorId;
        this.courseId = courseId;
    }
    public String getMajorId() {
        return majorId;
    }
    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }
    public String getCourseId() {
        return courseId;
    }
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

}
