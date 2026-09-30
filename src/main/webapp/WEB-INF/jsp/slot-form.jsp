<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
        <form action="${pageContext.request.contextPath}/slot" method="post">
        <input type="hidden" name="action" value="${empty slot ? 'save' : 'update'}">
        节次编号：<input type="text" name="slotId" value="${slot.slotId}" ${empty slot ? '' : 'readonly'}>
        开始日期：<input type="Time" name="startTime" value="${slot.startTime}">
        结束日期：<input type="Time" name="endTime" value="${slot.endTime}">
        <button type="submit">保存</button>
    </form>
</body>
</html>