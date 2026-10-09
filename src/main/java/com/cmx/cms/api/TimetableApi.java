package com.cmx.cms.api;


import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cmx.cms.dao.ScheduleDao;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/api/timetable")
public class TimetableApi extends HttpServlet{
    private ObjectMapper mapper = new ObjectMapper();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        resp.setContentType("application/json;charset=UTF-8");
        Map<String,Object> result = new LinkedHashMap<>();
        try {
            result.put("code", 200);
            String classId=req.getParameter("classId");
            String semesterId=req.getParameter("semesterId");
            result.put("data", new ScheduleDao().getByClassId(classId, semesterId));
        } catch (Exception e) {
            // TODO: handle exception
            result.put("code", 500);
            result.put("data", null);
        }
        resp.getWriter().write(mapper.writeValueAsString(result));
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        super.doPost(req, resp);
    }
}
