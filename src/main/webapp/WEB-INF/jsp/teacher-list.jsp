<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>教师列表</title>
</head>
<body>
    <h1>教师列表</h1>
    <a href="${pageContext.request.contextPath}/teacher?action=add">添加教师</a>
    <table border="1">
        <tr>
            <th>教师编号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>职称</th>
            <th>联系电话</th>
            <th>所属院系</th>
            <th>操作</th>
        </tr>
            <c:forEach items="${teachers}" var="t">
                <tr>
                    <td>${t.teacherId}</td>
                    <td>${t.name}</td>
                    <td>${t.gender}</td>
                    <td>${t.title}</td>
                    <td>${t.phone}</td>
                    <td>${t.departmentId}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/teacher?action=edit&id=${t.teacherId}">编辑</a>
                        <form action="${pageContext.request.contextPath}/teacher" method="post"
                            onsubmit="return confirm('确认删除该教师？');" style="display:inline">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="id" value="${t.teacherId}">
                            <button type="submit">删除</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
    </table>    
</body>
</html>