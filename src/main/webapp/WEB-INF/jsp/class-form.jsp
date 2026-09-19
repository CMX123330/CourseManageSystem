<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>班级表单</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/class" method="post">
    <input type="hidden" name="action" value="${empty clazz ? 'save' : 'update'}">
    班级编号：<input type="text" name="classId" value="${clazz.classId}" ${empty clazz ? '' : 'readonly'}>
    班级名称：<input type="text" name="name" value="${clazz.name}">
    所属专业编号：<input type="text" name="majorId" value="${clazz.majorId}">
    年级：<input type="number" name="grade" value="${clazz.grade}">
    班级人数：<input type="number" name="studentCount" value="${clazz.studentCount}">
    <button type="submit">保存</button>
    </form>
</body>
</html>
