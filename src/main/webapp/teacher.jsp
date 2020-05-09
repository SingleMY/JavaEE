<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 17301073
  Date: 2020/3/9
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
                        <span class="navbar-page-title">作业列表</span>
                    </div>

                    <ul class="topbar-right">
                        <li class="dropdown dropdown-profile">
                            <span>工号：</span>
                            <span><%=session.getAttribute("tno")%></span>
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
                            <div class="card-header" >
                                <div align="right">
                                    <a type="submit" class="btn bg-primary"  href="addhomework.jsp" >新建作业</a>
                                </div>

                            </div>
                            <div class="card-body">
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th style="text-align: center">Index</th>
                                        <th style="text-align: center">Title</th>
                                        <th style="text-align: center">Create_Time</th>
                                        <th style="text-align: center">Deadline</th>
                                        <th style="text-align: center">Count</th>
                                        <th style="text-align: center">Option</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${shomework_list}" var="list" varStatus="status">
                                    <tr style="text-align: center">
                                        <td scope="row" style="text-align: center"> ${status.count} </td>
                                        <td>${list.getTitle()}</td>
                                        <td>${list.create_time}</td>
                                        <td>${list.getDeadline()}</td>
                                        <td>${list.count}</td>
                                        <td>
                                            <a type="submit" class="btn btn-primary btn-xs"  href="checkhomework?h_id=${list.h_id} ">检查</a>

                                            <a type="submit"  class="btn btn-primary btn-xs"  href="homeworkdelete?h_id=${list.h_id}">删除</a>
                                        </td>
                                    </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
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
