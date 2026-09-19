<%@ page contentType="text/html;charset=utf-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>教室列表</title>
            <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        </head>

        <body>
            <h1>教室列表</h1>
            <a href="${pageContext.request.contextPath}/classroom?action=add">新增</a>
            <table border="1">
                <tr>
                    <th>教室编号</th>
                    <th>教室容量</th>
                    <th>所属楼栋</th>
                    <th>操作</th>
                </tr>
                <c:forEach var="d" items="${classrooms}">
                    <tr>
                        <td>${d.classroomId}</td>
                        <td>${d.capacity}</td>
                        <td>${d.building}</td>
                        <td>
                            <a
                                href="${pageContext.request.contextPath}/classroom?action=edit&id=${d.classroomId}">编辑</a>
                            <form action="${pageContext.request.contextPath}/classroom" method="post"
                                onsubmit="return confirm('确认删除该教室？');" style="display:inline">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="id" value="${d.classroomId}">
                                <button type="submit">删除</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>

        </body>

        </html>