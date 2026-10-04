<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>我的调课</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <h1>我的调课</h1>
    <a href="${pageContext.request.contextPath}/">返回首页</a>
    <table>
        <tr>
            <th>编号</th><th>课程</th><th>星期</th><th>节次</th><th>教室</th><th>操作</th>
        </tr>
        <c:forEach var="s" items="${mySchedules}">
            <tr>
                <td>${s.scheduleId}</td>
                <td>${s.courseName}</td>
                <td>星期${s.weekday}</td>
                <td>第${s.startSlot}节</td>
                <td>${s.classroomId}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/scheduleChange?action=apply&scheduleId=${s.scheduleId}">申请调课</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
