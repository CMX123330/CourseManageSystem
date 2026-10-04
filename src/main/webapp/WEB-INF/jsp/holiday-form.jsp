<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>新增假期</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <h1>新增假期</h1>
    <form action="${pageContext.request.contextPath}/holiday" method="post">
        <input type="hidden" name="action" value="save">
        假期编号：<input type="text" name="holidayId"><br>
        放假日期：<input type="text" name="holidayDate" placeholder="格式：2025-10-01"><br>
        假期名称：<input type="text" name="name"><br>
        <button type="submit">保存</button>
        <a href="${pageContext.request.contextPath}/holiday">返回列表</a>
    </form>
</body>
</html>
