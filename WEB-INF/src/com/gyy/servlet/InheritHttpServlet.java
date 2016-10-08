/**
 * 这是第三种开发servlet的方法（继承HttpServlet的方法）
 */
package com.gyy.servlet;
import javax.servlet.http.*;

import java.io.*;

/**
 * @author Gloria
 *
 */
public class InheritHttpServlet extends HttpServlet{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    //重写doGet方法
    public void doGet(HttpServletRequest req, HttpServletResponse res){
        PrintWriter pw;
        try {
            pw = res.getWriter();
        pw.println("Hello, this is extended from HttpServlet");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //重写doPost方法
    public void doPost(HttpServletRequest req, HttpServletResponse res){
        this.doGet(req, res);
    }

}
