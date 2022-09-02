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
import java.util.List;

/**
 * 查询显示所有信息,有了分页查询这个就没用了
 */
@WebServlet("/stuListServlet")
public class StuListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1、调用StuService完成查询
        StuService service = new StuServiceImpl();
        List<Stu> stus = service.findAll();
        // 2、将list存入request域
        request.setAttribute("stus", stus);
        // 3、转发到list.jsp
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
