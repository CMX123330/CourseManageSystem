package com.cmx.cms.util;

import com.cmx.cms.service.SimulationEngine;

/** 自动排课验证：先自动开课，再自动排课，看 schedule 表长出 AS 编号 */
public class TestAutoSchedule {
    public static void main(String[] args) throws Exception {
        SimulationEngine engine = SimulationEngine.getInstance();
        int offerings = engine.autoCreateOfferings();
        System.out.println("自动开课数: " + offerings);
        int scheduled = engine.autoSchedule();
        System.out.println("自动排课数: " + scheduled);
        System.out.println("事件流水: " + engine.getState().getEvents());
    }
}
