package com.cmx.cms.model;
public class Clazz{
    private String classId;
    private String name;
    private String majorId;
    private int grade;
    private int studentCount;

    public Clazz(String classId, int grade, String majorId, String name, int studentCount) {
        this.classId = classId;
        this.grade = grade;
        this.majorId = majorId;
        this.name = name;
        this.studentCount = studentCount;
    }

    public Clazz() {
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    @Override
    public String toString() {
        return "Clazz{classId=" + classId + ", name=" + name + ", majorId=" + majorId
                + ", grade=" + grade + ", studentCount=" + studentCount + "}";
    }
}
