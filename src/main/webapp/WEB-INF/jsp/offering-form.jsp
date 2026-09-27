<%@ page contentType="text/html;charset=utf-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>新增开课</title>
                <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
            </head>

            <body>
                <form action="${pageContext.request.contextPath}/offering" method="post">
                    <input type="hidden" name="action" id="" value="${empty offering ? 'save' : 'update'}">
                    开课编号：<input type="text" name="offeringId" id="" value="${offering.offeringId}" ${empty offering ? ''
                        : 'readonly' }>
                    学期：<select name="semesterId" id="">
                        <option value="">-- 请选择 --</option>
                        <c:forEach var="s" items="${semesters}">
                            <option value="${s.semesterId}" ${s.semesterId==offering.semesterId ? 'selected' : '' }>
                                ${s.name}</option>
                        </c:forEach>
                    </select>
                    课程：<select name="courseId" id="">
                        <option value="">-- 请选择 --</option>
                        <c:forEach var="c" items="${courses}">
                            <option value="${c.courseId}" ${c.courseId==offering.courseId ? 'selected' : '' }>${c.name}
                            </option>
                        </c:forEach>
                    </select>
                    授课教师：<select name="teacherId" id="">
                        <option value="">-- 请选择 --</option>
                        <c:forEach var="t" items="${teachers}">
                            <option value="${t.teacherId}" ${t.teacherId==offering.teacherId ? 'selected' : '' }>
                                ${t.name}</option>
                        </c:forEach>
                    </select>
                    周学时：<input type="text" name="weeklyHours" id="" value="${offering.weeklyHours}">
                    班级名单：
                    <c:forEach var="clazz" items="${clazzs}">
                        <label>
                            <input type="checkbox" name="classId" value="${clazz.classId}"
                                ${fn:contains(checkedClassIds, clazz.classId) ? 'checked' : '' }> ${clazz.name}
                        </label>
                    </c:forEach>
                    <button type="submit">保存</button>
                </form>
            </body>
            
            </html>