<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>排课列表</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <h1>排课列表</h1>
    <a href="${pageContext.request.contextPath}/schedule?action=add">新增排课</a>
    <table>
        <tr>
            <th>编号</th>
            <th>课程</th>
            <th>教师</th>
            <th>教室</th>
            <th>星期</th>
            <th>节次</th>
            <th>周范围</th>
            <th>单双周</th>
            <th>操作</th>
        </tr>
        <c:forEach var="s" items="${schedules}">
            <tr>
                <td>${s.scheduleId}</td>
                <td>${s.courseName}</td>
                <td>${s.teacherName}</td>
                <td>${s.classroomId}</td>
                <td>星期${s.weekday}</td>
                <td>${s.startSlot}-${s.startSlot + s.slotCount - 1}节</td>
                <td>${s.startWeek}-${s.endWeek}周</td>
                <td>${s.weekType}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/schedule?action=edit&id=${s.scheduleId}">编辑</a>
                    <form action="${pageContext.request.contextPath}/schedule" method="post"
                        onsubmit="return confirm('确认删除该排课？');" style="display:inline">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="id" value="${s.scheduleId}">
                        <button type="submit">删除</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
