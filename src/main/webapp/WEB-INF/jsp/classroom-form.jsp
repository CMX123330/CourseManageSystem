<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <form action="${pageContext.request.contextPath}/classroom" method="post">
    <input type="hidden" name="action" value="${empty classroom ? 'save' : 'update'}">
    教室编号：<input type="text" name="classroomId" value="${classroom.classroomId}" ${empty classroom ? '' : 'readonly'}>
    教室容量：<input type="number" name="capacity" value="${classroom.capacity}">
    教室所在楼栋：<input type="text" name="building" id="" value="${classroom.building}">
    <button type="submit">保存</button>
    </form>
</body>
</html>