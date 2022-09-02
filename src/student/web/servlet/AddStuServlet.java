package student.web.servlet;

import org.apache.commons.beanutils.BeanUtils;
import student.domain.Stu;
import student.service.StuService;
import student.service.impl.StuServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 添加学生信息
 */
@WebServlet("/addStuServlet")
public class AddStuServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Map<String, String[]> map = request.getParameterMap();
        Stu stu = new Stu();
        try {
            BeanUtils.populate(stu, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        // 调用StuService保存
        StuService service = new StuServiceImpl();
        service.addStu(stu);
        // 跳转到StuListServlet页面列表查询,重定向
        response.sendRedirect(request.getContextPath() + "/findStuByPageServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
