<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>课程列表</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <h1>课程列表</h1>
    <a href="${pageContext.request.contextPath}/course?action=add">新增</a>
    <table border="1">
        <tr>
            <th>编号</th>
            <th>名称</th>
            <th>学时</th>
            <th>学分</th>
            <th>考试类型</th>
            <th>课程性质</th>
            <th>所属院系</th>
            <th>操作</th>
        </tr>
        <c:forEach var="c" items="${courses}">
            <tr>
                <td>${c.courseId}</td>
                <td>${c.name}</td>
                <td>${c.hours}</td>
                <td>${c.credit}</td>
                <td>${c.examType}</td>
                <td>${c.nature}</td>
                <td>${c.departmentId}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/course?action=edit&id=${c.courseId}">编辑</a>
                    <form action="${pageContext.request.contextPath}/course" method="post"
                        onsubmit="return confirm('确认删除该课程？');" style="display:inline">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="id" value="${c.courseId}">
                        <button type="submit">删除</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
