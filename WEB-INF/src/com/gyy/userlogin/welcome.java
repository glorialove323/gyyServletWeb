/**
 * 欢迎界面
 */
package com.gyy.userlogin;

import javax.servlet.http.*;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Gloria
 * 
 */
public class welcome extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest req, HttpServletResponse res) {

        /*
         * //得到session HttpSession hs = req.getSession(true); String val
         * =(String) hs.getAttribute("pass"); String sessionID = hs.getId();
         * 
         * if(val == null){ //非法登录 try { System.out.println("this is illegal");
         * res.sendRedirect("login"); } catch (IOException e) {
         * e.printStackTrace(); } }
         */

        // 业务逻辑
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String url = "jdbc:mysql://127.0.0.1:3306/userlogin_db";
        String user = "root";
        String password = "gyy123";

        // 得到从verify页面传递过来的username
        // 在welcome页面显示登录用户的用户名
        String username = req.getParameter("uname");
        try {
            // 解决中文乱码
            res.setCharacterEncoding("utf-8");
            res.setContentType("text/html");

            PrintWriter pw = res.getWriter();

            // 调整布局，居中显示
            pw.println("<body><center>");

            // =====================在servlet中显示图片===================
            pw.println("<img src=imgs/welcome.gif><br>");
            pw.println("Welcom to our site!<br>");
            pw.println("Dear " + username);
            // 做个超链接
            pw.println("<br><a href =login>login again</a><br>");
            // 获取当前时间
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = format.format(date);
            pw.println("current time: " + time);

            // ====================分页的功能========================

            int pageSize = 3;// 一页显示几条记录
            int pageNow = 1;// 希望显示第几页
            int rowCount = 0;// 共有几条记录
            int pageCount = 0;// 共有几页

            // 动态接收pageNow
            String mPageNow = req.getParameter("pageNowok");
            if (mPageNow != null) {
                pageNow = Integer.parseInt(mPageNow);
            }

            // 得到rowCount
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            System.out.println("has connected sql ");
            ps = con.prepareStatement("select count(*) from tbl_userlogin");
            rs = ps.executeQuery();
            if (rs.next()) {
                rowCount = rs.getInt(1);
            }
            // 计算pageCount
            if (pageCount % pageSize == 0) {
                pageCount = rowCount / pageSize;
            } else {
                pageCount = rowCount / pageSize + 1;
            }
            ps = con.prepareStatement("select * from tbl_userlogin limit " + pageSize * (pageNow - 1) + "," + pageSize
                    + "");
            // 给？赋值
            // ps.setInt(1, pageSize);//给第一个问号赋值
            // ps.setInt(2, pageSize*(pageNow-1));//给第二个问号赋值

            rs = ps.executeQuery();

            pw.println("<table border = 1");
            pw.println("<tr><th>id</th><th>name</th><th>passwd</th><th>mail</th><th>grade</th></tr>");

            while (rs.next()) {
                pw.println("<tr>");
                pw.println("<td>" + rs.getInt(1) + "</td>");
                pw.println("<td>" + rs.getString(2) + "</td>");
                pw.println("<td>" + rs.getString(3) + "</td>");
                pw.println("<td>" + rs.getString(4) + "</td>");
                pw.println("<td>" + rs.getInt(5) + "</td>");
                pw.println("</tr>");
            }
            pw.println("</table>");

            //======================超链接显示上一页=========================
            if(pageNow!=1){
                pw.println("<a href = welcome?pageNowok="+(pageNow-1)+">前一页</a>");                
            }
           
            //======================超链接显示页数==========================
            //即使页数很多，当前页面也只显示特定的页数
            for (int i = pageNow; i <= pageNow+pageSize; i++) {
                pw.println("<a href =welcome?pageNowok=" + i + ">" + i + "</a>");
            }
            
           //=======================超链接显示下一页=========================
            if(pageNow!=pageCount){
                pw.println("<a href = welcome?pageNowok="+(pageNow+1)+">后一页</a>");
            }

            pw.println("</center></body>");

            // pw.println("session ID:"+sessionID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) {
        this.doGet(req, res);
    }
}
