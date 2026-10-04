package com.cmx.cms.model;

/**
 * 停课标记视图：把假期日期换算成"第几周 + 星期几"，
 * 课表格子才能和排课的周范围/星期比对。
 */
public class HolidayView {
    private String name;     // 假期名称
    private int week;        // 落在学期第几周（1 起）
    private int weekday;     // 星期几（1=周一 ... 7=周日）

    public HolidayView() {
    }

    public HolidayView(String name, int week, int weekday) {
        this.name = name;
        this.week = week;
        this.weekday = weekday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getWeekday() {
        return weekday;
    }

    public void setWeekday(int weekday) {
        this.weekday = weekday;
    }
}
