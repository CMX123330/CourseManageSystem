package com.cmx.cms.model;
public class Department{
    private String departmentId;
    private String name;
    private String dean;
    private String officePhone;

    public Department(String dean, String departmentId, String officePhone, String name) {
        this.dean = dean;
        this.departmentId = departmentId;
        this.officePhone = officePhone;
        this.name = name;
    }

    public Department() {
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public String getName() {
        return name;
    }

    public String getDean() {
        return dean;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDean(String dean) {
        this.dean = dean;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Department{");
        sb.append("departmentId=").append(departmentId);
        sb.append(", name=").append(name);
        sb.append(", dean=").append(dean);
        sb.append(", officePhone=").append(officePhone);
        sb.append('}');
        return sb.toString();
    }

}
