<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>调课审批</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <h1>调课审批</h1>
    <a href="${pageContext.request.contextPath}/">返回首页</a>

    <%-- 冲突提示区：approve 被 checkConflicts 拦时 forward 回来显示 --%>
    <c:if test="${not empty conflicts}">
        <div style="color:red">
            <c:forEach var="c" items="${conflicts}">
                <p>${c}</p>
            </c:forEach>
        </div>
    </c:if>

    <table>
        <tr>
            <th>申请编号</th><th>原排课</th><th>目标时间</th><th>目标教室</th>
            <th>申请人</th><th>原因</th><th>操作</th>
        </tr>
        <c:forEach var="sc" items="${pending}">
            <tr>
                <td>${sc.changeId}</td>
                <td>${sc.scheduleId}</td>
                <td>星期${sc.targetWeekday} 第${sc.targetStartSlot}节</td>
                <td>${sc.targetClassroomId}</td>
                <td>${sc.teacherId}</td>
                <td>${sc.reason}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/changeApprove" method="post" style="display:inline">
                        <input type="hidden" name="action" value="approve">
                        <input type="hidden" name="changeId" value="${sc.changeId}">
                        <button type="submit">通过</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/changeApprove" method="post" style="display:inline">
                        <input type="hidden" name="action" value="reject">
                        <input type="hidden" name="changeId" value="${sc.changeId}">
                        <button type="submit">驳回</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
