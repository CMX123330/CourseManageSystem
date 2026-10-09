package com.cmx.cms.api;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cmx.cms.dao.OfferingDao;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/api/offerings")
public class OfferingsApi extends HttpServlet {

    private ObjectMapper mapper = new ObjectMapper();   // ① JSON 翻译官：对象 ↔ JSON 字符串

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");   // ② 声明"下面是 JSON 不是 HTML"

        Map<String, Object> result = new LinkedHashMap<>();      // ③ 统一响应结构 {code, data}
        try {
            result.put("code", 200);                              // 成功标志
            result.put("data", new OfferingDao().getViewList(null));  // ④ 复用 v1 的 DAO
        } catch (SQLException e) {
            e.printStackTrace();
            result.put("code", 500);
            result.put("data", null);
        }

        resp.getWriter().write(mapper.writeValueAsString(result));  // ⑤ 翻译 + 输出
    }
}
