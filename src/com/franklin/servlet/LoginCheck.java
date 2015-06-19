package com.franklin.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.franklin.dao.User;
import com.franklin.db.DbUtil;
import com.mysql.jdbc.interceptors.SessionAssociationInterceptor;

public class LoginCheck extends HttpServlet {

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
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		User user = new User(username,password);
		//PrintWriter pw = resp.getWriter();
		User checkUser = new DbUtil().checkUser(user);
		if(checkUser!=null) {
			//将User存入Session中，并跳转到另一个servlet
			HttpSession  session = req.getSession();
			session.setAttribute("User", checkUser);
			req.getRequestDispatcher("/Main").forward(req, resp);
			//pw.write("Welcom User: "+checkUser.getUsername());
		} else {
			req.getRequestDispatcher("Login?login=usernotmatch").forward(req, resp);
			//pw.write("wrong user");
		}
		/*
		if(pw!=null) {
			pw.close();
		}
		*/
	}
	
}
