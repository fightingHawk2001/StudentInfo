package student.web.servlet;

import student.service.StuService;
import student.service.impl.StuServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteSelectedServlet")
public class DeleteSelectedServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取选中的id值数组
        String[] checkIds = request.getParameterValues("checkId");
        // 调用service完成删除
        StuService service = new StuServiceImpl();
        service.deleteStus(checkIds);
        // 重定向到查询所有页面
        response.sendRedirect(request.getContextPath() + "/findStuByPageServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
