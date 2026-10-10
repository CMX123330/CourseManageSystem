package com.cmx.cms.model;

import java.math.BigDecimal;

public class StudentState {
    private String studentId;
    private String semesterId;
    private int energy;
    private int mood;
    private int diligence;
    private BigDecimal attendanceRate;

    public StudentState(String studentId, String semesterId, int energy, int mood, int diligence, BigDecimal attendanceRate) {
        this.studentId = studentId;
        this.semesterId = semesterId;
        this.energy = energy;
        this.mood = mood;
        this.diligence = diligence;
        this.attendanceRate = attendanceRate;
    }

    public StudentState() {
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(String semesterId) {
        this.semesterId = semesterId;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

    public int getDiligence() {
        return diligence;
    }

    public void setDiligence(int diligence) {
        this.diligence = diligence;
    }

    public BigDecimal getAttendanceRate() {
        return attendanceRate;
    }

    public void setAttendanceRate(BigDecimal attendanceRate) {
        this.attendanceRate = attendanceRate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("StudentState{");
        sb.append("studentId=").append(studentId);
        sb.append(", semesterId=").append(semesterId);
        sb.append(", energy=").append(energy);
        sb.append(", mood=").append(mood);
        sb.append(", diligence=").append(diligence);
        sb.append(", attendanceRate=").append(attendanceRate);
        sb.append('}');
        return sb.toString();
    }

}
