<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>课程管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <h1 class="page-title">课程管理系统 <span class="sub">Course Management System</span></h1>

        <div class="card">
            <h2>基础数据</h2>
            <div class="nav-grid">
                <a class="nav-card" href="${pageContext.request.contextPath}/department">院系管理</a>
                <a class="nav-card" href="${pageContext.request.contextPath}/semester">学期管理</a>
                <a class="nav-card" href="${pageContext.request.contextPath}/slot">节次管理</a>
                <a class="nav-card" href="${pageContext.request.contextPath}/classroom">教室管理</a>
                <a class="nav-card" href="${pageContext.request.contextPath}/class">班级管理</a>
                <a class="nav-card" href="${pageContext.request.contextPath}/major">专业管理</a>
                <a class="nav-card" href="${pageContext.request.contextPath}/course">课程管理</a>
                <a class="nav-card" href="${pageContext.request.contextPath}/student">学生管理</a>
                <a class="nav-card" href="${pageContext.request.contextPath}/teacher">教师管理</a>
            </div>
        </div>

        <div class="card">
            <h2>教学管理</h2>
            <div class="nav-grid">
                <a class="nav-card" href="${pageContext.request.contextPath}/offering">开课管理</a>
                <a class="nav-card" href="${pageContext.request.contextPath}/schedule">排课管理</a>
            </div>
        </div>

        <div class="card">
            <h2>课表查询</h2>
            <div class="nav-grid">
                <a class="nav-card" href="${pageContext.request.contextPath}/timetable">班级课表</a>
                <a class="nav-card" href="${pageContext.request.contextPath}/teachertable">教师课表</a>
                <a class="nav-card" href="${pageContext.request.contextPath}/classroomtable">教室课表</a>
            </div>
        </div>
    </div>
</body>
</html>
