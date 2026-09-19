package com.cmx.cms.model;

import java.math.BigDecimal;

public class Course{
    private String courseId;
    private String name;
    private int hours;
    private String examType;
    private BigDecimal credit;
    private String departmentId;
    private String nature;

    public Course(String courseId, BigDecimal credit, String departmentId, String examType, int hours, String name, String nature) {
        this.courseId = courseId;
        this.credit = credit;
        this.departmentId = departmentId;
        this.examType = examType;
        this.hours = hours;
        this.name = name;
        this.nature = nature;
    }

    public Course() {
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Course{");
        sb.append("courseId=").append(courseId);
        sb.append(", name=").append(name);
        sb.append(", hours=").append(hours);
        sb.append(", examType=").append(examType);
        sb.append(", credit=").append(credit);
        sb.append(", departmentId=").append(departmentId);
        sb.append(", nature=").append(nature);
        sb.append('}');
        return sb.toString();
    }

}