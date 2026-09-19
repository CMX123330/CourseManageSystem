package com.cmx.cms.model;
import java.sql.Time;
public class Slot{
    private int slotId;
    private Time startTime;
    private Time endTime;

    public Slot(Time endTime, int slotId, Time startTime) {
        this.endTime = endTime;
        this.slotId = slotId;
        this.startTime = startTime;
    }

    public Slot() {
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Slot{");
        sb.append("slotId=").append(slotId);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append('}');
        return sb.toString();
    }

}
