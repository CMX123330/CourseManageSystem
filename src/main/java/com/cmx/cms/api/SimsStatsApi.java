package com.cmx.cms.api;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cmx.cms.service.SimulationEngine;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/api/sims/stats")
public class SimsStatsApi extends HttpServlet {
    private ObjectMapper mapper = new ObjectMapper();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        Map<String,Object> result = new LinkedHashMap<>();
        try {
            result.put("code", 200);
            result.put("data", SimulationEngine.getInstance().getStats());
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            result.put("code", 500);
            result.put("data", null);
        }
        resp.getWriter().write(mapper.writeValueAsString(result));
    }
}
