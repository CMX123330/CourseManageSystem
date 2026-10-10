package com.cmx.cms.util;

import com.cmx.cms.service.SimulationEngine;

/** 自动开课验证：调引擎方法，看 offering 表长出 AK 编号记录 */
public class TestAutoOfferings {
    public static void main(String[] args) throws Exception {
        SimulationEngine engine = SimulationEngine.getInstance();
        int count = engine.autoCreateOfferings();
        System.out.println("自动开课数: " + count);
    }
}
