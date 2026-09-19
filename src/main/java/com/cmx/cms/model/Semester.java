package com.cmx.cms.model;
import java.sql.Date;
public class Semester{
    private String semesterId;
    private String name;
    private Date startDate;
    private Date endDate;
    private int totalWeeks;

    public Semester(Date endDate, String semesterId, Date startDate, int totalWeeks, String name) {
        this.endDate = endDate;
        this.semesterId = semesterId;
        this.startDate = startDate;
        this.totalWeeks = totalWeeks;
        this.name = name;
    }

    public Semester() {
    }

    public String getSemesterId() {
        return semesterId;
    }

    public String getName() {
        return name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getTotalWeeks() {
        return totalWeeks;
    }

    public void setSemesterId(String semesterId) {
        this.semesterId = semesterId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setTotalWeeks(int totalWeeks) {
        this.totalWeeks = totalWeeks;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Semester{");
        sb.append("semesterId=").append(semesterId);
        sb.append(", name=").append(name);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", totalWeeks=").append(totalWeeks);
        sb.append('}');
        return sb.toString();
    }

}
