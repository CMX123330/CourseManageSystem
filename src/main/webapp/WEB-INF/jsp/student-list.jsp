<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <h1>学生列表</h1>
    <a href="${pageContext.request.contextPath}/student?action=add">添加学生</a>
    <table border="1">
        <tr>
            <th>学号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>班级</th>
            <th>联系电话</th>
            <th>操作</th>
        </tr>
            <c:forEach items="${students}" var="s">
                <tr>
                    <td>${s.studentId}</td>
                    <td>${s.name}</td>
                    <td>${s.gender}</td>
                    <td>${s.classId}</td>
                    <td>${s.phone}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/student?action=edit&id=${s.studentId}">编辑</a>
                        <form action="${pageContext.request.contextPath}/student" method="post"
                            onsubmit="return confirm('确认删除该学生？');" style="display:inline">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="id" value="${s.studentId}">
                            <button type="submit">删除</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
    </table>    
</body>
</html>