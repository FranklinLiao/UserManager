package com.franklin.servlet;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.franklin.dao.User;
import com.franklin.tools.PropUtil;

public class Ok extends HttpServlet {

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
		HttpSession  session = req.getSession();
		User user = (User) session.getAttribute("User");
		session.setAttribute("User", user);
		String code = req.getParameter("code");
		String codeInfo = "";
		if(code!=null) { 
			if(code.equals("add")||code.equals("del")) {//添加或删除用户
				codeInfo = PropUtil.readInfo(code);
			}
		}
		PrintWriter pw = resp.getWriter();
		pw.write("<html>");
		pw.write("<head>");
		pw.write("</head>");
		pw.write("<body>");
		pw.write("<p><font color='#00FF00'>处理成功！</font></p>");
		pw.write("提示信息："+codeInfo);
		pw.write("<br/>");
		pw.write("<a href='Main'>返回主界面</a>");
		pw.write("</body>");
		pw.write("</html>");
		if(pw!=null) {
			pw.close();
			pw = null;
		}
	}
	
}
