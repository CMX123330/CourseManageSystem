package com.cmx.cms.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cmx.cms.dao.SlotDao;
import com.cmx.cms.model.Slot;
@WebServlet("/slot")
public class SlotServlet extends HttpServlet{

        private SlotDao slotDao = new SlotDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("add".equals(action)) {
            req.getRequestDispatcher("/WEB-INF/jsp/slot-form.jsp").forward(req, resp);
        } else if ("edit".equals(action)) {
            Slot slot = new Slot();
            String id = req.getParameter("id");
            try {
                slot = slotDao.getById(id);
                req.setAttribute("slot", slot);
                req.getRequestDispatcher("/WEB-INF/jsp/slot-form.jsp").forward(req, resp);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                List<Slot> slots = slotDao.getAll();
                req.setAttribute("slots", slots);
                req.getRequestDispatcher("/WEB-INF/jsp/slot-list.jsp").forward(req, resp);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if ("save".equals(action)) {
            Slot s;
            s = new Slot();
            s.setSlotId(Integer.parseInt(req.getParameter("slotId")));
            s.setStartTime(Time.valueOf(fixTime(req.getParameter("startTime"))));
            s.setEndTime(Time.valueOf(fixTime(req.getParameter("endTime"))));
            try {
                slotDao.add(s);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if ("update".equals(action)) {
            Slot s = new Slot();
            s.setSlotId(Integer.parseInt(req.getParameter("slotId")));
            s.setStartTime(Time.valueOf(fixTime(req.getParameter("startTime"))));
            s.setEndTime(Time.valueOf(fixTime(req.getParameter("endTime"))));
            try {
                slotDao.update(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("delete".equals(action)) {
            try {
                slotDao.delete(Integer.parseInt(req.getParameter("id")));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        resp.sendRedirect(req.getContextPath() + "/slot");
    }

    private String fixTime(String t) {
        return (t != null && t.length() == 5) ? t + ":00" : t;
    }

}