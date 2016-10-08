/**
 * 这是第二种实现servlet的方法（继承GenericServlet的方法）
 */
package com.gyy.servlet;
import javax.servlet.*;

import java.io.*;

/**
 * @author Gloria
 *
 */
public class InheritGenericServlet extends GenericServlet{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    //重写service方法即可
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        // TODO Auto-generated method stub
        System.out.println("service it");
        PrintWriter pw = res.getWriter();
        pw.println("Hello, this is extended from GenericServlet");
    }

}
