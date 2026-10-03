package com.cmx.cms.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cmx.cms.dao.UserDao;
import com.cmx.cms.model.User;
@WebServlet("/login")
public class LoginServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");
        User user;
        try {
            user = new UserDao().getByCredentials(userId, new UserDao().md5(password));
            if (user != null) {
                // 登录成功：把用户写进会员卡（session）→ 重定向到首页
                req.getSession().setAttribute("user", user);
                resp.sendRedirect(req.getContextPath() + "/");
            } else {
                // 登录失败：错误放 request（一次性快递单）→ 转发回登录页显示
                req.setAttribute("error", "账号或密码错误");
                req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
