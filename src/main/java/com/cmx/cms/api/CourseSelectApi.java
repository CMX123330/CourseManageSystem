package com.cmx.cms.api;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cmx.cms.dao.StudentOfferingDao;
import com.cmx.cms.model.StudentOffering;
import com.cmx.cms.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
@WebServlet("/api/courseSelect")
public class CourseSelectApi extends HttpServlet{
    private ObjectMapper mapper = new ObjectMapper();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        resp.setContentType("application/json;charset=UTF-8");
        StudentOfferingDao dao = new StudentOfferingDao();
        Map<String,Object> result = new LinkedHashMap<>();
        User u =(User) req.getSession().getAttribute("user");
        String studentId = u.getUserId();
        String action = req.getParameter("action");
        if ("select".equals(action)) {
            try {
                if(dao.isValidOffering(req.getParameter("offeringId"), "2025-2026-1"))
                {
                    StudentOffering so = new StudentOffering();
                    so.setStudentId(studentId);
                    so.setOfferingId(req.getParameter("offeringId"));
                    try {
                        dao.add(so);
                        result.put("code", 200);
                        result.put("data", "选课成功");
                    } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                        result.put("code",500);
                        result.put("data", "选课失败");
                    }
                }

            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        } else if("drop".equals(action)){
            try {
                dao.delete(studentId, req.getParameter("offeringId"));
                result.put("code", 200);
                result.put("data","退课成功");
            } catch (Exception e) {
                // TODO: handle exception
                result.put("code", 500);
                result.put("data", "操作失败");
            }
        }
        resp.getWriter().write(mapper.writeValueAsString(result));
    }
}
