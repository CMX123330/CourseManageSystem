package com.cmx.cms.model;

public class ScheduleChange {
    private String changeId;
    private String scheduleId;
    private String offeringId;
    private String teacherId;
    private Integer targetWeekday;
    private Integer targetStartSlot;
    private Integer targetSlotCount;
    private String targetClassroomId;
    private String reason;
    private String status;

    public ScheduleChange() {
    }

    public ScheduleChange(String changeId, String scheduleId, String offeringId, String teacherId,
            Integer targetWeekday, Integer targetStartSlot, Integer targetSlotCount,
            String targetClassroomId, String reason, String status) {
        this.changeId = changeId;
        this.scheduleId = scheduleId;
        this.offeringId = offeringId;
        this.teacherId = teacherId;
        this.targetWeekday = targetWeekday;
        this.targetStartSlot = targetStartSlot;
        this.targetSlotCount = targetSlotCount;
        this.targetClassroomId = targetClassroomId;
        this.reason = reason;
        this.status = status;
    }

    public String getChangeId() {
        return changeId;
    }

    public void setChangeId(String changeId) {
        this.changeId = changeId;
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

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getTargetWeekday() {
        return targetWeekday;
    }

    public void setTargetWeekday(Integer targetWeekday) {
        this.targetWeekday = targetWeekday;
    }

    public Integer getTargetStartSlot() {
        return targetStartSlot;
    }

    public void setTargetStartSlot(Integer targetStartSlot) {
        this.targetStartSlot = targetStartSlot;
    }

    public Integer getTargetSlotCount() {
        return targetSlotCount;
    }

    public void setTargetSlotCount(Integer targetSlotCount) {
        this.targetSlotCount = targetSlotCount;
    }

    public String getTargetClassroomId() {
        return targetClassroomId;
    }

    public void setTargetClassroomId(String targetClassroomId) {
        this.targetClassroomId = targetClassroomId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
