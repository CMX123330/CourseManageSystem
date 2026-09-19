<%@ page contentType="text/html;charset=utf-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>班级列表</title>
            <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        </head>

        <body>
            <h1>班级列表</h1>
            <a href="${pageContext.request.contextPath}/class?action=add">新增</a>
            <table border="1">
                <tr>
                    <th>班级编号</th>
                    <th>班级名称</th>
                    <th>所属专业编号</th>
                    <th>年级</th>
                    <th>人数</th>
                    <th>操作</th>
                </tr>
                <c:forEach var="c" items="${classs}">
                    <tr>
                        <td>${c.classId}</td>
                        <td>${c.name}</td>
                        <td>${c.majorId}</td>
                        <td>${c.grade}</td>
                        <td>${c.studentCount}</td>
                        <td>
                            <a
                                href="${pageContext.request.contextPath}/class?action=edit&id=${c.classId}">编辑</a>
                            <form action="${pageContext.request.contextPath}/class" method="post"
                                onsubmit="return confirm('确认删除该班级？');" style="display:inline">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="id" value="${c.classId}">
                                <button type="submit">删除</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>

        </body>

        </html>