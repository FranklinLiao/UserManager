package com.franklin.servlet;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DelUserPage extends HttpServlet {

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
		pw.write("<html>");
		pw.write("<head>");
		pw.write("</head>");
		pw.write("<body>");
		pw.write("<a href='Login'>退出系统</a>");
		pw.write("&nbsp;&nbsp;");
		pw.write("<a href='Main'>返回主界面</a>");
		pw.write("<p>");
		pw.write("<font color='#FF0000'>");
		pw.write("删除用户");
		pw.write("</font>");
		pw.write("</p>");
		pw.write("<form action='DelUser' method='post'>");
		pw.write("用户名:<input type='text' name='username'/>");
		pw.write("<br/>");
		pw.write("密码:<input type='password' name='password'/>");
		pw.write("<br/>");
		pw.write("<input type='submit' value='提交'/>");
		pw.write("&nbsp;&nbsp;");
		pw.write("<input type='reset' value='取消'/>");
		pw.write("</form>");
		pw.write("</body>");
		pw.write("</html>");
		if(pw!=null) {
			pw.close();
			pw = null;
		}
	}
	
}
