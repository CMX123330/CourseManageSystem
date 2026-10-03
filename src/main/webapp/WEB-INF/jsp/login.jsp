<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>登录界面</title>
        </head>

        <body>
            <form action="${pageContext.request.contextPath}/login" method="post">
                账号<input type="text" name="userId" id="">
                密码<input type="password" name="password" id="">
                <c:if test="${not empty error}">
                    <p style="color: red;">${error}</p>
                </c:if>
                <button type="submit">登录</button>
            </form>
        </body>

        </html>