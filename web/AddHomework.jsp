<%--
  Created by IntelliJ IDEA.
  User: 17301073
  Date: 2020/3/9
  Time: 20:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.homework.java.Model.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="com.homework.java.Model.Submit" %>
<%@ page import="com.homework.java.jdbc.TeacherJdbc" %>
<%@ page import="com.homework.java.jdbc.StudentJdbc" %>
<%@ page import="com.homework.java.Model.Homework" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>作业管理</title>
    <link href="./assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="./assets/css/materialdesignicons.min.css" rel="stylesheet">
    <link href="./assets/css/style.min.css" rel="stylesheet">
</head>

<body>
<script type="text/javascript" src="./assets/js/jquery.min.js"></script>
<script type="text/javascript" src="./assets/js/bootstrap.min.js"></script>
<script type="text/javascript" src="./assets/js/perfect-scrollbar.min.js"></script>
<script type="text/javascript" src="./assets/js/main.min.js"></script>
<div class="lyear-layout-web">
    <div class="lyear-layout-container">
        <aside class="lyear-layout-sidebar">
            <div id="logo" class="sidebar-header">
                <a style="height: 64px"><img src="./assets/img/logo-sidebar.png"/></a>
            </div>
            <div class="lyear-layout-sidebar-scroll">
                <nav class="sidebar-main">
                    <ul class="nav nav-drawer">
                        <li class="nav-item"><a href="HomeworkListServlet">作业列表</a></li>
                        <li class="nav-item"><a href="StudentListServlet">学生列表</a></li>
                    </ul>
                </nav>
            </div>
        </aside>

        <header class="lyear-layout-header">
            <nav class="navbar navbar-default">
                <div class="topbar">
                    <div class="topbar-left">
                        <div class="lyear-aside-toggler">
                            <span class="lyear-toggler-bar"></span>
                            <span class="lyear-toggler-bar"></span>
                            <span class="lyear-toggler-bar"></span>
                        </div>
                        <span class="navbar-page-title">新建作业</span>
                    </div>

                    <ul class="topbar-right">
                        <li class="dropdown dropdown-profile">
                            <span>工号：</span>
                            <%=session.getAttribute("tno")%>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>
        <main class="lyear-layout-content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body">
                                <form action="AddHomeworkServlet" method="post" class="row">
                                    <div class="form-group col-md-12">
                                        <label for="h_id">ID</label>
                                        <input type="text" class="form-control" id="h_id" name="h_id"  placeholder="请输入ID..." />
                                    </div>
                                    <div class="form-group col-md-12">
                                        <label for="title">作业名称</label>
                                        <input type="text" class="form-control" id="title" name="title" placeholder="请输入作业名称..."/>
                                    </div>
                                    <div class="form-group col-md-12">
                                        <label for="content">作业详情</label>
                                        <input type="text" class="form-control" id="content" name="content" placeholder="请输入作业详情...">
                                    </div>
                                    <div class="form-group col-md-12">
                                        <label for="create_time">创建时间</label>
                                        <input class="form-control" id="create_time" name="create_time"  value="<%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())%>" readonly/>
                                    </div>
                                    <div class="form-group col-md-12">
                                        <label for="deadline">截止时间</label>
                                        <input class="form-control" id="deadline" name="deadline" type="datetime-local" />
                                    </div>
                                    <div class="form-group col-md-12">
                                        <input type="submit" class="btn btn-primary" value="发布" />
                                        <a type="submit" class="btn btn-default" href="HomeworkListServlet">返 回</a>
                                    </div>
                                </form>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>
</div>


</body>
</html>
