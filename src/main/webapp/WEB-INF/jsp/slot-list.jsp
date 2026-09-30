<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>节次时间段</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <h1>节次时间段</h1>
    <a href="${pageContext.request.contextPath}/slot?action=add">新增</a>
    <table border="1">
        <tr>
            <th>节次编号</th>
            <th>开始时间</th>
            <th>结束时间</th>
            <th>操作</th>
        </tr>
        <c:forEach var="s" items="${slots}">
            <tr>
                <td>${s.slotId}</td>
                <td>${s.startTime}</td>
                <td>${s.endTime}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/slot?action=edit&id=${s.slotId}">编辑</a>
                    <form action="${pageContext.request.contextPath}/slot" method="post"
                        onsubmit="return confirm('确认删除该时间段？');" style="display:inline">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="id" value="${s.slotId}">
                        <button type="submit">删除</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>