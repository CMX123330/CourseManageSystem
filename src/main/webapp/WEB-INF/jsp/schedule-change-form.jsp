<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>申请调课</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <h1>申请调课</h1>
    <form action="${pageContext.request.contextPath}/scheduleChange" method="post">
        <input type="hidden" name="action" value="save">
        <input type="hidden" name="scheduleId" value="${sc.scheduleId}">
        <input type="hidden" name="offeringId" value="${sc.offeringId}">
        申请编号：<input type="text" name="changeId"><br>
        将 ${sc.scheduleId}（星期${sc.weekday} 第${sc.startSlot}节 教室${sc.classroomId}）调至：<br>
        目标星期：<select name="targetWeekday">
            <option value="1">星期一</option>
            <option value="2">星期二</option>
            <option value="3">星期三</option>
            <option value="4">星期四</option>
            <option value="5">星期五</option>
            <option value="6">星期六</option>
            <option value="7">星期日</option>
        </select><br>
        目标时段：<select name="targetStartSlot">
            <option value="1">第1-2节（8:00-9:50）</option>
            <option value="3">第3-4节（10:10-12:00）</option>
            <option value="5">第5-6节（14:30-16:20）</option>
            <option value="7">第7-8节（16:40-18:30）</option>
            <option value="9">第9-10节（19:30-21:20）</option>
        </select>
        <input type="hidden" name="targetSlotCount" value="2"><br>
        目标教室：<select name="targetClassroomId">
            <c:forEach var="cl" items="${classrooms}">
                <option value="${cl.classroomId}">${cl.classroomId}</option>
            </c:forEach>
        </select><br>
        调课原因：<input type="text" name="reason"><br>
        <button type="submit">提交申请</button>
        <a href="${pageContext.request.contextPath}/scheduleChange">返回</a>
    </form>
</body>
</html>
