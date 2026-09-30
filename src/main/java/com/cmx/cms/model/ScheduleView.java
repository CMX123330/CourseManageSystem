package com.cmx.cms.model;

public class ScheduleView {
    private String scheduleId;
    private String offeringId;
    private String courseName;
    private String teacherName;
    private String classroomId;
    private int weekday;
    private int startSlot;
    private int slotCount;
    private int startWeek;
    private int endWeek;
    private String weekType;
    public ScheduleView() {
    }
    public ScheduleView(String scheduleId, String offeringId, String courseName, String teacherName, String classroomId,
            int weekday, int startSlot, int slotCount, int startWeek, int endWeek, String weekType) {
        this.scheduleId = scheduleId;
        this.offeringId = offeringId;
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.classroomId = classroomId; 
        this.weekday = weekday;
        this.startSlot = startSlot;
        this.slotCount = slotCount;
        this.startWeek = startWeek;
        this.endWeek = endWeek;
        this.weekType = weekType;
    }
    public String getScheduleId() {
        return scheduleId;
    }
    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }
    public String getOfferingId() {
        return offeringId;
    }
    public void setOfferingId(String offeringId) {
        this.offeringId = offeringId;
    }
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public String getTeacherName() {
        return teacherName;
    }
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
    public String getClassroomId() {
        return classroomId;
    }
    public void setClassroomId(String classroomId) {
        this.classroomId = classroomId;
    }
    public int getWeekday() {
        return weekday;
    }
    public void setWeekday(int weekday) {
        this.weekday = weekday;
    }
    public int getStartSlot() {
        return startSlot;
    }
    public void setStartSlot(int startSlot) {
        this.startSlot = startSlot;
    }
    public int getSlotCount() {
        return slotCount;
    }
    public void setSlotCount(int slotCount) {
        this.slotCount = slotCount;
    }
    public int getStartWeek() {
        return startWeek;
    }
    public void setStartWeek(int startWeek) {
        this.startWeek = startWeek;
    }
    public int getEndWeek() {
        return endWeek;
    }
    public void setEndWeek(int endWeek) {
        this.endWeek = endWeek;
    }
    public String getWeekType() {
        return weekType;
    }
    public void setWeekType(String weekType) {
        this.weekType = weekType;
    }
    
}
