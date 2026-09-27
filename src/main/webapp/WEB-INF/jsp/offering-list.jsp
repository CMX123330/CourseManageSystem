<%@ page contentType="text/html;charset=utf-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>开课列表</title>
            <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        </head>

        <body>
            <h1>开课列表</h1>
            <a href="${pageContext.request.contextPath}/offering?action=add">添加课程</a>
            <c:if test="${not empty error}">
                <p style="color:red">${error}</p>
            </c:if>
            <form action="${pageContext.request.contextPath}/offering" method="get">
                <select name="semesterId">
                    <option value="">全部学期</option>
                    <c:forEach var="s" items="${semesters}">
                        <option value="${s.semesterId}" ${param.semesterId==s.semesterId ? 'selected' : '' }>${s.name}
                        </option>
                    </c:forEach>
                </select>
                <button type="submit">查询</button>
            </form>

            <table>

                <tr>
                    <th>开课编号</th>
                    <th>学期</th>
                    <th>课程</th>
                    <th>授课教师</th>
                    <th>周学时</th>
                    <th>班级名单</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${offerings}" var="o">
                    <tr>
                        <td>${o.offeringId}</td>
                        <td>${o.semesterName}</td>
                        <td>${o.courseName}</td>
                        <td>${o.teacherName}</td>
                        <td>${o.weeklyHours}</td>
                        <td>${o.classNames}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/offering?action=edit&id=${o.offeringId}">编辑</a>
                            <form action="${pageContext.request.contextPath}/offering" method="post"
                                onsubmit="return confirm('确认删除该课程？');" style="display:inline">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="id" value="${o.offeringId}">
                                <button type="submit">删除</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </body>

        </html>