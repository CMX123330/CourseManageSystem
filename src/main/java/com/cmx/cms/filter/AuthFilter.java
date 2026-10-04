package com.cmx.cms.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cmx.cms.model.User;

@WebFilter("/*")
public class AuthFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String path = req.getRequestURI();
        if (path.endsWith("/login") || path.contains("/css/")) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");   // 未登录：踢 + 必须 return
            return;
        }
        User user = (User) session.getAttribute("user");
        String role = user.getRole();
        String uripath = req.getRequestURI();
        if ("admin".equals(role)) {
            chain.doFilter(request, response);
            return;
        }
        boolean allowed = false;
        if ("teacher".equals(role)) {
            allowed = uripath.contains("teachertable") || uripath.contains("classroomtable")
                    || uripath.contains("timetable") || uripath.endsWith("/");

        } else if ("student".equals(role)) {
            allowed = uripath.contains("/mytimetable") || uripath.contains("/courseSelect")
                    || uripath.endsWith("/");
        }
        if (allowed) {
            chain.doFilter(request, response);
        } else {
            resp.sendRedirect(req.getContextPath() + "/");
        }
    }

    @Override
    public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
