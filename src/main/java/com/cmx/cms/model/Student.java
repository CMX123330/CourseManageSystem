package com.cmx.cms.model;

public class Student {
    private String studentId;
    private String name;
    private String gender;
    private String classId;
    private String phone;

    public Student(String classId, String gender, String name, String phone, String studentId) {
        this.classId = classId;
        this.gender = gender;
        this.name = name;
        this.phone = phone;
        this.studentId = studentId;
    }

    public Student() {
    }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getClassId() { return classId; }
    public void setClassId(String classId) { this.classId = classId; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    @Override
    public String toString() {
        return "Student{studentId=" + studentId + ", name=" + name + ", gender=" + gender
                + ", classId=" + classId + ", phone=" + phone + "}";
    }
}
