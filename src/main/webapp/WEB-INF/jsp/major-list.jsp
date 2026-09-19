<%@ page contentType="text/html;charset=utf-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>专业列表</title>
            <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        </head>

        <body>
            <h1>专业列表</h1>
            <a href="${pageContext.request.contextPath}/major?action=add">新增</a>
            <table border="1">
                <tr>
                    <th>编号</th>
                    <th>名称</th>
                    <th>所属院系</th>
                    <th>操作</th>
                </tr>
                <c:forEach var="m" items="${majors}">
                    <tr>
                        <td>${m.majorId}</td>
                        <td>${m.name}</td>
                        <td>${m.departmentId}</td>
                        <td>
                            <a
                                href="${pageContext.request.contextPath}/major?action=edit&id=${m.majorId}">编辑</a>
                            <form action="${pageContext.request.contextPath}/major" method="post"
                                onsubmit="return confirm('确认删除该专业？');" style="display:inline">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="id" value="${m.majorId}">
                                <button type="submit">删除</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </body>
        </html>