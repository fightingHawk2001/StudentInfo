package student.web.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * 模拟验证码
 */
@WebServlet("/checkCodeServlet")
public class CheckCode extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 通知浏览器不要缓存
        resp.setHeader("pragma", "no-cache");
        resp.setHeader("cache-control", "no-cache");
        resp.setHeader("expires", "0");

        int width = 90;
        int height = 30;
        // 1、创建一对象，在内存中表示图片（验证码对象）
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);


        // 2、美化图片
        // 填充背景色
        Graphics g = image.getGraphics(); // 获取画笔对象
        g.setColor(Color.GRAY); // 设置画笔颜色
        g.fillRect(0, 0, width, height); // 填充颜色

        // 写验证码
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdrfghijklmnopqrstuvwxyz0123456789"; // 验证码中字符得所有可能
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        g.setColor(Color.YELLOW);
        g.setFont(new Font("黑体", Font.BOLD, 24));
        for (int i = 1; i <= 4; i++) {
            int index = random.nextInt(str.length());
            int rdWidth = 10 * (i + (i - 1));
            int rdHeight = 25;
            char ch = str.charAt(index); // 随机字符
            sb.append(ch);
            g.drawString(ch + "", rdWidth, rdHeight); // 写字


        }
        String checkCode = sb.toString();
        HttpSession session = req.getSession();
        session.setAttribute("checkCode", checkCode);

        // 3、将图片输出道页面展示
        ImageIO.write(image, "png", resp.getOutputStream());
    }
}
