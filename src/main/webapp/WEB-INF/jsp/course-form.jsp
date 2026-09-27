<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>课程表单</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <form action="${pageContext.request.contextPath}/course" method="post">
        <input type="hidden" name="action" value="${empty course ? 'save' : 'update'}">
        课程编号：<input type="text" name="courseId" value="${course.courseId}" ${empty course ? '' : 'readonly'}><br>
        课程名称：<input type="text" name="name" value="${course.name}"><br>
        学时：<input type="text" name="hours" value="${course.hours}"><br>
        考试类型：
        <select name="examType">
            <option value="考试" ${empty course || course.examType == '考试' ? 'selected' : ''}>考试</option>
            <option value="考查" ${course.examType == '考查' ? 'selected' : ''}>考查</option>
        </select><br>
        课程性质：
        <select name="nature">
            <option value="必修" ${empty course || course.nature == '必修' ? 'selected' : ''}>必修</option>
            <option value="选修" ${course.nature == '选修' ? 'selected' : ''}>选修</option>
        </select><br>
        学分：<input type="text" name="credit" value="${course.credit}"><br>
        所属院系：
        <select name="departmentId">
            <option value="">-- 请选择 --</option>
            <c:forEach var="dept" items="${departments}">
                <option value="${dept.departmentId}" ${dept.departmentId == course.departmentId ? 'selected' : ''}>${dept.name}</option>
            </c:forEach>
        </select><br>
        <button type="submit">保存</button>
        <a href="${pageContext.request.contextPath}/course">返回列表</a>
    </form>
</body>
</html>
