<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>添加教师</title>
        </head>

        <body>
            <form action="${pageContext.request.contextPath}/teacher" method="post">
                <input type="hidden" name="action" value="${empty teacher ? 'save' : 'update'}">
                教师编号<input type="text" name="teacherId" value="${teacher.teacherId}" ${empty teacher ? '' : 'readonly'
                    }>
                姓名<input type="text" name="name" value="${teacher.name}">
                职称<select name="title">
                    <option value="教授" ${empty teacher || teacher.title=='教授' ? 'selected' : '' }>教授</option>
                    <option value="副教授" ${teacher.title=='副教授' ? 'selected' : '' }>副教授</option>
                    <option value="讲师" ${teacher.title=='讲师' ? 'selected' : '' }>讲师</option>
                </select>
                电话<input type="text" name="phone" value="${teacher.phone}">
                性别<select name="gender" id="">
                    <option value="男" ${empty teacher || teacher.gender=='男' ? 'selected' : '' }>男</option>
                    <option value="女" ${teacher.gender=='女' ? 'selected' : '' }>女</option>
                </select>
                所属院系<select name="departmentId">
                    <option value="">-- 请选择 --</option>
                    <c:forEach var="department" items="${departments}">
                        <option value="${department.departmentId}" ${department.departmentId==teacher.departmentId
                            ? 'selected' : '' }>${department.name}</option>
                    </c:forEach>
                </select>
                <button type="submit">保存</button>
            </form>
        </body>

        </html>