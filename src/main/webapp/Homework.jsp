<%--
  Created by IntelliJ IDEA.
  User: 17301073
  Date: 2020/3/9
  Time: 20:12
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="jdbc.StudentJdbc" %>
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
                        <li class="nav-item"><a href="StudentHomeworkListServlet">作业列表</a></li>
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
                        <span class="navbar-page-title">查看作业</span>
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
                                <%  String h_id = (String) request.getParameter("h_id");
                                    String sno = (String) request.getSession().getAttribute("sno");

                                    StudentJdbc student_jdbc = new StudentJdbc();
                                    main.java.Model.Submit sh = student_jdbc.QuerySubmitContent(sno,h_id);%>
                                <form action="SubmitHomeworkServlet" method="post" class="row">
                                    <div class="form-group col-md-3">
                                        <label for="h_id">ID</label>
                                        <input type="text" class="form-control " id="h_id" name="h_id" value="<%=request.getParameter("h_id")%>" readonly />
                                    </div>
                                    <div class="form-group col-md-5">
                                        <label for="title">作业名称</label>
                                        <input type="text" class="form-control" id="title" name="title" value="<%=request.getParameter("title")%>" readonly />
                                    </div>
                                    <div class="form-group col-md-3">
                                        <label for="tname">老师</label>
                                        <input type="text" class="form-control" id="tname" name="tname"  value="<%=request.getParameter("tname")%>"  readonly/>
                                    </div>
                                    <div class="form-group col-md-10">
                                        <label for="content">作业详情  </label>
                                        <input type="text" class="form-control" id="content" name="content"  value="<%=request.getParameter("content")%>"  readonly/>
                                    </div>
                                    <div class="form-group col-md-5">
                                        <label for="create_time">发布时间</label>
                                        <input type="text" class="form-control" id="create_time" name="create_time"  value="<%=request.getParameter("create_time")%>"  readonly/>
                                    </div>
                                    <div class="form-group col-md-5">
                                        <label for="deadline">截止时间</label>
                                        <input type="text" class="form-control" id="deadline" name="deadline"  value="<%=request.getParameter("deadline")%>"  readonly/>
                                    </div>
                                    <div class="form-group col-md-12">
                                        <label for="commit_time">提交时间</label>
                                        <input class="form-control" id="commit_time" name="commit_time"  value="<%=sh.getCommit_time()%>" readonly/>
                                    </div>
                                    <div class="form-group col-md-10">
                                        <label for="commit_content">提交内容</label>
                                        <input class="form-control" id="commit_content" name="commit_content"  value="<%=sh.getCommit_content()%>" readonly/>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <input type="submit" class="btn btn-default" value="返 回" />
                                        <a type="submit" class="btn btn-primary" href="HomeworkSubmit.jsp?h_id=<%=request.getParameter("h_id")%>">去提交</a>
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
