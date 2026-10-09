package com.cmx.cms.service;

import com.cmx.cms.model.SimState;

public class SimulationEngine {
    private static SimulationEngine instance = new SimulationEngine();

    public static SimulationEngine getInstance() {
        return instance;
    }

    private SimulationEngine() {
    }
    public SimState state = new SimState();
    public SimState getState()
    {
        return state;
    }
    public SimState tick() {
        if (!state.isStarted()) {
            state.setStarted(true);
            state.setWeek(1);
            state.getEvents().add("学期开始");
        } else {
            state.setWeek(state.getWeek() + 1);
            state.getEvents().add("第 " + state.getWeek() + " 周推进");
        }
        return state;
    }

}
