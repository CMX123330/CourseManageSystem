package com.cmx.cms.model;

public class OfferingClass {
    private String offeringId;
    private String classId;

    public OfferingClass(String classId, String offeringId) {
        this.classId = classId;
        this.offeringId = offeringId;
    }

    public OfferingClass() {
    }

    public String getOfferingId() { return offeringId; }
    public void setOfferingId(String offeringId) { this.offeringId = offeringId; }
    public String getClassId() { return classId; }
    public void setClassId(String classId) { this.classId = classId; }

    @Override
    public String toString() {
        return "OfferingClass{offeringId=" + offeringId + ", classId=" + classId + "}";
    }
}
