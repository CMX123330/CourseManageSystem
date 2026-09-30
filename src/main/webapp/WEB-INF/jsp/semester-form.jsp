<%@ page contentType="text/html;charset=utf-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${empty semester ? "新增学期" : "编辑学期"}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <form action="${pageContext.request.contextPath}/semester" method="post">
        <input type="hidden" name="action" value="${empty semester ? 'save' : 'update'}">
        学期编号：<input type="text" name="semesterId" value="${semester.semesterId}" ${empty semester ? '' : 'readonly'}>
        学期名称：<input type="text" name="name" value="${semester.name}">
        开始日期：<input type="date" name="startDate" value="${semester.startDate}">
        结束日期：<input type="date" name="endDate" value="${semester.endDate}">
        总周数：<input type="number" name="totalWeeks" value="${semester.totalWeeks}">
        <button type="submit">保存</button>
    </form>
</body>
</html>