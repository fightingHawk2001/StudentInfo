<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>登录</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="../fightingHawk/fightingHawk.png" rel="shortcut icon" type="image/png">
        <script src="js/jquery-3.5.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </head>
    <body>
        <script>
            <%--window.history.forward();--%>
            // 页面加载完之后执行js
            window.onload = function () {
                let img = document.getElementById("vcode"); // 获取图片对象
                // 添加点击事件
                img.onclick = function () {
                    let time = new Date().getTime(); // 获取时间戳
                    img.src = "${pageContext.request.contextPath}/checkCodeServlet?" + time // 加上时间戳，防止缓存情况
                }
            }
        </script>
        <script>
            $(function () {
                window.onpageshow = function (event) {
                    if (event.persisted) {
                        window.location.reload();
                    }
                }

            });
        </script>

		<div style="margin-top: 20px; text-align: center; margin-top: 50px">
			<span style="font-size: 30px;">
				学生信息管理系统
			</span>
        </div>

        <div class="container" style="width: 330px;margin-top: 50px">
            <h3 style="text-align: center">登录</h3>
                                                    <%--临时取消登录验证功能--%>
            <form action="${pageContext.request.contextPath}/loginServlet" method="post">
                <div class="form-group">
                    <label for="username">用户名 : </label>
                    <input type="text" name="username" class="form-control" id="username" placeholder="请输入用户名">
                </div>
                <div class="form-group">
                    <label for="password">密码 : </label>
                    <input type="password" name="password" class="form-control" id="password" placeholder="请输入密码">
                </div>
                <div class="form-group">
                    <label for="verifyCode">验证码 : </label>
                    <div class="form-inline">
                        <input type="text" name="verifyCode" class="form-control" id="verifyCode"
                               placeholder="请输入验证码">
                        <img class="img-rounded" src="${pageContext.request.contextPath}/checkCodeServlet" title="Hit Refresh" id="vcode">
                    </div>
                </div>
                <hr>
                <div class="form-group">
                    <input class="btn btn-primary" type="submit" value="登录"
                           style="display: block; width:100px; margin: auto">
                </div>
            </form>
            <%--出错提示的信息框--%>
            <c:if test="${not empty login_error}"> <%--如果错误信息不为空才显示错误消息框--%>
                <div class="alert alert-warning alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <strong>${login_error}</strong>
                </div>
            </c:if>
        </div>
    </body>
</html>
