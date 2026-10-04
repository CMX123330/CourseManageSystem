<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Document</title>
        </head>

        <body>
            <h2>可选课程</h2>
            <table>
                <tr>
                    <th>课程</th>
                    <th>教师</th>
                    <th>周学时</th>
                    <th>操作</th>
                </tr>
                <c:forEach var="o" items="${available}">
                    <tr>
                        <td>${o.courseName}</td>
                        <td>${o.teacherName}</td>
                        <td>${o.weeklyHours}</td>
                        <td>
                            <form action="${pagecontext.request.pagecontextPath}/courseSelect" method="post" style="display:inline">
                                <input type="hidden" name="action" value="select">
                                <input type="hidden" name="offeringId" value="${o.offeringId}">
                                <button type="submit">选课</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>

            <h2>已选课程</h2>
            <table>
                <tr>
                    <th>课程</th>
                    <th>教师</th>
                    <th>周学时</th>
                    <th>操作</th>
                </tr>
                <c:forEach var="o" items="${myList}">
                    <tr>
                        <td>${o.courseName}</td>
                        <td>${o.teacherName}</td>
                        <td>${o.weeklyHours}</td>
                        <td>
                            <form action="${pagecontext.request.pagecontextPath}/courseSelect" method="post" style="display:inline">
                                <input type="hidden" name="action" value="drop">
                                <input type="hidden" name="offeringId" value="${o.offeringId}">
                                <button type="submit">退课</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>

        </body>

        </html>