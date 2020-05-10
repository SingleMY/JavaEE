<%--
  Created by IntelliJ IDEA.
  User: 17301073
  Date: 2020/3/9
  Time: 20:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生作业</title>
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
                        <li class="nav-item"><a href="homeworklist_student">作业列表</a></li>
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
                        <span class="navbar-page-title">提交作业</span>
                    </div>

                    <ul class="topbar-right">
                        <li class="dropdown dropdown-profile">
                            <span>学号：</span>
                            <%=session.getAttribute("sno")%>
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
                                <form action="homeworksubmit_student" method="post" class="row">
                                    <div class="form-group col-md-12">
                                        <label for="h_id">ID</label>
                                        <input type="text" class="form-control" id="h_id" name="h_id" value="<%=request.getParameter("h_id")%>" readonly />
                                    </div>
                                    <div class="form-group col-md-12">
                                        <label for="title">作业名称</label>
                                        <input type="text" class="form-control" id="title" name="title" value="<%=request.getParameter("title")%>" readonly />
                                    </div>
                                    <div class="form-group col-md-12">
                                        <label for="content">作业详情</label>
                                        <input type="text" class="form-control" id="content" name="content"  value="<%=request.getParameter("content")%>"  readonly/>
                                    </div>
                                    <div class="form-group col-md-12">
                                        <label for="commit_time">提交时间</label>
                                        <input class="form-control" id="commit_time" name="commit_time"  value="<%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())%>" readonly/>
                                    </div>
                                    <div class="form-group col-md-12">
                                        <label for="commit_content">提交内容</label>
                                        <textarea class="form-control" id="commit_content" name="commit_content" rows="5"   placeholder="请提交作业..."></textarea>
                                    </div>
                                    <div class="form-group col-md-12">
                                        <input type="submit" class="btn btn-primary" value="提交" />
                                        <a type="submit" class="btn btn-default" href="homeworklist_student">返 回</a>
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
