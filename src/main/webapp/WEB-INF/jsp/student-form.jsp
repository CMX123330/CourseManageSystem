<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>添加学生</title>
</head>
<body>
        <form action="${pageContext.request.contextPath}/student" method="post">
        <input type="hidden" name="action" value="${empty student ? 'save' : 'update'}">
        学号<input type="text" name="studentId" value="${student.studentId}" ${empty student ? '' : 'readonly'}>
        姓名<input type="text" name="name" value="${student.name}">
        性别<select name="gender" id="">
            <option value="男" ${empty student || student.gender == '男' ? 'selected' : ''}>男</option>
            <option value="女" ${student.gender == '女' ? 'selected' : ''}>女</option>
        </select>
        班级<select name="classId">
            <option value="">-- 请选择 --</option>
            <c:forEach var="clazz" items="${clazzs}">
                <option value="${clazz.classId}" ${clazz.classId == student.classId ? 'selected' : ''}>${clazz.name}</option>
            </c:forEach>
        </select>
        电话<input type="text" name="phone" value="${student.phone}">
        <button type="submit">保存</button>
    </form>
</body>
</html>