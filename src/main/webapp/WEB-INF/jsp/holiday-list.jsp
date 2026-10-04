<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>节假日管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <h1>节假日管理</h1>
    <a href="${pageContext.request.contextPath}/">返回首页</a>
    <a href="${pageContext.request.contextPath}/holiday?action=add">新增假期</a>
    <table>
        <tr>
            <th>编号</th><th>日期</th><th>名称</th><th>操作</th>
        </tr>
        <c:forEach var="h" items="${holidays}">
            <tr>
                <td>${h.holidayId}</td>
                <td>${h.holidayDate}</td>
                <td>${h.name}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/holiday" method="post"
                        onsubmit="return confirm('确认删除该假期？');" style="display:inline">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="id" value="${h.holidayId}">
                        <button type="submit">删除</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
