/**
 * 
 */
package com.gyy.userlogin;

import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Gloria
 *
 */
@SuppressWarnings("serial")
public class CookieTest extends HttpServlet{

    public void doGet(HttpServletRequest req, HttpServletResponse res){
        try{
            res.setContentType("text/html;charset=gbk");
            PrintWriter pw = res.getWriter();
            //当用户访问该servlet时，就将信息创建到该用户的cookie中
            
            //1.先在服务器端创建一个cookie
            Cookie myCookie = new Cookie("gyy", "123");
            
            //2.该cookie存在的时间
            myCookie.setMaxAge(30);
            //如果你不设置存在时间，那么该cookie将不会保存
            
            //3.将该cookie写回到客户端
            res.addCookie(myCookie);
            
            pw.println("已经创建了cookie");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void doPost(HttpServletRequest req, HttpServletResponse res){
        this.doGet(req, res);
    }
}
