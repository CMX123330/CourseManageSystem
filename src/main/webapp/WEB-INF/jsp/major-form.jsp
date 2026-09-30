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
        <form action="${pageContext.request.contextPath}/major" method="post" >
            <input type="hidden" name="action" id="" value="${empty major ? 'save' : 'update'}">
            专业编号：<input type="text" name="majorId" id="" value="${major.majorId}" ${empty major ? '' : 'readonly'}>
            专业名称：<input type="text" name="name" id="" value="${major.name}">
            所属院系：<select name="departmentId">
                <option value="">-- 请选择 --</option>
                <c:forEach var="dept" items="${departments}">
                    <option value="${dept.departmentId}" ${dept.departmentId == major.departmentId ? 'selected' : ''}>${dept.name}</option>
                </c:forEach>
            </select>

            <button type="submit">保存</button>
        </form>
    </body>

    </html>