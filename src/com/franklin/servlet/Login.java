package com.franklin.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.franklin.tools.PropUtil;

public class Login extends HttpServlet {

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
		PrintWriter pw = resp.getWriter();
		resp.setContentType("text/html;charset=UTF-8");
		//resp.setCharacterEncoding("UTF-8");
		//pw.write("你好");
		Object obj = req.getParameter("login");
		if(obj!=null) {
			String loginCode = obj.toString();
			String loginInfo = PropUtil.readInfo(loginCode);
			pw.write("出错了！出错信息为："+loginInfo);
			pw.write("<br/>");
		}
		pw.write("<html>");
		pw.write("<head>");
		pw.write("</head>");
		pw.write("<body>");
		pw.write("<form action='LoginCheck' method='post'>");
		pw.write("User:<input type='text' name='username'/>");
		pw.write("<br/>");
		pw.write("Passwd:<input type='password' name='password'/>");
		pw.write("<br/>");
		pw.write("<input type='submit' value='登陆'/>");
		pw.write("&nbsp;&nbsp;");
		pw.write("<input type='reset' value='取消'/>");
		pw.write("</form>");
		pw.write("</body>");
		pw.write("</html>");
		if(pw!=null) {
			pw.close();
		}
	}
}
