package student.web.servlet;

import student.service.StuService;
import student.service.impl.StuServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 删除学生信息
 */
@WebServlet("/deleteStuServlet")
public class DeleteStuServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取需要删除的id
        String id = request.getParameter("id");
        //调用Service完成删除
        StuService service = new StuServiceImpl();
        service.deleteStu(id);
        // 重定向到查询所有页面
        response.sendRedirect(request.getContextPath() + "/findStuByPageServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
