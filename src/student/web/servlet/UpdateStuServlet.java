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
 * 修改学生信息
 */
@WebServlet("/updateStuServlet")
public class UpdateStuServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        // 获取表单提交的信息
        Map<String, String[]> map = request.getParameterMap();
        // 封装为Stu对象
        Stu stu = new Stu();
        try {
            BeanUtils.populate(stu, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        // 调用service完成修改
        StuService service = new StuServiceImpl();
        service.updateStu(stu);
        // 重定向到查询所有页面
        response.sendRedirect(request.getContextPath() + "/findStuByPageServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
