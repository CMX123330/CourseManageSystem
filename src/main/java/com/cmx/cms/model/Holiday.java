package com.cmx.cms.model;

import java.sql.Date;

public class Holiday {
    private String holidayId;
    private Date holidayDate;
    private String name;

    public Holiday() {
    }

    public Holiday(String holidayId, Date holidayDate, String name) {
        this.holidayId = holidayId;
        this.holidayDate = holidayDate;
        this.name = name;
    }

    public String getHolidayId() {
        return holidayId;
    }

    public void setHolidayId(String holidayId) {
        this.holidayId = holidayId;
    }

    public Date getHolidayDate() {
        return holidayDate;
    }

    public void setHolidayDate(Date holidayDate) {
        this.holidayDate = holidayDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
