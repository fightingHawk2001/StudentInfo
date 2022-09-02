package student.web.servlet;

import student.domain.PageBean;
import student.domain.Stu;
import student.service.StuService;
import student.service.impl.StuServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 分页查询
 */
@WebServlet("/findStuByPageServlet")
public class FindStuByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        // 获取参数
        String currentPage = request.getParameter("currentPage"); // 当前页码
        String rows = request.getParameter("rows");// 每页显示条数

        // 获取条件查询的参数,姓名、性别、籍贯
        Map<String, String[]> condition = request.getParameterMap();

        // 调用service完成查询
        StuService service = new StuServiceImpl();
        PageBean<Stu> pb = service.findStuByPage(currentPage, rows, condition);
        // 将PageBean对象存入域中
        request.setAttribute("pb", pb);
        // 将map集合也存入域中，以供查询完之后数据回显
        request.setAttribute("condition", condition);
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
