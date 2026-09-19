<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>学期列表</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <h1>学期列表</h1>
    <a href="${pageContext.request.contextPath}/semester?action=add">新增</a>
    <table border="1">
        <tr>
            <th>学期编号</th>
            <th>学期名称</th>
            <th>开始日期</th>
            <th>结束日期</th>
            <th>总周数</th>
            <th>操作</th>
        </tr>
        <c:forEach var="s" items="${semesters}">
            <tr>
                <td>${s.semesterId}</td>
                <td>${s.name}</td>
                <td>${s.startDate}</td>
                <td>${s.endDate}</td>
                <td>${s.totalWeeks}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/semester?action=edit&id=${s.semesterId}">编辑</a>
                    <form action="${pageContext.request.contextPath}/semester" method="post" onsubmit="return confirm('确认删除该学期？');" style="display:inline">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="id" value="${s.semesterId}">
                        <button type="submit">删除</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
