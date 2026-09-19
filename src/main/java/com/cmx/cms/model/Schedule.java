package com.cmx.cms.model;

public class Schedule {
    private String scheduleId;
    private String offeringId;
    private String classroomId;
    private Integer weekday;
    private Integer startSlot;
    private Integer slotCount;
    private Integer startWeek;
    private Integer endWeek;
    private String weekType;

    public Schedule(String classroomId, Integer endWeek, String offeringId, String scheduleId,
                    Integer slotCount, Integer startSlot, Integer startWeek, Integer weekday, String weekType) {
        this.classroomId = classroomId;
        this.endWeek = endWeek;
        this.offeringId = offeringId;
        this.scheduleId = scheduleId;
        this.slotCount = slotCount;
        this.startSlot = startSlot;
        this.startWeek = startWeek;
        this.weekday = weekday;
        this.weekType = weekType;
    }

    public Schedule() {
    }

    public String getScheduleId() { return scheduleId; }
    public void setScheduleId(String scheduleId) { this.scheduleId = scheduleId; }
    public String getOfferingId() { return offeringId; }
    public void setOfferingId(String offeringId) { this.offeringId = offeringId; }
    public String getClassroomId() { return classroomId; }
    public void setClassroomId(String classroomId) { this.classroomId = classroomId; }
    public Integer getWeekday() { return weekday; }
    public void setWeekday(Integer weekday) { this.weekday = weekday; }
    public Integer getStartSlot() { return startSlot; }
    public void setStartSlot(Integer startSlot) { this.startSlot = startSlot; }
    public Integer getSlotCount() { return slotCount; }
    public void setSlotCount(Integer slotCount) { this.slotCount = slotCount; }
    public Integer getStartWeek() { return startWeek; }
    public void setStartWeek(Integer startWeek) { this.startWeek = startWeek; }
    public Integer getEndWeek() { return endWeek; }
    public void setEndWeek(Integer endWeek) { this.endWeek = endWeek; }
    public String getWeekType() { return weekType; }
    public void setWeekType(String weekType) { this.weekType = weekType; }

    @Override
    public String toString() {
        return "Schedule{scheduleId=" + scheduleId + ", offeringId=" + offeringId + ", weekday=" + weekday
                + ", startSlot=" + startSlot + ", slotCount=" + slotCount + ", classroomId=" + classroomId
                + ", startWeek=" + startWeek + ", endWeek=" + endWeek + ", weekType=" + weekType + "}";
    }
}
