package com.cmx.cms.api;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import com.cmx.cms.model.SimState;
import com.cmx.cms.service.SimulationEngine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet ("/api/sim/start")
public class SimStartApi extends HttpServlet{
    private ObjectMapper mapper = new ObjectMapper();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        super.doGet(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        Map<String, Object> result = new LinkedHashMap<>();
        try {
            // 必须用 getInstance()：new 一个就绕过单例了，状态全丢
            SimState state = SimulationEngine.getInstance().startSemester();
            result.put("code", 200);
            result.put("data", state);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", 500);
            result.put("data", "开始学期失败");
        }
        resp.getWriter().write(mapper.writeValueAsString(result));
    }
}
