package student.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        // 强制转换为其实现类
        HttpServletRequest request = (HttpServletRequest)req;
        // 获取资源请求路径
        String uri = request.getRequestURI();
        // 判断访问的是否是登录页面和首页，除此之外，还要排除其他一些页面资源,验证码，甚至一些css/js
        if (uri.contains("login.jsp") ||uri.contains("loginServlet") || uri.contains("index.jsp")
                || uri.equals("/StudentInfo/") || uri.contains("checkCodeServlet") || uri.contains("/js/")
                || uri.contains("/css/")) {
            // 如果是，则直接放行
            chain.doFilter(request, resp);
        } else {
            // 如果不是，则判断用户是否已经登录
            // 从session中获取username
            Object username = request.getSession().getAttribute("username");
            if (username != null) {
                // 如果不为空，则表示已经登录，放行
                chain.doFilter(request, resp);
            } else {
                // 否则就是没有登录，则跳转到登录页面
                request.setAttribute("login_error", "您还没有登录，请先登陆！");
                request.getRequestDispatcher("/login.jsp").forward(request, resp);
            }
        }
        // 如果已经登录则会在session中存储的有username键值对
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
