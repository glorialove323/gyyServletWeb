/**
 * 这是第一种开发servlet的方法（实现servlet的接口）
 */
package com.gyy.servlet;
import javax.servlet.*;

import java.io.*;

/**
 * @author Gloria
 *
 */
public class InheritServlet implements Servlet{

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
        System.out.println("Destroy it");
        
    }

    @Override
    public ServletConfig getServletConfig() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getServletInfo() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void init(ServletConfig arg0) throws ServletException {
        // TODO Auto-generated method stub
        System.out.println("init it");
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        // TODO Auto-generated method stub
        System.out.println("service it");
        PrintWriter pw = res.getWriter();
        pw.println("Hello, this is implemented from Servlet");
    }

}
