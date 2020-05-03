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
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生作业</title>
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
                            <span class="navbar-page-title">作业列表</span>
                        </div>

                        <ul class="topbar-right">
                            <li class="dropdown dropdown-profile">
                                <span>学号：</span>
                                <span><%=session.getAttribute("sno")%></span>
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
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th style="text-align: center">Index</th>
                                            <th style="text-align: center">Title</th>
                                            <th style="text-align: center">Create_Time</th>
                                            <th style="text-align: center">Deadline</th>
                                            <th style="text-align: center">Teacher</th>
                                            <th style="text-align: center">State</th>
                                            <th style="text-align: center">Option</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <%
                                            List<Homework> homeworkList = (List<Homework>) request.getAttribute("homework_list");
                                            int i = 0;
                                            for(Homework homework: homeworkList) {
                                                String State = "待提交";
                                               // boolean sign = false;
                                                TeacherJdbc teacherJdbc = new TeacherJdbc();
                                                String tname = teacherJdbc.GetTeacherName(homework.getTno());
                                                StudentJdbc studentJdbc = new StudentJdbc();
                                                String sno =(String)request.getSession().getAttribute("sno");
                                                if(studentJdbc.QuerySubmit(sno,homework.getH_id())){
                                                    State = "已提交";
                                                    //sign = true;
                                                }

                                        %>
                                        <tr style="text-align: center">
                                            <td scope="row" style="text-align: center"> <%=i+1%> </td>
                                            <td><%=homework.getTitle()%></td>
                                            <td><%=homework.getCreate_time()%></td>
                                            <td><%=homework.getDeadline()%></td>
                                            <td><%=tname%></td>
                                            <td><%=State%></td>
                                            <td>
                                                <a type="submit" class="btn btn-primary btn-xs"  href="Homework.jsp?h_id=<%=homework.getH_id() %>&title=<%=homework.getTitle() %>&content=<%=homework.getContent()%>&tname=<%=tname%>&create_time=<%=homework.getCreate_time()%>&deadline=<%=homework.getDeadline()%> ">查看</a>
                                                <a type="submit"  class="btn btn-primary btn-xs"  href="HomeworkSubmit.jsp?h_id=<%=homework.getH_id() %>&title=<%=homework.getTitle() %>&content=<%=homework.getContent()%>">提交</a>
                                            </td>
                                        </tr>
                                        <%
                                                i++;
                                            }
                                        %>
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
