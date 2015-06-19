package com.franklin.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sun.org.mozilla.javascript.internal.ast.NewExpression;

import com.franklin.dao.User;

public class Main extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html;charset=UTF-8");
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = resp.getWriter();
		HttpSession session = req.getSession();
		//总访问量
		int visitUserCnt = 1;
		Integer visitUserCntInteger = (Integer) this.getServletContext().getAttribute("VisitorUserCnt");
		if(visitUserCntInteger!=null) {
			visitUserCnt = visitUserCntInteger;
		}
		//当前在线用户数
		int onlineUserCnt = 1;
		Integer onlineUserCntInteger = (Integer) this.getServletContext().getAttribute("OnlineUserCnt");
		if(onlineUserCntInteger!=null) {
			onlineUserCnt = onlineUserCntInteger;
		}
		User user = (User) session.getAttribute("User");
		String username = "";
		if(user!=null) {
			username = user.getUsername().trim();
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String nowTime = sdf.format(date);
		Cookie[] cookies = req.getCookies();
		String lastTime = "";
		if(cookies!=null) {
			for(int i=0;i<cookies.length;i++) {
				if(cookies[i].getName().equals(username+"lastTime")) {
					lastTime = cookies[i].getValue();
				}
			}
		}
		
		Cookie cookie = new Cookie(username+"lastTime", nowTime);
		resp.addCookie(cookie);
		pw.write("<html>");
		pw.write("<head>");
		pw.write("</head>");
		pw.write("<body>");
		pw.write("<a href='Login'>退出系统</a>");
		pw.write("<p>");
		pw.write("<font color='#FF0000'>");
		pw.write("Welcome User: "+username);
		if(lastTime.length()>1) {
			pw.write("&nbsp;&nbsp;您上次登陆时间为:"+lastTime);
		} else {
			pw.write("&nbsp;&nbsp;您是第一次登陆");
		}
		pw.write("</font>");
		pw.write("</p>");
		pw.write("<a href='AddUserPage'>添加用户</a>");
		pw.write("<br/>");
		pw.write("<a href='DelUserPage'>删除用户</a>");
		pw.write("<br/>");
		pw.write("<a href='ShowUserPage?pageNow=1'>展示用户信息</a>");
		pw.write("<br/>");
		pw.write("您是第"+visitUserCnt+"位来访者！"+"当前在线用户数："+onlineUserCnt+".");
		pw.write("</body>");
		pw.write("</html>");
		if(pw!=null) {
			pw.close();
			pw = null;
		}
	}
	
}
