<%--
  Created by IntelliJ IDEA.
  User: 17301073
  Date: 2020/4/9
  Time: 20:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>作业管理</title>
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/css/materialdesignicons.min.css" rel="stylesheet">
    <link href="assets/css/style.min.css" rel="stylesheet">
</head>

<body>
<script type="text/javascript" src="assets/js/jquery.min.js"></script>
<script type="text/javascript" src="assets/js/bootstrap.min.js"></script>
<script type="text/javascript" src="assets/js/perfect-scrollbar.min.js"></script>
<script type="text/javascript" src="assets/js/main.min.js"></script>
<div class="lyear-layout-web">
    <div class="lyear-layout-container">
        <aside class="lyear-layout-sidebar">
            <div id="logo" class="sidebar-header">
                <a style="height: 64px"><img src="assets/img/logo-sidebar.png"/></a>
            </div>
            <div class="lyear-layout-sidebar-scroll">
                <nav class="sidebar-main">
                    <ul class="nav nav-drawer">
                        <li class="nav-item"><a href="homeworklist_teacher">作业列表</a></li>
                        <li class="nav-item"><a href="studentlist">学生列表</a></li>
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
                        <span class="navbar-page-title">添加学生</span>
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
                                <form action="addstudent" method="post" class="row">
                                    <div class="form-group col-md-12">
                                        <label for="sno">学号</label>
                                        <input type="text" class="form-control" id="sno" name="sno"  placeholder="请输入学号..." />
                                    </div>
                                    <div class="form-group col-md-12">
                                        <label for="sname">学生姓名</label>
                                        <input type="text" class="form-control" id="sname" name="sname" placeholder="请输入学生姓名..."/>
                                    </div>
                                    <div class="form-group col-md-12">
                                        <label for="password">密码</label>
                                        <input type="text" class="form-control" id="password" name="password" placeholder="请输入密码...">
                                    </div>
                                    <div class="form-group col-md-12">
                                        <input type="submit" class="btn btn-primary" value="完成" />
                                        <a type="submit" class="btn btn-default" href="studentlist">返 回</a>
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
