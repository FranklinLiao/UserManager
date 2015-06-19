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

public class Err extends HttpServlet {

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
		PrintWriter pw = resp.getWriter();
		String errcode = req.getParameter("errcode");
		String errInfo = req.getParameter("info");
		String errMsg = "";
		if(errcode!=null) {
			if(errcode.equals("add")||errcode.equals("del")) { //add用户的时候出的错
				errMsg = PropUtil.readInfo(errInfo);
			}
		}
		pw.write("<html>");
		pw.write("<head>");
		pw.write("</head>");
		pw.write("<body>");
		pw.write("<p><font color='#FF0000'>出错了！</font></p>");
		pw.write("出错信息："+errMsg);
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
