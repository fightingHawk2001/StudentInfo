package student.web.servlet;

import org.apache.commons.beanutils.BeanUtils;
import student.domain.Admin;
import student.service.StuService;
import student.service.impl.StuServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 管理员登录
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        // 判断验证码是否正确
        HttpSession session = request.getSession();
        String checkCode = (String) session.getAttribute("checkCode"); // 程序生成的验证码
        session.removeAttribute("checkCode"); // 取出之后马上移除以保证验证码一次性
        String verifyCode = request.getParameter("verifyCode"); // 用户输入的验证码
        if (checkCode != null && checkCode.equalsIgnoreCase(verifyCode)) {
            // 如果验证码相同
            // 获取用户名与密码键值对
            Map<String, String[]> map = request.getParameterMap();
            // 创建Admin对象
            Admin adminLogin = new Admin();
            try {
                // 将map集合封装到admin对象中
                BeanUtils.populate(adminLogin, map);
                // 使用StuServlet对象完成判断用户名与密码是否正确的操作
                StuService service = new StuServiceImpl();
                Admin admin = service.login(adminLogin);
                if (admin != null) {
                    // 不等于空就代表用户名与密码正确，登录成功
                    request.getSession().setAttribute("username", admin.getUsername());
                    response.sendRedirect(request.getContextPath() + "/findStuByPageServlet"); // 重定向
                } else {
                    request.setAttribute("login_error", "用户名或密码错误！");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } else {
            request.setAttribute("login_error", "验证码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
