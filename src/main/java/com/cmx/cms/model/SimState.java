package com.cmx.cms.model;

import java.util.ArrayList;
import java.util.List;

public class SimState {
    private int week = 0;
    private String semesterId = "2025-2026-1";
    private boolean started = false;
    private List<String> events = new ArrayList<>();

    public SimState(int week, String semesterId, boolean started, List<String> events) {
        this.week = week;
        this.semesterId = semesterId;
        this.started = started;
        this.events = events;
    }

    public SimState() {
    }


    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public String getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(String semesterId) {
        this.semesterId = semesterId;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public List<String> getEvents() {
        return events;
    }

    public void setEvents(List<String> events) {
        this.events
         = events;
    }

}
