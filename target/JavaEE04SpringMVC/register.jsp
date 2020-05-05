<%--
  Created by IntelliJ IDEA.
  User: 17301073
  Date: 2020/4/9
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <link rel="icon" type="image/ico">
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/css/materialdesignicons.min.css" rel="stylesheet">
    <link href="assets/css/style.min.css" rel="stylesheet">
    <%--<link rel="./assets/stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">--%>

    <style>
        body{
          background: #2ba3f6;
        }
        .lyear-wrapper {
            position: relative;
        }
        .lyear-login {
            display: flex !important;
            min-height: 100vh;
            align-items: center !important;
            justify-content: center !important;
        }
        .login-center {
            background: #fff;
            min-width: 38.25rem;
            padding: 2.14286em 3.57143em;
            border-radius: 5px;
            margin: 2.85714em 0;
        }
        .login-center .has-feedback.feedback-left .form-control {
            padding-left: 38px;
            padding-right: 12px;
        }
        .login-center .has-feedback.feedback-left .form-control-feedback {
            left: 0;
            right: auto;
            width: 38px;
            height: 38px;
            line-height: 38px;
            z-index: 4;
            color: #dcdcdc;
        }
        .login-center .has-feedback.feedback-left.row .form-control-feedback {
            left: 15px;
        }
    </style>
</head>

<body>
<div class="row lyear-wrapper">
    <div class="lyear-login">
        <div class="login-center">
            <form action="register" method="post">
                <div align="center">
                    <label class="center-block" >用户注册</label>
                </div>
                <div class="form-group has-feedback feedback-left">
                    <input type="text" placeholder="请输入您的ID" class="form-control" name="register_no" id="register_no" />
                </div>
                <div class="form-group has-feedback feedback-left">
                    <input type="text" placeholder="请输入您的用户名" class="form-control" name="register_name" id="register_name" />
                </div>
                <div class="form-group has-feedback feedback-left">
                    <input type="password" placeholder="请设定您的密码" class="form-control" name="register_password" id="register_password" />
                </div>
                <div class="form-group has-feedback feedback-left">
                    <input type="text" placeholder="请输入您的身份（老师/学生）" class="form-control" id="register_identity" name="register_identity" />
                </div>
                <div class="form-group">
                    <input class="btn btn-block btn-primary" type="submit" value="注册" />
                </div>
            </form>
            <hr>
        </div>
    </div>
</div>

<script type="text/javascript" src="assets/js/jquery.min.js"></script>
<script type="text/javascript" src="assets/js/bootstrap.min.js"></script>

</body>
</html>
