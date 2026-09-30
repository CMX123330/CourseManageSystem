<%@ page contentType="text/html;charset=utf-8" language="java" %>
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
            <form action="${pageContext.request.contextPath}/schedule" method="post">
                <input type="hidden" name="action" value="${empty schedule ? 'save' : 'update'}">
                <c:if test="${not empty conflicts}">
                    <div style="color:red">
                        <c:forEach var="c" items="${conflicts}">
                            <p>${c}</p>
                        </c:forEach>
                    </div>
                </c:if>

                开课：<select name="offeringId">
                    <option value="">-- 请选择 --</option>
                    <c:forEach items="${offerings}" var="o">
                        <option value="${o.offeringId}" ${o.offeringId==schedule.offeringId ? 'selected' : ''}>${o.courseName} - ${o.teacherName}</option>
                    </c:forEach>
                </select>

                <select name="weekday" id="">
                    <option value="1" ${schedule.weekday == 1 ? 'selected' : ''}>星期一</option>
                    <option value="2" ${schedule.weekday == 2 ? 'selected' : ''}>星期二</option>
                    <option value="3" ${schedule.weekday == 3 ? 'selected' : ''}>星期三</option>
                    <option value="4" ${schedule.weekday == 4 ? 'selected' : ''}>星期四</option>
                    <option value="5" ${schedule.weekday == 5 ? 'selected' : ''}>星期五</option>
                    <option value="6" ${schedule.weekday == 6 ? 'selected' : ''}>星期六</option>
                    <option value="7" ${schedule.weekday == 7 ? 'selected' : ''}>星期日</option>
                </select>
                节次：<select name="startSlot">
                    <option value="1" ${schedule.startSlot == 1 ? 'selected' : ''}>第1-2节（8:00-9:50）</option>
                    <option value="3" ${schedule.startSlot == 3 ? 'selected' : ''}>第3-4节（10:10-12:00）</option>
                    <option value="5" ${schedule.startSlot == 5 ? 'selected' : ''}>第5-6节（14:30-16:20）</option>
                    <option value="7" ${schedule.startSlot == 7 ? 'selected' : ''}>第7-8节（16:40-18:30）</option>
                    <option value="9" ${schedule.startSlot == 9 ? 'selected' : ''}>第9-10节（19:30-21:20）</option>
                </select>
                <input type="hidden" name="slotCount" value="2">
                周范围：
                起始周：<select name="startWeek">
                    <c:forEach var="i" begin="1" end="20">
                        <option value="${i}" ${schedule.startWeek == i ? 'selected' : ''}>第${i}周</option>
                    </c:forEach>
                </select>
                结束周：<select name="endWeek">
                    <c:forEach var="i" begin="1" end="20">
                        <option value="${i}" ${schedule.endWeek == i ? 'selected' : ''}>第${i}周</option>
                    </c:forEach>
                </select>
                单双周：<select name="weekType">
                    <option value="全周" ${schedule.weekType == '全周' ? 'selected' : ''}>全周</option>
                    <option value="单周" ${schedule.weekType == '单周' ? 'selected' : ''}>单周</option>
                    <option value="双周" ${schedule.weekType == '双周' ? 'selected' : ''}>双周</option>
                </select>

                教室编号：<select name="classroomId" id="">
                    <c:forEach items="${classrooms}" var="classroom">
                        <option value="${classroom.classroomId}" ${classroom.classroomId == schedule.classroomId ? 'selected' : ''}>${classroom.classroomId}</option>
                    </c:forEach>
                </select>
                排课表：<input type="text" name="scheduleId" id="" value="${schedule.scheduleId}" ${empty schedule ? '' : 'readonly'}>
                <button type="submit">保存</button>
            </form>
        </body>

        </html>