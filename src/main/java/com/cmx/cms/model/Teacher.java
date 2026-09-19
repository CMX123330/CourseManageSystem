package com.cmx.cms.model;

public class Teacher {
    private String teacherId;
    private String name;
    private String phone;
    private String gender;
    private String departmentId;
    private String title;

    public Teacher(String departmentId, String gender, String name, String phone, String teacherId, String title) {
        this.departmentId = departmentId;
        this.gender = gender;
        this.name = name;
        this.phone = phone;
        this.teacherId = teacherId;
        this.title = title;
    }

    public Teacher() {
    }

    public String getTeacherId() { return teacherId; }
    public void setTeacherId(String teacherId) { this.teacherId = teacherId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getDepartmentId() { return departmentId; }
    public void setDepartmentId(String departmentId) { this.departmentId = departmentId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    @Override
    public String toString() {
        return "Teacher{teacherId=" + teacherId + ", name=" + name + ", phone=" + phone
                + ", gender=" + gender + ", departmentId=" + departmentId + ", title=" + title + "}";
    }
}
