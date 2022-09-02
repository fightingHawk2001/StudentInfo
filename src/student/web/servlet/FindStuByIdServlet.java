package student.web.servlet;

import student.domain.Stu;
import student.service.StuService;
import student.service.impl.StuServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 修改学生信息之前的查询回显
 */
@WebServlet("/findStuByIdServlet")
public class FindStuByIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取需要修改信息的记录id
        String id = request.getParameter("id");
        // 调用service通过id查询信息
        StuService service = new StuServiceImpl();
        Stu stu = service.findStuById(id);
        // 存储Stu对象，转发到修改页面
        request.setAttribute("stu", stu);
        request.getRequestDispatcher("/update.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
