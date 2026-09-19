package com.cmx.cms.model;
public class Major{
    private String majorId;
    private String name;
    private String departmentId;

    public Major() {
    }

    public Major(String departmentId, String majorId, String name) {
        this.departmentId = departmentId;
        this.majorId = majorId;
        this.name = name;
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Major{");
        sb.append("majorId=").append(majorId);
        sb.append(", name=").append(name);
        sb.append(", departmentId=").append(departmentId);
        sb.append('}');
        return sb.toString();
    }

}