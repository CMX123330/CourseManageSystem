package com.cmx.cms.model;
public class Classroom{
    private String classroomId;
    private String building;
    private int capacity;

    public Classroom(String building, int capacity, String classroomId) {
        this.building = building;
        this.capacity = capacity;
        this.classroomId = classroomId;
    }

    public Classroom() {
    }

    public String getClassroomId() {
        return classroomId;
    }

    public String getBuilding() {
        return building;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setClassroomId(String classroomId) {
        this.classroomId = classroomId;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Classroom{");
        sb.append("classroomId=").append(classroomId);
        sb.append(", building=").append(building);
        sb.append(", capacity=").append(capacity);
        sb.append('}');
        return sb.toString();
    }

}
