/**
 *验证界面 
 */
package com.gyy.userlogin;

import javax.servlet.http.*;

import java.sql.*;

/**
 * @author Gloria
 * 
 */
public class verify extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest req, HttpServletResponse res) {

        // 驱动程序
        String driver = "com.mysql.jdbc.Driver";
        // 连接对象
        Connection con = null;
        // 创建连接的三个参数
        String url = "jdbc:mysql://127.0.0.1:3306/userlogin_db";
        String user = "root";
        String password = "gyy123";
        // statement对象
        Statement statement = null;
        // resultset对象
        ResultSet resultset = null;

        try {
            // 接受用户名和密码
            String username = req.getParameter("username");
            String passwd = req.getParameter("passwd");

            // 利用数据库数据进行验证
            // 连接数据库
            Class.forName(driver);
            // 得到连接
            con = DriverManager.getConnection(url, user, password);
            if (!con.isClosed()) {
                System.out.println("Succeeded connecting to the database !");
            }
            // 创建statement对象，用来执行sql语句,ResultSet类用来存放获取的结果集
            statement = con.createStatement();
            String sql = "select * from tbl_userlogin where username ='" + username + "'and passwd ='" + passwd + "'";
            resultset = statement.executeQuery(sql);
            if(resultset.next()){
                System.out.println("this is legal !");
                res.sendRedirect("welcome?uname="+username);
            }else{
                System.out.println("this is illegal !");
                res.sendRedirect("login");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            try {
                if (resultset != null) {
                    resultset.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
       /*     
        * sql注入的漏洞问题
             * 为了解决sql注入的漏洞问题
             
            if (resultset.next()) {
                String dbPasswd = resultset.getString(1);
                if (dbPasswd.equals(passwd)) {
                    // 合法
                     System.out.println("this is legal!");
                    res.sendRedirect("welcome?uname=" + username);
                }   
            }else{
                // 不合法
                 System.out.println("this is illegal!");
                res.sendRedirect("login");
            }
            
             * if (resultset.next()) { // 合法
             * //System.out.println("this is legal!");
             * res.sendRedirect("welcome?uname="+username); } else { // 不合法 //
             * System.out.println("this is illegal!");
             * res.sendRedirect("login"); }
             
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 最后利用finally语句来关闭数据库,因为在try...catch中最后都会执行finally子句
        finally {
            try {
                if (resultset != null) {
                    resultset.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
*/
        /*
         * // 验证处理 if (username.equals("gyy") && passwd.equals("123")) { // 合法
         * 
         * // 将验证成功的信息写入session中 HttpSession hs = req.getSession(true); //
         * 修改session的存在时间 hs.setMaxInactiveInterval(20);
         * 
         * //清空session中的内容 hs.setMaxInactiveInterval(0);
         * 
         * hs.setAttribute("pass", "ok");
         * 
         * // 跳转到welcome页面 // 首先跳转到welcome页面，同时还要把username通过uname也传递过去
         * res.sendRedirect("welcome?uname=" + username); } else { // 不合法
         * 
         * // 跳转 res.sendRedirect("login");// 写你要跳转的servlet的url，即url-pattern中的地址
         * }
         * 
         * } catch (Exception e) { e.printStackTrace(); }
         */
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) {
        this.doGet(req, res);
    }
}
