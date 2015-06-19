package com.franklin.servlet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.franklin.dao.User;
import com.franklin.db.DbUtil;

public class AddUser extends HttpServlet {

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
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String confirmPassword = req.getParameter("confirmPassword");
		if(username==null||password==null||confirmPassword==null) {
			req.getRequestDispatcher("/Err?errcode=add&info=addnull").forward(req, resp);
			//return;
		}
		username = username.trim();
		password = password.trim();
		confirmPassword = confirmPassword.trim();
		//判断两个密码是否一致
		if(!password.equals(confirmPassword)) {
			req.getRequestDispatcher("/Err?errcode=add&info=addpwNotSame").forward(req, resp);
			return;
		}
		//用户名、密码为""
		if(username.equals("")||password.equals("")) {
			req.getRequestDispatcher("/Err?errcode=add&info=addnull").forward(req, resp);
			return;
		}
		User user = new User(username,password);
		//判断该用户是否已经存在
		boolean existFlag = new DbUtil().checkExistUser(user);
		if(existFlag) {
			req.getRequestDispatcher("/Err?errcode=add&info=addexist").forward(req, resp);
			return;
		} 
		//判断是否添加成功
		boolean addFlag = new DbUtil().addUser(user);
		if(addFlag) {
			req.getRequestDispatcher("/Ok?code=add").forward(req, resp);
			return;
		} else {
			req.getRequestDispatcher("/Err?errcode=add&info=addinsertErr").forward(req, resp);
			return;
		}
		
	}
	
}
