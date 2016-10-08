/**
 * 登录界面
 */
package com.gyy.userlogin;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;

/**
 * @author Gloria
 * 
 */
public class login extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    // 重写doGet方法
    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        PrintWriter pw;
        try {
            //解决中文乱码+识别HTML标签
            res.setContentType("text/html; charset= gbk");
            pw = res.getWriter();

            pw.println("<html>");
            pw.println("<body>");
            pw.println("<h1>登录界面</h1>");
            //要实现登录页面的跳转，需要在这里的action进行指定页面
            pw.println("<form action=verify method = post>");
            pw.println("用户名：<input type = text name = username><br>");
            pw.println("密码：<input type = password name = passwd><br>");
            pw.println("<input type = checkbox name = keep value = 2>两周内不再重新登录<br>");
            pw.println("<input type = submit value = loging><br>");
            //hidden 表示隐藏属性， 在界面是不显示的
            pw.println("<input type = hidden name = sex value = 女><br>");
            pw.println("<input type = submit value = 提交><br>");
            pw.println("</form>");
            pw.println("</html>");
            pw.println("</body>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 重写doPost方法
    public void doPost(HttpServletRequest req, HttpServletResponse res) {
        this.doGet(req, res);
    }
}
