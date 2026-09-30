<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html lang="zh-CN">

        <head>
            <meta charset="UTF-8">
            <title>教室课表</title>
            <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        </head>

        <body>
            <h1>教室课表</h1>

            <%-- 顶部筛选表单：GET 提交，参数带到 URL --%>
                <form action="${pageContext.request.contextPath}/timetable" method="get">
                    教室：<select name="classroomId">
                        <c:forEach var="cl" items="${clazzs}">
                            <option value="${cl.classroomId}" ${param.classroomId==classroomId ? 'selected' : '' }>
                                ${cl.name}</option>
                        </c:forEach>
                    </select>
                    学期：<select name="semesterId">
                        <c:forEach var="sm" items="${semesters}">
                            <option value="${sm.semesterId}" ${param.semesterId==sm.semesterId ? 'selected' : '' }>
                                ${sm.name}</option>
                        </c:forEach>
                    </select>
                    <button type="submit">查询课表</button>
                </form>

                <c:if test="${empty schedules}">
                    <p>该学期暂无排课，请先到排课管理添加。</p>
                </c:if>

                <%-- 二维课表：外层时段、内层星期、最内层匹配记录 --%>
                    <table>
                        <tr>
                            <th>节次</th>
                            <th>星期一</th>
                            <th>星期二</th>
                            <th>星期三</th>
                            <th>星期四</th>
                            <th>星期五</th>
                            <th>星期六</th>
                            <th>星期日</th>
                        </tr>
                        <c:forEach var="slot" items="${slots}"> <%-- 第 1 层：5 个时段 --%>
                                <tr>
                                    <td>第${slot}-${slot + 1}节</td>
                                    <c:forEach var="day" begin="1" end="7"> <%-- 第 2 层：7 个星期 --%>
                                            <td>
                                                <c:forEach var="s" items="${schedules}"> <%-- 第 3 层：所有排课 --%>
                                                        <c:if test="${s.weekday == day && s.startSlot == slot}">
                                                            ${s.courseName}<br>
                                                            ${s.teacherName} ${s.classroomId}
                                                            <c:if test="${s.weekType != '全周'}">（${s.weekType}）</c:if>
                                                        </c:if>
                                                </c:forEach>
                                            </td>
                                    </c:forEach>
                                </tr>
                        </c:forEach>
                    </table>
        </body>

        </html>