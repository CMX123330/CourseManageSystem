package com.cmx.cms.model;

public class StudentOffering {
    private String studentId;
    private String offeringId;

    public StudentOffering() {
    }

    public StudentOffering(String studentId, String offeringId) {
        this.studentId = studentId;
        this.offeringId = offeringId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getOfferingId() {
        return offeringId;
    }

    public void setOfferingId(String offeringId) {
        this.offeringId = offeringId;
    }
}
