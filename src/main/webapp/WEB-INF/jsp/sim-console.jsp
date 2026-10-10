<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>模拟控制台</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <h1>模拟控制台</h1>
    <p>当前：第${state.week}周</p>
    <form action="${pageContext.request.contextPath}/simConsole" method="post">
        <input type="hidden" name="cmd" id="" value="start">
        <button>开始学期</button>
    </form>
    <form action="${pageContext.request.contextPath}/simConsole" method="post">
        <input type="hidden" name="cmd" id="" value="advance">
        <button>推进一周</button>
    </form>
    <p>平均精力${stats.avgEnergy}</p>
    <p>平均出勤率${stats.avgAttendance}</p>
    <p>翘课组${stats.slackers}</p>
    <c:forEach var="e" items="${state.events}">
    <p>${e}</p>
    </c:forEach>
</body>
</html>