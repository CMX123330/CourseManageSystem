<%@ page contentType="text/html;charset=utf-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>院系列表</title>
            <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        </head>

        <body>
            <h1>院系列表</h1>
            <a href="${pageContext.request.contextPath}/department?action=add">新增</a>
            <table border="1">
                <tr>
                    <th>编号</th>
                    <th>名称</th>
                    <th>院长</th>
                    <th>办公电话</th>
                    <th>操作</th>
                </tr>
                <c:forEach var="d" items="${departments}">
                    <tr>
                        <td>${d.departmentId}</td>
                        <td>${d.name}</td>
                        <td>${d.dean}</td>
                        <td>${d.officePhone}</td>
                        <td>
                            <a
                                href="${pageContext.request.contextPath}/department?action=edit&id=${d.departmentId}">编辑</a>
                            <form action="${pageContext.request.contextPath}/department" method="post"
                                onsubmit="return confirm('确认删除该院系？');" style="display:inline">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="id" value="${d.departmentId}">
                                <button type="submit">删除</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>

        </body>

        </html>