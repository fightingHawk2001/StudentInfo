<%@ page import="student.domain.Stu" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>修改学生信息</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="../fightingHawk/fightingHawk.png" rel="shortcut icon" type="image/png">
        <script src="js/jquery-3.5.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <%--<script>
            window.history.forward();
        </script>--%>
    </head>
    <body>
        <div class="container" style="width: 400px">
            <h3 class="text-center">修改学生信息</h3>
            <form action="${pageContext.request.contextPath}/updateStuServlet" method="post" id="form">
                <%--隐藏域提交id值--%>
                <input type="hidden" name="id" value="${stu.id}">
                <div class="form-group"  id="name-div">
                    <label for="name">姓名 : </label>
                    <input type="text" name="name" class="form-control" onblur="checkName()"
                           id="name" placeholder="请输入姓名" value="${stu.name}">
                    <span id="name-state" aria-hidden="true" ></span>
                    <label id="label-name" style="color: red;font-size: 12px;float: right"></label>
                </div>
                <div class="form-group">
                    <label>性别 : </label>
                    <c:if test="${stu.gender == '男'}">
                        <input type="radio" name="gender" value="男" checked="checked">男&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="radio" name="gender" value="女">女
                    </c:if>
                    <c:if test="${stu.gender == '女'}">
                        <input type="radio" name="gender" value="男">男&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="radio" name="gender" value="女" checked="checked">女
                    </c:if>
                </div>
                <div class="form-group"  id="age-div">
                    <label for="age">年龄 : </label>
                    <input type="number" name="age" class="form-control" onblur="checkAge()"
                           id="age" placeholder="请输入年龄" value="${stu.age}">
                    <span id="age-state" aria-hidden="true" ></span>
                    <label id="age-label" style="color: red;font-size: 12px;float: right"></label>
                </div>
                <div class="form-group" id="address-div">
                    <label for="address">籍贯 : </label>
                    <input type="text" name="address" class="form-control" onblur="checkAddress()"
                           id="address" placeholder="请输入籍贯" value="${stu.address}">
                    <span id="address-state" aria-hidden="true" ></span>
                </div>
                <div class="form-group" id="qq-div">
                    <label for="qq">QQ : </label>
                    <input type="text" name="qq" class="form-control" onblur="checkQq()"
                           id="qq" placeholder="请输入QQ号码" value="${stu.qq}">
                    <span id="qq-state" aria-hidden="true" ></span>
                    <label id="qq-label" style="color: red;font-size: 12px;float: right"></label>
                </div>
                <div class="form-group" id="email-div">
                    <label for="email">邮箱 : </label>
                    <input type="email" name="email" class="form-control" onblur="checkEmail()"
                           id="email" placeholder="请输入邮箱" value="${stu.email}">
                    <span id="email-state" aria-hidden="true" ></span>
                    <label id="email-label" style="color: red;font-size: 12px;float: right"></label>
                </div>
                <div style="height: 10px"></div>
                <div class="form-group text-center">
                    <input type="submit" class="btn btn-primary" value="提交">
                    <input type="reset" class="btn btn-default" value="重置">
                    <a href="${pageContext.request.contextPath}/findStuByPageServlet" class="btn btn-default">返回</a>
                </div>
            </form>
        </div>

        <script>
            // 表单检验
            document.getElementById("form").onsubmit = function () {
                return checkName() && checkAge() && checkAddress() && checkQq() && checkEmail();
            }
            function checkName() {
                let name = document.getElementById("name");
                let label_name = document.getElementById("label-name");
                let name_state = document.getElementById("name-state");
                let name_div = document.getElementById("name-div");
                if (name.value == null || name.value.length == 0) {
                    label_name.innerHTML = "姓名不能为空！";
                    name_state.className = "glyphicon glyphicon-remove form-control-feedback"
                    name_div.className = "form-group has-error has-feedback"
                    return false;
                } else {
                    label_name.innerHTML = "";
                    name_state.className = "glyphicon glyphicon-ok form-control-feedback";
                    name_div.className = "form-group has-success has-feedback"
                    return true;
                }
            }

            function checkAge() {
                let regExp = /^(([0-9]|[0-9][0-9]|1[0-1][0-9])(\\.[0-9]+)?|120)$/; //判断年龄在1 ~ 120岁之间
                let age = document.getElementById("age");
                let age_label = document.getElementById("age-label");
                let age_state = document.getElementById("age-state");
                let age_div = document.getElementById("age-div");
                if (age.value == null || age.value.length == 0 || !regExp.exec(age.value)) {
                    age_label.innerHTML = "年龄非法！";
                    age_state.className = "glyphicon glyphicon-remove form-control-feedback"
                    age_div.className = "form-group has-error has-feedback"
                    return false;
                } else {
                    age_label.innerHTML = "";
                    age_state.className = "glyphicon glyphicon-ok form-control-feedback";
                    age_div.className = "form-group has-success has-feedback"
                    return true;
                }
            }

            function checkAddress() {
                document.getElementById('address-div').className = 'form-group has-success has-feedback';
                document.getElementById('address-state').className = "glyphicon glyphicon-ok form-control-feedback";
            }

            function checkQq() {
                let qq = document.getElementById("qq");
                let qq_label = document.getElementById("qq-label");
                let qq_state = document.getElementById("qq-state");
                let qq_div = document.getElementById("qq-div");
                if (isNaN(qq.value)) {
                    qq_label.innerHTML = "QQ号码只能由数字组成";
                    qq_state.className = "glyphicon glyphicon-remove form-control-feedback"
                    qq_div.className = "form-group has-error has-feedback"
                    return false;
                } else {
                    qq_label.innerHTML = "";
                    qq_state.className = "glyphicon glyphicon-ok form-control-feedback";
                    qq_div.className = "form-group has-success has-feedback"
                    return true;
                }
            }

            function checkEmail() {
                let regExp = /^[a-zA-Z0-9_-]+@([a-zA-Z0-9]+\.)+(com|cn|net|org)$/; //判断邮箱格式是否正确
                let email = document.getElementById("email");
                let email_label = document.getElementById("email-label");
                let email_state = document.getElementById("email-state");
                let email_div = document.getElementById("email-div");
                if (!regExp.exec(email.value) && email.value != "") {
                    email_label.innerHTML = "邮箱格式不正确";
                    email_state.className = "glyphicon glyphicon-remove form-control-feedback"
                    email_div.className = "form-group has-error has-feedback"
                    return false;
                } else {
                    email_label.innerHTML = "";
                    email_state.className = "glyphicon glyphicon-ok form-control-feedback";
                    email_div.className = "form-group has-success has-feedback"
                    return true;
                }
            }
        </script>

    </body>
</html>
