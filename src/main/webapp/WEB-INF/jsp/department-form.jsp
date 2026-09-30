<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <form action="${pageContext.request.contextPath}/department" method="post">
    <input type="text"  name="action" id="" value="${empty department ? 'save' : 'update'}">
    院系编号：<input type="text" name="departmentId" id="" value="${department.departmentId}">
    院系名称：<input type="text" name="name" id="" value="${department.name}">
    院系院长：<input type="text" name="dean" id="" value="${department.dean}">
    院系电话：<input type="text" name="officePhone" id="" value="${department.officePhone}">
    <button type="submit">保存</button>
    </form>
</body>
</html>