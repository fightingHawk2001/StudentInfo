<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>学生信息管理系统</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="../fightingHawk/fightingHawk.png" rel="shortcut icon" type="image/png">
    <script src="js/jquery-3.5.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
      td,th{
        text-align: center;
      }
    </style>
    <script>function clearCondition() {
      document.getElementById("name").value = "";
      document.getElementById("gender").value = "";
      document.getElementById("address").value = "";
      document.getElementById("clearButton").blur();
    }

    <%--window.history.forward();--%>

      function deleteStu(id) {
        // 弹出确定删除的提示框
        if (confirm("您确定要删除此条记录吗？")) {
          // 如果点击了确认则访问删除的Servlet
          location.href="${pageContext.request.contextPath}/deleteStuServlet?id=" + id;
        }
      }

    window.onload = function () {
      //给删除选中按钮添加点击事件
      document.getElementById("delSelected").onclick = function () {
        let flag = false;
        let checkIds = document.getElementsByName("checkId");
        // 循环判断是否有记录被选中
        for (let i = 0; i < checkIds.length; i++) {
          // 如果有至少一条记录被选中
          if (checkIds[i].checked) {
            flag = true;
            break;
          }
        }
        // 如果flag为true才提示是否删除
        if (flag) {
          if (confirm("您确定要删除这些选中的记录吗？")) {
            // 提交表单
            document.getElementById("form").submit();
          }
        } else {
          alert("您选择了0条记录，请至少选择1条记录。")
        }
      }
      // 实现全选功能
      document.getElementById("checkAll").onclick = function () {
        // 获取所有的CheckBox
        let checkIds = document.getElementsByName("checkId");
        // 循环遍历
        for (let i = 0; i < checkIds.length; i++) {
          // 设置与第一个全选CheckBox相同的状态
          checkIds[i].checked = this.checked;
        }
      }

      // 实现取消选中功能
      document.getElementById("cancelSelected").onclick = function () {
        // 获取所有的CheckBox
        let checkIds = document.getElementsByName("checkId");
        // 循环遍历
        for (let i = 0; i < checkIds.length; i++) {
          // 设置为不选中的状态
          checkIds[i].checked = false;
        }
        // 同时设置全选按钮也为false
        document.getElementById("checkAll").checked = false;
      }

      // 实现反选的功能
      document.getElementById("invertSelected").onclick = function () {
        // 获取所有的CheckBox
        let checkIds = document.getElementsByName("checkId");
        // 循环遍历
        for (let i = 0; i < checkIds.length; i++) {
          // 设置为相反的状态
          checkIds[i].checked = !checkIds[i].checked;
        }
        // 同时设置全选按钮也为false
        document.getElementById("checkAll").checked = false;
      }
    }
    </script>
  </head>
  <body>
    <div class="container" style="width: fit-content;margin-top: 10px;">
      <div style="float: right">
        <span style="">你好 ${sessionScope.username}，欢迎访问！</span>
        <a class="btn btn-warning" href="login.jsp">退出登录</a>
      </div>
      <h3 style="text-align: center;margin-top: 50px">学生信息列表</h3>

      <div style="float: left">
        <form class="form-inline" action="${pageContext.request.contextPath}/findStuByPageServlet" method="post">
          <div class="form-group">
            <label for="name">姓名</label>
            <input type="text" class="form-control" id="name" name="name" value="${condition.name[0]}">
          </div>
          <div class="form-group">
            <label for="gender">性别</label>
            <select name="gender" id="gender" class="form-control">
              <c:choose>
                <c:when test="${condition.gender[0] == '男'}">
                  <option value="">默认</option>
                  <option value="男" selected>男</option>
                  <option value="女">女</option>
                </c:when>
                <c:when test="${condition.gender[0] == '女'}">
                  <option value="">默认</option>
                  <option value="男">男</option>
                  <option value="女" selected>女</option>
                </c:when>
                <c:otherwise>
                  <option value="" selected>默认</option>
                  <option value="男">男</option>
                  <option value="女">女</option>
                </c:otherwise>
              </c:choose>
            </select>
          </div>
          <div class="form-group">
            <label for="address">籍贯</label>
            <input type="text" class="form-control" id="address" name="address" value="${condition.address[0]}">
          </div>
          <button type="submit" class="btn btn-default">查询</button>
          <button type="button" id="clearButton" class="btn btn-default" onclick="clearCondition()" >清除</button>
        </form>
      </div>

      <div style="float: right; margin-left: 20px">
        <a class="btn btn-primary" href="add.jsp">添加学生信息</a>&nbsp;
        <a class="btn btn-primary" href="javascript:void(0);" id="invertSelected">反选</a>&nbsp;
        <a class="btn btn-primary" href="javascript:void(0);" id="cancelSelected">取消选中</a>&nbsp;
        <a class="btn btn-primary" href="javascript:void(0);" id="delSelected">删除选中</a>&nbsp;
      </div>

      <form id="form" action="${pageContext.request.contextPath}/deleteSelectedServlet" method="post" style="min-height: 560px">
        <table border="1" class="table table-bordered table-hover">
          <tr class="success">
            <th><input type="checkbox" id="checkAll"></th>
            <th>ID</th>
            <th>姓名</th>
            <th>性别</th>
            <th>年龄</th>
            <th>籍贯</th>
            <th>QQ</th>
            <th>邮箱</th>
            <th>操作</th>
          </tr>
          <%--pb.list里面存的就是stu对象--%>
          <c:forEach items="${pb.list}" var="stu" varStatus="s">
            <tr>
              <td><input type="checkbox" name="checkId" value="${stu.id}"></td>
              <td>${s.count}</td>
              <td>${stu.name}</td>
              <td>${stu.gender}</td>
              <td>${stu.age}</td>
              <td>${stu.address}</td>
              <td>${stu.qq}</td>
              <td>${stu.email}</td>
              <td>
                <a class="btn btn-sm btn-default"
                   href="${pageContext.request.contextPath}/findStuByIdServlet?id=${stu.id}">修改
                </a>&nbsp;
                <a class="btn btn-sm btn-default"
                   href="javascript:deleteStu(${stu.id})">删除
                </a>&nbsp;
              </td>
            </tr>
          </c:forEach>
        </table>
      </form>

      <div>
        <nav aria-label="Page navigation">
          <ul class="pagination">
            <c:if test="${pb.currentPage == 1}">
              <li disabled="disabled" class="disabled">
                <a aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
              </li>
            </c:if>
            <c:if test="${pb.currentPage != 1}">
              <li>
                <a href="${pageContext.request.contextPath}/findStuByPageServlet?currentPage=${pb.currentPage - 1}&rows=10&name=${condition.name[0]}&gender=${condition.gender[0]}&address=${condition.address[0]}"
                   aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
              </li>
            </c:if>

            <c:forEach begin="1" end="${pb.totalPage}" var="i">
              <c:if test="${pb.currentPage == i}">
                <li class="active">
                  <a href="${pageContext.request.contextPath}/findStuByPageServlet?currentPage=${i}&rows=10&name=${condition.name[0]}&gender=${condition.gender[0]}&address=${condition.address[0]}">${i}</a>
                </li>
              </c:if>
              <c:if test="${pb.currentPage != i}">
                <li><a href="${pageContext.request.contextPath}/findStuByPageServlet?currentPage=${i}&rows=10&name=${condition.name[0]}&gender=${condition.gender[0]}&address=${condition.address[0]}">${i}</a></li>
              </c:if>
            </c:forEach>

            <c:if test="${pb.currentPage == pb.totalPage}">
              <li disabled class="disabled">
                <a aria-label="Next"><span aria-hidden="true">&raquo;</span></a>
              </li>
            </c:if>
            <c:if test="${pb.currentPage != pb.totalPage}">
              <li>
                <a href="${pageContext.request.contextPath}/findStuByPageServlet?currentPage=${pb.currentPage + 1}&rows=10&name=${condition.name[0]}&gender=${condition.gender[0]}&address=${condition.address[0]}"
                   aria-label="Next"><span aria-hidden="true">&raquo;</span></a>
              </li>
            </c:if>
            <span style="font-size: 24px;margin-left: 5px">
              一共有 ${pb.totalCount} 条记录, ${pb.totalPage} 页
            </span>
          </ul>
        </nav>
      </div>
    </div>
  </body>
</html>
